package Game;

import javax.swing.*;

import Effets.Bonus;
import Entités.Tour;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SettingsPage extends Page {

    private Joueur joueur;
    JTextArea name;
    JTextArea niveau;
    JTextArea or;
    JTextArea score;
    JTextArea nbTour;
    JTextArea nbBonus;
    JPanel bonusPanel;
    JPanel toursPanel;

    public SettingsPage(Main window, Joueur joueur) {
        super();
        this.joueur = joueur;
        initialize();
        addComponents(window);
        addListeners(window);
        addDefaultComponents(window);
    }

    @Override
    public void initialize() {
        // Utilisez un BorderLayout pour disposer les composants de manière flexible
        panel.setLayout(new BorderLayout());
    }

    @Override
    public void addComponents(Main window) {
        // Créer un panneau pour centrer infoJoueur
        JPanel centerInfoJoueurPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton sauvegarde = createMenuButton("Sauvegarder");
        sauvegarde.setBackground(Color.RED);
        sauvegarde.setForeground(Color.BLACK);
        sauvegarde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joueur.sauvegarderJoueur("Game/sauvegarde.ser");
            }
        });
        centerInfoJoueurPanel.add(sauvegarde);
        centerInfoJoueurPanel.setBackground(Color.BLACK);
        // Créer un panneau pour les informations du joueur
        JPanel infoJoueur = new JPanel();
        infoJoueur.setOpaque(false);
        infoJoueur.setLayout(new BoxLayout(infoJoueur, BoxLayout.Y_AXIS));

        this.name = createStyledTextArea("Pseudo : " + joueur.getName(), Font.PLAIN, 20);
        this.niveau = createStyledTextArea("Niveau : " + String.valueOf(joueur.getLvl()), Font.PLAIN, 16);
        this.score = createStyledTextArea("Meilleur Score : " + joueur.getMeilleurScore(), Font.PLAIN, 16);
        this.or = createStyledTextArea("Or possédé : " + String.valueOf(joueur.getGold()), Font.PLAIN, 16);
        this.nbTour = createStyledTextArea("Nombre de tours : " + String.valueOf(joueur.getToursPossedes().size()),
                Font.PLAIN, 16);
        this.nbBonus = createStyledTextArea("Nombre de Bonus : " + String.valueOf(joueur.getBonusPossedes().size()),
                Font.PLAIN, 16);

        infoJoueur.add(name);
        infoJoueur.add(niveau);
        infoJoueur.add(score);
        infoJoueur.add(or);
        infoJoueur.add(nbTour);
        infoJoueur.add(nbBonus);

        // Ajouter un espace vertical entre chaque composant
        infoJoueur.add(Box.createVerticalStrut(20));

        // Ajouter le panneau infoJoueur centré au panneau de centrage
        centerInfoJoueurPanel.add(infoJoueur);

        // Ajouter le panneau de centrage au haut de la fenêtre
        panel.add(centerInfoJoueurPanel, BorderLayout.NORTH);

        // Créer deux panneaux supplémentaires pour les bonus et les tours
        this.bonusPanel = inibonus(joueur.getBonusPossedes());

        this.toursPanel = createToursPanel(joueur);

        // Ajouter les panneaux bonus et tours au centre de la fenêtre
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2));
        centerPanel.add(bonusPanel);
        centerPanel.add(toursPanel);

        // Ajouter le centrePanel au centre de la fenêtre
        panel.add(centerPanel, BorderLayout.CENTER);

        // Ajouter le bouton Shop en bas des panneaux Tour et Bonus
        JButton shopButton = createMenuButton("Shop");
        shopButton.setBackground(Color.BLACK);
        shopButton.setForeground(Color.RED);
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ajouter l'action pour passer à la boutique ici
                // window.switchToPage("Shop");
                window.replacePanel(window.getShopPanel(), "Shop");
            }
        });

        // Ajouter le bouton Shop au sud de la fenêtre
        panel.add(shopButton, BorderLayout.SOUTH);
    }

    private JPanel inibonus(ArrayList<Bonus> items) {
        JPanel bonusPanel = new JPanel(null);
        bonusPanel.setBackground(Color.RED);
        bonusPanel.setBounds(0, 150, 1200 / 2, 350);
        int x = 5;
        int y = 15;
        int w = 160;
        int h = 250;
        for (Bonus item : items) {
            // Create a panel for each item
            JPanel itemPanel = item.afficheBonus(x, y, w, h, item);
            itemPanel.setBackground(Color.black);
            // Add the item panel to the main panel
            bonusPanel.add(itemPanel);
            bonusPanel.add(Box.createHorizontalStrut(10));

            // espace les bonus
            x += 169;
        }
        return bonusPanel;
    }

    private JPanel createToursPanel(Joueur joueur) {
        JPanel toursPanel = new JPanel();
        toursPanel.setLayout(new BoxLayout(toursPanel, BoxLayout.Y_AXIS));
        toursPanel.setBackground(new Color(25, 150, 150)); // Set background color

        // Example: Add a label to display owned towers
        JLabel labelTours = new JLabel("Tours Possédées:");
        toursPanel.add(labelTours);

        // Add components for the owned towers
        for (Tour tour : joueur.getToursPossedes()) {
            // Assuming Tour class has a getSprite() method returning ImageIcon
            ImageIcon tourSprite = tour.getSprite();

            // Create a panel for each tower
            JPanel towerPanel = new JPanel();
            towerPanel.setLayout(new BoxLayout(towerPanel, BoxLayout.Y_AXIS));
            towerPanel.setBackground(new Color(25, 150, 150)); // Set background color

            // Create a label to display the tower name
            JLabel tourNameLabel = new JLabel(tour.getName());
            tourNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            towerPanel.add(tourNameLabel);

            // Create a label to display the tower sprite
            JLabel tourLabel = new JLabel(tourSprite);
            tourLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            towerPanel.add(tourLabel);

            // Add some spacing between towers
            towerPanel.add(Box.createVerticalStrut(10));

            // Add the tower panel to the main panel
            toursPanel.add(towerPanel);
        }
        return toursPanel;
    }

    @Override
    public void addListeners(Main window) {
        // Ajoutez des écouteurs d'événements si nécessaire
    }

    @Override
    public void addDefaultComponents(Main window) {
        JButton retourButton = createMenuButton("Retour");
        retourButton.setBackground(Color.BLACK);
        retourButton.setForeground(Color.RED);
        panel.add(retourButton, BorderLayout.WEST);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vous pouvez ajuster cela en fonction de votre besoin par défaut pour le
                // bouton "Retour"
                // window.switchToPage("Home");
                window.replacePanel(window.getHomePanel(), "Home");
            }
        });
    }

    protected JLabel createStyledLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", style, size));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    protected JTextArea createStyledTextArea(String text, int style, int size) {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("Arial", style, size));
        textArea.setForeground(Color.WHITE);
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        return textArea;
    }

    // Méthode pour mettre à jour les informations du joueur
    public void updateSettingsPage() {
        // Mettez à jour les composants d'information du joueur
        this.name.setText("Pseudo : " + joueur.getName());
        this.score.setText("Meilleur Score : " + joueur.getMeilleurScore());
        this.niveau.setText("Niveau : " + joueur.getLvl());
        this.or.setText("Or possédé : " + joueur.getGold());
        this.nbTour.setText("Nombre de tours : " + String.valueOf(joueur.getToursPossedes().size()));
        this.nbBonus.setText("Nombre de Bonus : " + String.valueOf(joueur.getBonusPossedes().size()));

        // Mettez à jour le panneau bonus
        updateBonusPanel();

        // Mettez à jour le panneau tours
        updateToursPanel();

        // Actualisez l'interface utilisateur
        panel.revalidate();
        panel.repaint();
    }

    private void updateBonusPanel() {
        // Mettez à jour le contenu du panneau bonus
        bonusPanel.removeAll();

        JLabel labelBonus = new JLabel("Bonus Possédés:");
        labelBonus.setForeground(Color.BLACK);
        labelBonus.setBounds(150, 5, 100, 20);
        bonusPanel.add(labelBonus);
        int y = 15;
        for (Bonus bonus : joueur.getBonusPossedes()) {
            JLabel bonusLabel = new JLabel(bonus.getNom());
            bonusLabel.setForeground(Color.BLACK);
            bonusLabel.setBounds(25, y, 100, 100);
            bonusPanel.add(bonusLabel);
            bonusLabel = bonus.getSpriteContainer();
            bonusLabel.setBounds(25, y, 100, 75);
            bonusPanel.add(bonusLabel);
            y += 75;
        }

        // Actualisez le panneau bonus
        bonusPanel.revalidate();
        bonusPanel.repaint();
    }

    private void updateToursPanel() {
        // Mettez à jour le contenu du panneau tours
        toursPanel.removeAll();

        JLabel labelTours = new JLabel("Tours Possédées:");
        toursPanel.add(labelTours);

        for (Tour tour : joueur.getToursPossedes()) {
            // Assuming Tour class has a getSprite() method returning ImageIcon
            ImageIcon tourSprite = tour.getSprite();

            // Create a panel for each tower
            JPanel towerPanel = new JPanel();
            towerPanel.setLayout(new BoxLayout(towerPanel, BoxLayout.Y_AXIS));
            towerPanel.setBackground(new Color(25, 150, 150));

            JLabel tourNameLabel = new JLabel(tour.getName());
            tourNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            towerPanel.add(tourNameLabel);

            JLabel tourLabel = new JLabel(tourSprite);
            tourLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            towerPanel.add(tourLabel);

            towerPanel.add(Box.createVerticalStrut(10));

            toursPanel.add(towerPanel);
        }

        // Actualisez le panneau tours
        toursPanel.revalidate();
        toursPanel.repaint();
    }
}
