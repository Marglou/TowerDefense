package Design;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class MyButtons {

    // ------ Bouton ------
    public static JButton initButton(String name, int x, int y, int width, int height, Color color) {
        JButton button = new JButton(name);
        button.setBounds(x, y, width, height);
        button.setBackground(color);
        return button;
    }

    public static JButton initButton(String name, int x, int y, int width, int height, Color colorBack,
            Color colorText) {
        JButton button = new JButton(name);
        button.setBounds(x, y, width, height);
        button.setBackground(colorBack);
        button.setForeground(colorText);
        return button;
    }

    public static JButton initButton(String name, int x, int y, int width, int height) {
        JButton button = new JButton(name);
        button.setBounds(x, y, width, height);
        return button;
    }

    public static JButton initButton(String name, int width, int height) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(width, height));
        button.setForeground(Color.BLACK);
        button.setBackground(Color.RED);
        button.setFont(MyFont.getWhispersFont(30));
        return button;
    }

    // ------ Bouton rond ------
    public static JButton initRoundButton(String name, int x, int y, int diameter, Color color) {
        RoundButton roundButton = new RoundButton(name);
        roundButton.setBounds(x, y, diameter, diameter);
        roundButton.setBackground(color);
        return roundButton;
    }

    public static JButton initRoundButton(String name, int x, int y, int diameter) {
        RoundButton roundButton = new RoundButton(name);
        roundButton.setBounds(x, y, diameter, diameter);
        // roundButton.setBorder(new RoundedBorder(10)); // Utilisez la classe
        // RoundedBorder pour les bordures arrondies
        return roundButton;
    }

    // ------ JLabel ------
    public static JLabel iniLabel(String name, int x, int y, int width, int height, Color color) {
        JLabel button = new JLabel(name);
        button.setBounds(x, y, width, height);
        button.setBackground(color);
        return button;
    }

    public static JLabel iniLabel(String name, int x, int y, int width, int height, Color colorBack, Color colorText) {
        JLabel button = new JLabel(name);
        button.setBounds(x, y, width, height);
        button.setBackground(colorBack);
        button.setForeground(colorText);
        return button;
    }

    public static JLabel iniLabel(String name, int x, int y, int width, int height, Font font) {
        JLabel button = new JLabel(name);
        button.setBounds(x, y, width, height);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(font);
        return button;
    }

    public static JLabel iniLabel(String name, int x, int y, int width, int height) {
        JLabel button = new JLabel(name);
        button.setBounds(x, y, width, height);
        return button;
    }

    // Classe interne statique pour créer un bouton rond
    public static class RoundButton extends JButton {

        public RoundButton(String label) {
            super(label);

            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);

            // addActionListener(new ActionListener() {
            // @Override
            // public void actionPerformed(ActionEvent e) {
            // // System.out.println("Bouton rond cliqué!");
            // }
            // });
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(Color.lightGray);
            } else {
                g.setColor(getBackground());
            }

            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

            super.paintComponent(g);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }
    }
}
