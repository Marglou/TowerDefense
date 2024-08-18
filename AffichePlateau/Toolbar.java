package AffichePlateau;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.swing.*;

import Entités.Tour;
import Game.Joueur;
import Game.Main;
import managers.GameManager;
import Design.MyButtons;

public class Toolbar extends JPanel {

    private JLabel score = new JLabel();
    private JButton returnToMenuButton;
    private TourButton tourButton;
    private JPanel panelInfo = new JPanel();
    private Tour tourStockee;
    private Joueur joueur;
    private Main window;
    private JLabel ame;
    private GameManager gameManager;

    public Toolbar(Main window, GameManager gameManager) {
        this.joueur = gameManager.getJoueur();
        this.window = window;
        this.gameManager = gameManager;
        this.joueur = gameManager.getJoueur();

        setLayout(null); // Utilisation de null pour le gestionnaire de disposition

        // Autres boutons pour Pause et Retour au menu
        returnToMenuButton = returnToMenuButton(window);

        tourButton = new TourButton(joueur.getToursEnPossession());

        // affiche le nombre de vie
        this.ame = MyButtons.iniLabel(gameManager.getAme() + " âmes", 990, 3, 100, 30);

        iniPanelInfo();

        // Ajouter le panneauTour au panneau principal
        add(ame);
        add(tourButton.getPanelTour());
        add(returnToMenuButton);
        add(panelInfo);

        if (gameManager.getGame().getTypeGame().equals("marathon")) {
            this.score = MyButtons.iniLabel("Ennemis tués : " + gameManager.getGame().getScore(), 10, 75, 100, 30);
            add(score);
        }

        // mainPanel Toolbar
        setBackground(new Color(115, 175, 175));
        setPreferredSize(new Dimension(800, 210));
    }

    // initialise le panneau des info sur les tours
    private void iniPanelInfo() {
        panelInfo.setPreferredSize(new Dimension(200, 600));
        panelInfo.setBackground(new Color(75, 100, 125));
        panelInfo.setBounds(970, 35, 200, 165);
        panelInfo.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
    }

    // bouton retour
    public JButton returnToMenuButton(Main window) {
        JButton returnToMenuButton = MyButtons.initButton("Retour", 10, 10, 100, 30);
        returnToMenuButton.addActionListener(e -> {
            // gameManager.getGame().stopGame();
            // window.switchToPage("Home");
            gameManager.getGame().reset();
            window.replacePanel(window.getHomePanel(), "Home");
            
        });
        return returnToMenuButton;
    }

    // // bouton pause
    // public JButton pauseButton(Main window) {
    //     JButton pauseButton = MyButtons.initButton("Pause", 10, 50, 100, 30);
    //     pauseButton.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             // gameManager.getGame().stopGame();
    //         }
    //     });
    //     return pauseButton;
    // }

    // -------- AFFICHAGE -------------

    // affiche le nombre d'ames
    public void update() {
        this.ame.setText(gameManager.getAme() + " âmes");
        this.score.setText("Ennemis tués : " + gameManager.getGame().getScore());
        SwingUtilities.invokeLater(() -> { 
            repaint();
        });
    }

