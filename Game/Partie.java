package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import AffichePlateau.Game;
import Design.MyFont;
import Effets.Bonus;

public class Partie extends JPanel {

    private Main window;
    private String niveauPartie;
    private String typePartie;
    private Joueur player; // Instance de la classe Joueur pour stocker les informations du joueur
    private JLabel title;
    private JPanel panel = new JPanel();
    private int lvl;

    public Partie(Main window, Joueur player, String difficulte, String typeGame, int lvl) {

        this.player = player;
        this.window = window;
        this.niveauPartie = difficulte;
        this.typePartie = typeGame;
        this.title = setLabel("Choisissez votre bonus :");
        this.panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        this.title.setBounds(500, 150, 500, 50);
        title.setFont(MyFont.getWhispersFont(45));
        this.panel.add(title);

        this.lvl = lvl;
        JPanel bonus = inibonus(player.getBonusPossedes());
        bonus.setBackground(Color.RED);
        this.panel.add(bonus);

        JButton play = new JButton("Play");
        play.setBounds(1050, 650, 100, 30);
        this.panel.add(play);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchGame();
            }
        });
        this.panel.add(play);

        JButton retour = new JButton("Retour");
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
     
                if (typePartie.equals("histoire")){
                    window.replacePanel(window.getHistoirePanel(), "Histoire");
                } else {
                    window.replacePanel(window.getNormalPanel(), "Normal");
                }
            }
        });
        retour.setBounds(50, 10, 100, 30);
        this.panel.add(retour);

    }

    private void launchGame() {
        Game game = new Game(window, player, niveauPartie, typePartie, lvl);
        game.start();
        window.replacePanel(game.getMainPanel(), "Game");
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel inibonus(ArrayList<Bonus> items) {
        JPanel bonusPanel = new JPanel(null);
        bonusPanel.setBackground(Color.BLACK);
        bonusPanel.setBounds(0, 150, 1200, 350);

        int x = 5;
        int y = 25;
        int w = 160;
        int h = 250;
        for (Bonus item : items) {
            // Create a panel for each item
            JPanel itemPanel = item.afficheBonus(x, y, w, h, item);
            itemPanel.setBackground(Color.BLACK);
            JButton button = new JButton("Choisir");
            button.setBounds(5, 220, 150, 25);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player.setBonusChoisi(item);
                    button.setText("Bonus choisi");
                }
            });

            itemPanel.add(button);

            // Add the item panel to the main panel
            bonusPanel.add(itemPanel);

            // espace les bonus
            x += 169;
        }

        return bonusPanel;
    }

    private JLabel setLabel(String s) {
        JLabel title = new JLabel();
        title.setBounds(200, 250, 300, 50);
        title.setText(s);
        Font titleFont = new Font("Arial", Font.BOLD, 25);
        title.setFont(titleFont);
        return title;
    }

    // Méthode pour récupérer le joueur après la fermeture de la fenêtre
    public Joueur getPlayer() {
        return player;
    }

    private void updateBonus (){

    }

}
