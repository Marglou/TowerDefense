package Game;

import javax.swing.*;

import Design.MyFont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Page {
    protected JPanel panel;
    protected JButton retourButton;
    protected Main window;

    public Page() {
        panel = new JPanel();
        initialize();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void initialize() {
        panel.setLayout(null);
        // addDefaultComponents(window);
    }

    public void addDefaultComponents(Main window) {
        retourButton = createMenuButton("Retour");
        // Placer le bouton "Retour" au bas et au centre du panel
        retourButton.setBounds(10, 10, 100, 25);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vous pouvez ajuster cela en fonction de votre besoin par défaut pour le bouton "Retour"
                // window.switchToPage("Home");
                window.replacePanel(window.getHomePanel(), "Home");
            }
        });
        panel.add(retourButton);
    }

    public void addComponents(Main homePage) {
        // Ajoutez des composants spécifiques à la page ici
    }

    public void addListeners(Main homePage) {
        // Ajoutez des écouteurs spécifiques à la page ici
    }

    protected JButton createMenuButton(String label) {
        JButton button = new JButton(label);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setFont(MyFont.getWhispersFont(25));
        return button;
    }


}
