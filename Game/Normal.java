package Game;

import javax.swing.*;

import Design.MyButtons;

import java.awt.*;

public class Normal extends Page {

    private Joueur joueur;

    public Normal(Main window, Joueur joueur) {
        super();
        this.joueur = joueur;
        initialize();
        addComponents(window);
        addListeners(window);
        addDefaultComponents(window);
    }

    @Override
    public void initialize() {
        panel.setLayout(null);
    }

    @Override
    public void addComponents(Main window) {
        JPanel centerJPanel = new JPanel(new GridBagLayout());
        centerJPanel.setBounds(350, 100, 500, 450);
        centerJPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // Vertical spacing

        int buttonWidth = 200;
        int buttonHeight = 40;

        JButton partieFacile = MyButtons.initButton("Partie Facile", buttonWidth, buttonHeight);
        JButton partieMoyenne = MyButtons.initButton("Partie Moyenne", buttonWidth, buttonHeight);
        JButton partieDifficile = MyButtons.initButton("Partie Difficile", buttonWidth, buttonHeight);
        JButton marathon = MyButtons.initButton("Marathon", buttonWidth, buttonHeight);

        centerJPanel.add(partieFacile, gbc);
        gbc.gridy++;
        centerJPanel.add(partieMoyenne, gbc);
        gbc.gridy++;
        centerJPanel.add(partieDifficile, gbc);
        gbc.gridy++;
        centerJPanel.add(marathon, gbc);

        panel.add(centerJPanel);
        panel.setBackground(Color.BLACK);

        partieFacile.addActionListener(e -> {
            Partie fPartie = new Partie(window, joueur, "facile", "normal", -1);
            window.replacePanel(fPartie.getPanel(), "");
        });

        partieMoyenne.addActionListener(e -> {
            Partie fPartie = new Partie(window, joueur, "moyen", "normal", -1);
            window.replacePanel(fPartie.getPanel(), "");
        });

        partieDifficile.addActionListener(e -> {
            Partie fPartie = new Partie(window, joueur, "difficile", "normal", -1);
            window.replacePanel(fPartie.getPanel(), "");
        });

        marathon.addActionListener(e -> {
            Partie fPartie = new Partie(window, joueur, "marathon", "marathon", -1);
            window.replacePanel(fPartie.getPanel(), "");
        });
    }

    @Override
    public void addListeners(Main window) {
        // Add any additional listeners as needed
    }
}
