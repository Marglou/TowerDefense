package Game;

import javax.swing.*;

import Design.MyButtons;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Histoire extends Page {

    private Joueur joueur;

    public Histoire(Main window, Joueur joueur) {
        super();
        this.joueur = joueur;
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
    }

    @Override
    public void addComponents(Main window) {
        // Ajoutez une image de fond
        ImageIcon backgroundImage = new ImageIcon("path/to/your/background_image.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, window.getWidth(), window.getHeight());
        panel.setBackground(Color.BLACK);
        panel.add(backgroundLabel);

        // Ajoutez des boutons pour représenter les niveaux de jeu
        int numLevels = 7; // Choisissez le nombre de niveaux que vous souhaitez afficher
        int buttonWidth = 100;
        int spacing = 120;

        for (int i = 1; i < numLevels; i++) {
            String level = String.valueOf(i);
            JButton levelButton = MyButtons.initRoundButton(level, spacing, 200, 35);
            if (i > joueur.getLvl()) {
                levelButton.setBackground(Color.GRAY);
            } else {
                levelButton.setBackground(Color.YELLOW);
            }
            levelButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            levelButton.addActionListener(new LevelButtonListener(i, window)); // Ajoutez un auditeur pour le bouton
            panel.add(levelButton);
            spacing += buttonWidth + 50;
        }
    }

    @Override
    public void addListeners(Main window) {
        // Ajoutez des écouteurs d'événements si nécessaire
    }

    private class LevelButtonListener implements ActionListener {
        private int level;
        private Main window;

        public LevelButtonListener(int level, Main window) {
            this.level = level;
            this.window = window;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Ajoutez la logique pour passer au niveau sélectionné
            String name;
            if (level <= 2) {
                name = "facile";
            } else if (level <= 4) {
                name = "moyen";
            } else {
                name = "difficile";
            }

            if (level <= joueur.getLvl()) {
                switch (name) {
                    case "facile":
                        // lancer une partie facile
                        Partie partieFacile = new Partie(window, joueur, name, "histoire", level);
                        JPanel panel = partieFacile.getPanel();
                        window.replacePanel(panel, "");
                        break;
                    case "moyen":
                        // lancer une partie moyenne
                        Partie partieMoyenne = new Partie(window, joueur, name, "histoire", level);
                        window.replacePanel(partieMoyenne.getPanel(), "");
                        break;
    
                    case "difficile":
                        // lancer une partie difficile
                        Partie partieDifficile = new Partie(window, joueur, name, "histoire", level);
                        window.replacePanel(partieDifficile.getPanel(), "");
                        break;
    
                    default:
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas encore débloqué ce niveau !", "Niveau verrouillé",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateHistoire() {
        // Mettez à jour l'apparence des boutons en fonction du niveau actuel du joueur
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton levelButton = (JButton) component;
        
                try {
                    int level = Integer.parseInt(levelButton.getText());
        
                    if (level <= joueur.getLvl()) {
                        levelButton.setBackground(Color.YELLOW);
                    } else {
                        levelButton.setBackground(Color.GRAY);
                    }
                } catch (NumberFormatException e) {
                    // Gérez ici le cas où le texte du bouton n'est pas un nombre
                    // Peut-être afficher un message d'erreur ou ignorer ce bouton, selon vos besoins.
                    levelButton.setBackground(Color.RED); // Ou une autre couleur indiquant une erreur
                }
            }
        }
        
    }
}
