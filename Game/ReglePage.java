package Game;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Design.MyButtons;
import Design.MyFont;
import Entités.Tour;

public class ReglePage extends Page {

    private Main window;

    public ReglePage(Main window) {
        super();
        this.window = window;
        initialize();
        addComponents(window);
        addListeners(window);
        addDefaultComponents(window);
    }

    @Override
    public void initialize() {
        // Utilisez un gestionnaire de disposition null pour positionner les composants
        // manuellement
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
    }

    @Override
    public void addComponents(Main window) {

        JLabel title = MyButtons.iniLabel("REGLES DU JEU", 500, 35, 500, 35, MyFont.getBrushFont(35));
        panel.add(title);

        JPanel regle = reglePanel();
        panel.add(regle);

        JPanel infoTour = infoTourPanel();
        panel.add(infoTour);

    }

    private JPanel reglePanel() {
        JPanel regle = new JPanel();
        regle.setBounds(0, 90, 1200, 300);
        regle.setOpaque(false);
        JTextArea regles = new JTextArea(
                "Ce jeu de Tower Defense consiste a placer des tours sur le plateau de jeu afin d'empecher les ennemis de traverser le terrain.\n\n "
                        +
                        " Vous pouvez les ameliorer grace aux ames obtenues au cours de la partie et en tuant des ennemis. Pour debloquer les tours et des bonus, vous devez gagner de l'or, "
                        +
                        " obtenable lors du mode histoire et de parties simples. \n Certaines tours ne sont débloquables qu'après avoir passé certains niveau du mode histoire. \n\n "
                        +
                        "Le mode marathon fait défiler des vagues d'ennemis infinies, cela correspond a votre meilleur score.");
        regles.setBounds(0, 0, 750, regle.getHeight());
        regles.setOpaque(false);
        regles.setForeground(Color.WHITE);
        regles.setFont(MyFont.getWhispersFont(22));
        regles.setEditable(false);
        regles.setLineWrap(true);
        regle.add(regles);
        return regle;
    }

    private JPanel infoTourPanel () {
        JPanel tourPanel = new JPanel(null);
        tourPanel.setBackground(new Color(0, 0, 230));
        tourPanel.setBounds(0, 350, window.getWidth(), 300);

        int x = 5;
        int y = 20;
        int w = 160;
        int h = 250;
        for (Tour item : window.getTowers()) {
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

            JTextArea description = new JTextArea(item.getDescription());
            description.setBounds(5, 170, 150, 50);
            description.setForeground(Color.WHITE);
            description.setOpaque(false);
            description.setEditable(false);
            description.setLineWrap(true);
            itemPanel.add(description);

            // Add the item panel to the main panel
            tourPanel.add(itemPanel);

            // espace les tours
            x += 169;
        }

        return tourPanel;
    }

    private ArrayList<JLabel> infoTour(Tour item) {
        ArrayList<JLabel> info = new ArrayList<JLabel>();
        info.add(new JLabel(item.getClass().getSimpleName()));
        info.add(new JLabel("Dégâts : " + item.getDegats()));
        info.add(new JLabel("Pv : " + item.getPointsDeVie()));
        return info;
    }

    @Override
    public void addListeners(Main window) {
        // Ajoutez des écouteurs d'événements si nécessaire
    }

}
