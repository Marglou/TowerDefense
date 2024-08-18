package Game;

import javax.swing.*;

import Design.MyFont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Entités.Tour;
import Effets.Bonus;

public class ShopPage extends Page {

    private Joueur joueur;
    protected ArrayList<Tour> towers;
    protected ArrayList<Bonus> bonus;
    private JLabel goldLabel;

    public ShopPage(Main window, Joueur joueur, ArrayList<Tour> towers, ArrayList<Bonus> bonus) {
        super();
        this.joueur = joueur;
        this.towers = towers;
        this.bonus = bonus;
        this.goldLabel = new JLabel("Or : " + joueur.getGold());
        goldLabel.setForeground(Color.WHITE);
        addComponents(window);
        addListeners(window);
        initialize();
        addDefaultComponents(window);

    }

    @Override
    public void initialize() {
        // Initialise la page (ajoute des composants, configure des mises en page, etc.)
        panel.setLayout(null);
    }

    @Override
    public void addComponents(Main window) {
        // Créer un panneau pour le haut de la boutique (accueil, bouton retour, or)
        JPanel topPanel = iniToPanel(window);

        // Ajouter le panneau du haut à la page
        panel.add(topPanel);
        panel.setBackground(Color.BLACK);

        // Créer un panneau pour les tours à acheter avec une barre de défilement
        JPanel towersPanel = createShopPanelTower(towers, window);
        panel.add(towersPanel);

        // // Créer un panneau pour les bonus à acheter avec une barre de défilement
        JPanel bonusPanel = createShopBonusPanel(bonus, window);
        panel.add(bonusPanel);
    }

    private JPanel iniToPanel(Main window) {
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, window.getWidth(), 125);
        topPanel.setBackground(Color.BLACK);
        JTextArea welcomeText = new JTextArea("Bienvenue dans la boutique.\n" +
                "Ici, vous pouvez acheter des améliorations et des équipements pour votre jeu.\n" +
                "Explorez notre sélection de tours et de ressources pour améliorer votre stratégie.\n");
        welcomeText.setEditable(false);
        welcomeText.setLineWrap(true);
        welcomeText.setWrapStyleWord(true);
        welcomeText.setBounds(350, 10, 600, 225);
        welcomeText.setOpaque(false);
        welcomeText.setFont(MyFont.getWhispersFont(25));
        welcomeText.setForeground(Color.WHITE);
        topPanel.add(welcomeText);

        // Ajouter le label affichant l'or du joueur
        this.goldLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.goldLabel.setBounds(1050, 10, 200, 25);
        topPanel.add(goldLabel);