    // affiche les infos de la tour + possiblité d'achat quand click
    private void afficherInfoTour(JButton button) {
        // Récupérer l'objet Tour à partir de la propriété du bouton
        Tour tourAssocie = (Tour) button.getClientProperty("tour");

        // Logique pour afficher les informations dans le panneauInfo
        JLabel nomLabel = new JLabel("Nom: " + tourAssocie.getClass().getSimpleName());
        JLabel degatsLabel = new JLabel("Dégâts: " + tourAssocie.getDegats());
        JLabel pvLabel = new JLabel("Pv: " + tourAssocie.getPointsDeVie());
        JLabel coutPlacementLabel = new JLabel("Coût de placement: " + tourAssocie.getCoutPlacement());

        JButton buttonAchat = new JButton("Acheter"); // Déclarer en dehors des blocs if
        buttonAchat.putClientProperty("tour", tourAssocie);

        if (gameManager.isAmeEnough(tourAssocie.getCoutPlacement()) && tourStockee == null) {
            buttonAchat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tourStockee == null) {
                        acheterTour(tourAssocie);
                        afficherInfoTour(button);
                    }
                }
            });

        } else if (!gameManager.isAmeEnough(tourAssocie.getCoutPlacement())) {
            buttonAchat.setText("Pas assez d'âmes");
            buttonAchat.setEnabled(false); // Désactiver le bouton s'il n'y a pas assez d'âmes

        } else if (tourStockee != null) {
            buttonAchat.setText("Annuler");
            buttonAchat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    annulerAchat(tourStockee);
                    tourStockee = null;
                    afficherInfoTour(button);
                }
            });

        }

        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS)); // Utiliser un layout en colonne
        panelInfo.removeAll(); // Supprimer les composants précédents
        panelInfo.add(nomLabel);
        panelInfo.add(degatsLabel);
        panelInfo.add(pvLabel);
        panelInfo.add(coutPlacementLabel);
        panelInfo.add(buttonAchat);
        panelInfo.revalidate();
        panelInfo.repaint();
        update();
    }

    private void annulerAchat(Tour tour) {
        gameManager.addAme(tour.getCoutPlacement());
    }

    // affiche les infos de la tour + possiblité d'amélioration quand click
    public void afficherInfoTour(Tour tour) {
        // Logique pour afficher les informations dans le panneauInfo
        JLabel nomLabel = new JLabel("Nom: " + tour.getClass().getSimpleName());
        JLabel degatsLabel = new JLabel("Dégâts: " + tour.getDegats());
        JLabel pvLabel = new JLabel("Pv: " + tour.getPointsDeVie());
        JLabel coutPlacementLabel = new JLabel("Coût d'amélioration: " + tour.getCoutAmelioration());
        JLabel lvlTour = new JLabel("Niveau : " + tour.getNiveauActuel());

        JButton buttonAmelioration = new JButton("Ameliorer");
        buttonAmelioration.putClientProperty("tour", tour);

        if (gameManager.isAmeEnough(tour.getCoutAmelioration())) {
            buttonAmelioration.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    amelioreTour(tour);
                    afficherInfoTour(tour);
                }
            });

        }

        if (!gameManager.isAmeEnough(tour.getCoutPlacement())) {
            buttonAmelioration = new JButton("Pas assez d'âmes");
            buttonAmelioration.setEnabled(false); // Désactiver le bouton s'il n'y a pas assez d'âmes

        }

        if (tour.getNiveauActuel() == 3) {
            buttonAmelioration.setText("Niveau Max");
            buttonAmelioration.setEnabled(false);
        }

        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS)); // Utiliser un layout en colonne
        panelInfo.removeAll(); // Supprimer les composants précédents
        panelInfo.add(nomLabel);
        panelInfo.add(degatsLabel);
        panelInfo.add(pvLabel);
        panelInfo.add(coutPlacementLabel);
        panelInfo.add(lvlTour);
        panelInfo.add(buttonAmelioration);

        panelInfo.revalidate();
        panelInfo.repaint();
        update();
    }

    // -------- ACHAT -------------
    protected void acheterTour(Tour tour) {

        if (this.tourStockee == null) { // Nom de la classe en tant que chaîne
            String nomPackage = "Entités.";
            String nomDeLaClasse = tour.getClass().getSimpleName();

            try {
                // Charger la classe en utilisant le ClassLoader
                Class<?> classe = Class.forName(nomPackage + nomDeLaClasse);

                // Créer une nouvelle instance de la classe
                Object nouvelObjet = classe.getDeclaredConstructor().newInstance();

                // Vous pouvez maintenant utiliser l'objet nouvellement créé selon vos besoins
                Tour tourTmp = (Tour) nouvelObjet;
                gameManager.acheterTour(tourTmp);
                if (joueur.getBonusChoisi() != null && !joueur.getBonusChoisi().getClass().getSimpleName().equals("DegatsContinus")) {
                    joueur.getBonusChoisi().appliquerBonus(tourTmp);
                }
                this.tourStockee = tourTmp;

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

    // -------- AMELIORATION -------------
    private void amelioreTour(Tour tour) {
        gameManager.amelioreTour(tour);
        tour.améliorerTour();
    }

    // -------- AUTRES -------------
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Ajouter une ligne de séparation en haut
        g.setColor(Color.BLACK); // Ajustez la couleur si nécessaire
        g.drawLine(0, 0, getWidth(), 0);
    }

    // Classe interne pour les boutons de tour
    public class TourButton extends JPanel {

        private ArrayList<Tour> tours;
        private JPanel panelTour = new JPanel();

        // initialise le panneau des Tours possédées
        public TourButton(ArrayList<Tour> tours) {
            this.tours = tours;
            panelTour.setBackground(new Color(50, 50, 50));
            panelTour.setBounds(150, 15, 800, 175);
            drawToursDispo();
        }

        private void drawToursDispo() {
            int x = 0;
            for (Tour tour : tours) {
                init(tour, x);
                x += 75;
            }
        }

        // affiche les tours utilisables pendant le jeu
        private void init(Tour tour, int x) {

            // Créer une instance de JButton
            JButton button = MyButtons.initButton("", x, 25, 75, 30, new Color(50, 50, 50));
            // button.setBackground(new Color(50, 50, 50));

            JLabel tourLabel = tour.getSpriteContainer();
            ImageIcon tourSprite = (ImageIcon) tourLabel.getIcon();

            // Redimensionner l'image de la tour
            int taille = 75;
            Image scaledImage = tourSprite.getImage().getScaledInstance(taille - 10, taille, Image.SCALE_DEFAULT);
            ImageIcon tourIcon = new ImageIcon(scaledImage);

            // Configurer le bouton avec l'image redimensionnée
            button.setIcon(tourIcon);

            // Ajouter le bouton au panneauTour
            panelTour.add(button);

            // Stocker la référence à l'objet Tour comme propriété du bouton
            button.putClientProperty("tour", tour);

            // affiche les infos de la tour + possiblité d'achat quand click
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    acheterTour(tour);
                    afficherInfoTour(button);
                }
            });

        }

        public JPanel getPanelTour() {
            return panelTour;
        }
    }

    public JButton getReturnToMenuButton() {
        return returnToMenuButton;
    }

    public TourButton getTourButton() {
        return tourButton;
    }

    public Tour getTour() {
        return this.tourStockee;
    }

    public void setTour(Tour tour) {
        this.tourStockee = tour;
    }

}
