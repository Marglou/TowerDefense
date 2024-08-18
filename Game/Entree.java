package Game;

import javax.swing.*;
import Design.MyFont;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Entree extends JFrame {

    private Joueur joueur;
    private JLabel title;
    private JTextField textField;
    private JButton nouvellePartie;
    private JButton partieCharge;

    public Entree() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setLocationRelativeTo(null);

        // Panneau pour le fond d'écran avec le sprite
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon sprite = new ImageIcon(this.getClass().getResource("..\\Res\\fond.gif"));
                g.drawImage(sprite.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

        // Modifier la police du titre
        title = new JLabel("Entrez votre pseudo :");
        title.setFont(MyFont.getWhispersFont(75));
        title.setForeground(Color.BLACK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Champ de texte
        textField = new JTextField(20);
        textField.setMaximumSize(new Dimension(330, 30));
        textField.setBackground(Color.black);
        textField.setForeground(Color.RED);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nouveaux boutons
        nouvellePartie = new JButton("Nouvelle Partie");
        nouvellePartie.setFont(MyFont.getCreepyFont(10));
        nouvellePartie.setBackground(Color.RED);
        nouvellePartie.setForeground(Color.BLACK);
        nouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        partieCharge = new JButton("Charger Partie");
        partieCharge.setFont(MyFont.getCreepyFont(10));
        partieCharge.setBackground(Color.BLACK);
        partieCharge.setForeground(Color.RED);
        partieCharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });

        // Ajouter les composants au panneau d'arrière-plan
        backgroundPanel.add(Box.createHorizontalStrut(250)); // Espace verticalement flexible
        backgroundPanel.add(title);
        backgroundPanel.add(Box.createVerticalStrut(20)); // Espace verticalement flexible
        backgroundPanel.add(textField);
        backgroundPanel.add(Box.createVerticalStrut(20)); // Espace verticalement flexible
        backgroundPanel.add(nouvellePartie);
        backgroundPanel.add(Box.createVerticalStrut(20)); // Espace verticalement flexible
        backgroundPanel.add(partieCharge);
        backgroundPanel.add(Box.createVerticalGlue()); // Espace verticalement flexible

        // Ajouter le panneau d'arrière-plan à la fenêtre principale
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    private void startNewGame() {
        String name = textField.getText();
        if (!name.isEmpty()) {
            joueur = new Joueur(name);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nom valide.");
        }
    }

    private void loadGame() {
        String name = textField.getText();
        if (!name.isEmpty()) {
            // Charger le joueur existant
            Joueur joueurExistant = Joueur.chargerJoueur();
            if (joueurExistant != null && joueurExistant.isPseudoIdentique(name)) {
                joueur = joueurExistant;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Aucune sauvegarde trouvée pour le joueur ou pseudo incorrect.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nom valide.");
        }
    }

    public Joueur getjoueur() {
        return joueur;
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         @Override
    //         public void run() {
    //             new Entree();
    //         }
    //     });
    // }
}
