package Game;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeDeJeu extends Page {

    private ImageIcon sprite = new ImageIcon(this.getClass().getResource("..\\Res\\feufolletfond.gif"));
    private JLabel spriteContainer;

    public ModeDeJeu(Main window) {
        super();
        initialize();
        addComponents(window);
        addListeners(window);
        addDefaultComponents(window);
    }

    @Override
    public void initialize() {
        panel.setLayout(null);

        // Set the size of the panel
        panel.setSize(1200, 750);

        // Add the spriteContainer to the panel
        this.spriteContainer = new JLabel(this.sprite);
        spriteContainer.setBounds(0, 0, panel.getWidth(), panel.getHeight());
    }

    @Override
    public void addComponents(Main window) {
        // Utilisation d'un gestionnaire de disposition null pour centerPanel
        JPanel centerPanel = new JPanel(null);
        centerPanel.setOpaque(false);
        centerPanel.setBounds(495, 270, 500, 500);

        JButton histoireButton = createMenuButton("Histoire");
        JButton entrainementButton = createMenuButton("Entrainement");
        // JButton sansFin = createMenuButton("Sans Fin");

        histoireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // window.switchToPage("Histoire"); // Remplacez "HistoirePage" par le nom réel
                // de la page
                window.replacePanel(window.getHistoirePanel(), "Histoire");
            }
        });

        entrainementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // window.switchToPage("Normal"); // Remplacez "EntrainementPage" par le nom
                // réel de la page
                window.replacePanel(window.getNormalPanel(), "Normal");
            }

        });

        // Ajoutez les boutons à des positions spécifiques avec setBounds
        histoireButton.setBounds(0, 0, 200, 40);
        entrainementButton.setBounds(0, 55, 200, 40);

        centerPanel.add(histoireButton);
        centerPanel.add(entrainementButton);

        // Utilisation d'un gestionnaire de disposition BorderLayout pour "panel"
        panel.add(centerPanel);

        panel.add(spriteContainer);
    }

    @Override
    public void addListeners(Main window) {
        // Ajoutez les écouteurs supplémentaires au besoin
    }

}