        // Ajouter le bouton de retour
        JButton backButton = new JButton("Retour");
        backButton.setBounds(10, 10, 100, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // window.switchToPage("Home");
                window.replacePanel(window.getHomePanel(), "Home");
            }
        });
        topPanel.add(backButton);
        return topPanel;
    }

    private JPanel createShopPanelTower(ArrayList<Tour> items, Main window) {
        JPanel tourPanel = new JPanel(null);
        tourPanel.setBackground(new Color(255, 0, 0));
        tourPanel.setBounds(0, 125, window.getWidth(), 300);

        int x = 5;
        int y = 20;
        int w = 160;
        int h = 250;
        for (Tour item : items) {
            // Create a panel for each item
            JPanel itemPanel = new JPanel(null);
            // itemPanel.setBackground(new Color(25, 125, 150));
            itemPanel.setBackground(Color.BLACK);
            itemPanel.setBounds(x, y, w, h);
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            // Assuming ShopItem has a getSprite() method returning ImageIcon
            ImageIcon itemSprite = item.getSprite();

            // Create a label to display the item sprite
            JLabel itemLabel = new JLabel(itemSprite);
            itemLabel.setBounds(35, 5, 90, 100);
            itemPanel.add(itemLabel);

            int yBis = 115;
            for (JLabel info : infoTour(item)) {
                info.setBounds(5, yBis, 150, 25);
                info.setForeground(Color.WHITE);
                yBis += 15;
                itemPanel.add(info);
            }

            // Create a button to buy the item
            JButton buyButton = new JButton("Acheter");
            buyButton.putClientProperty("tour", item);

            String itemName = item.getClass().getSimpleName();
            if (itemName.equals("TourVindicta") || itemName.equals("TourCaemento") || itemName.equals("TourIanis")) {
                buyButton.setText("Battre un boss");
                buyButton.setEnabled(false);
            } else if (Main.possedeTour(joueur.getToursPossedes(), itemName)) {
                buyButton.setText("Déja Possédée");
                buyButton.setEnabled(false);
            }

            buyButton.setBounds(5, 220, 150, 25);
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Tour tourAchete = (Tour) buyButton.getClientProperty("tour");
                    // Vérifier si le joueur peut acheter la tour
                    if (joueur.acheterTourShop(tourAchete)) {
                        // Mise à jour de l'interface utilisateur
                        buyButton.setText("Déjà Possédée");
                        buyButton.setEnabled(false);

                        // Mise à jour du label gold
                        updateShopPage();
                        window.getSettingsPage().updateSettingsPage();
                    }

                }
            });

            itemPanel.add(buyButton);

            // Add the item panel to the main panel
            tourPanel.add(itemPanel);

            // espace les tours
            x += 169;
        }

        return tourPanel;
    }

    protected void updateShopPage() {
        // Mise à jour du label gold
        goldLabel.setText("Or : " + joueur.getGold());
        panel.revalidate();
        panel.repaint();

    }

    private ArrayList<JLabel> infoTour(Tour item) {
        ArrayList<JLabel> info = new ArrayList<JLabel>();
        info.add(new JLabel(item.getClass().getSimpleName()));
        info.add(new JLabel("Dégâts : " + item.getDegats()));
        info.add(new JLabel("Pv : " + item.getPointsDeVie()));
        info.add(new JLabel("Prix : " + item.getAchat()));
        return info;
    }

    private JPanel createShopBonusPanel(ArrayList<Bonus> items, Main window) {
        JPanel bonusPanel = new JPanel(null);
        // bonusPanel.setBackground(new Color(25, 225, 150));
        bonusPanel.setBackground(Color.RED);
        bonusPanel.setBounds(0, 440, window.getWidth(), 300);

        int x = 5;
        int y = 15;
        int w = 160;
        int h = 250;
        for (Bonus item : items) {
            // Create a panel for each item

            JPanel itemPanel = item.afficheBonus(x, y, w, h, item);
            itemPanel.setBackground(Color.BLACK);
            // Create a button to buy the item
            JButton buyButton = new JButton("Acheter");
            buyButton.putClientProperty("bonus", item);

            String itemName = item.getClass().getSimpleName();
            if (Main.possedeTour(joueur.getToursPossedes(), itemName)) {
                buyButton.setText("Déja Possédée");
                buyButton.setEnabled(false);
            }

            buyButton.setBounds(5, 220, 150, 25);
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Bonus bonusAchete = (Bonus) buyButton.getClientProperty("bonus");

                    // Vérifier si le joueur peut acheter la tour
                    if (joueur.acheterBonus(bonusAchete)) {
                        // Mise à jour de l'interface utilisateur
                        buyButton.setText("Déjà Possédée");
                        buyButton.setEnabled(false);

                        // Mise à jour du label gold
                        updateShopPage();
                        window.getSettingsPage().updateSettingsPage();

                    }
                }
            });
            itemPanel.add(buyButton);
            // Add the item panel to the main panel
            bonusPanel.add(itemPanel);
            // espace les bonus
            x += 169;
        }
        return bonusPanel;
    }

    @Override
    public void addListeners(Main window) {
        // Ajoutez des écouteurs d'événements si nécessaire
    }

    @Override
    public void addDefaultComponents(Main window) {
        // Ajoutez des composants par défaut si nécessaire
    }
}
