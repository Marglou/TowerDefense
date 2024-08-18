package Game;

import javax.swing.*;

import Design.MyFont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends Page {

    private enum HomeButtons {
        MODE_DE_JEU("Mode De Jeu"),
        SHOP("Shop"),
        SETTINGS("Settings"),
        REGLES("Regles"),
        QUITTER("Quitter");

        private final String label;

        HomeButtons(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    private ImageIcon sprite = new ImageIcon(this.getClass().getResource("..\\Res\\logo.gif"));
    private JLabel spriteContainer;

    public HomePage(Main window) {
        super();
        initialize();
        addComponents(window);
        addListeners(window);
    }

    @Override
    public void initialize() {
        panel.setLayout(new GridBagLayout());
        this.spriteContainer = new JLabel(this.sprite);
        spriteContainer.setBounds(10, 10, 200, 200);
        panel.setBackground(Color.BLACK);
        panel.add(spriteContainer);
    }

    @Override
    public void addComponents(Main window) {
        // Créer un JPanel pour les boutons avec un GridBagLayout
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setOpaque(false);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0); // Espacement vertical entre les boutons
    
        // Ajouter le logo devant les boutons
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.NORTH; // Aligner en haut
        constraints.fill = GridBagConstraints.NONE;
        buttonsPanel.add(spriteContainer, constraints);
    
        // Réinitialiser les contraintes pour les boutons
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
    
        for (HomeButtons buttonEnum : HomeButtons.values()) {
            JButton button = createMenuButton(buttonEnum.getLabel());
            button.setBackground(Color.RED);
            button.setForeground(Color.BLACK);
            button.setFont(MyFont.getBrushFont(25));
            button.setPreferredSize(new Dimension(200, 40));
    
            if (buttonEnum == HomeButtons.QUITTER) {
                button.addActionListener(e -> System.exit(0));
            } else if (buttonEnum == HomeButtons.MODE_DE_JEU) {
                button.addActionListener(e -> window.replacePanel(window.getModeDeJeuPanel(), "Mode De Jeu"));
            } else if (buttonEnum == HomeButtons.SHOP) {
                button.addActionListener(e -> window.replacePanel(window.getShopPanel(), "Shop"));
            } else if (buttonEnum == HomeButtons.SETTINGS) {
                button.addActionListener(e -> window.replacePanel(window.getSettingsPanel(), "Settings"));
            } else if (buttonEnum == HomeButtons.REGLES){
                button.addActionListener(e -> window.replacePanel(window.getReglePanel(), "Regle"));
            }
    
            constraints.gridy++;
            buttonsPanel.add(button, constraints);
        }
    
        // Ajouter les boutons au JPanel principal
        panel.add(buttonsPanel);
    }
    

    @Override
    public void addListeners(Main window) {
    }

}
