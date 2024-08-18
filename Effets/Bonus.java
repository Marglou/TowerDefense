package Effets;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Entités.Ennemi;
import Entités.Tour;

public abstract class Bonus implements Serializable {

    protected String nom;
    protected int prix;
    protected String description;
    protected boolean estAcheté = false;
    protected transient ImageIcon sprite;
    protected transient JLabel spriteContainer;

    public void appliquerBonus(Tour t) {
        this.appliquerBonus(t);
    }

    public void appliquerBonus(Ennemi e) {
        this.appliquerBonus(e);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEstAcheté() {
        return estAcheté;
    }

    public void setEstAcheté(boolean estAcheté) {
        this.estAcheté = estAcheté;
    };

    public ArrayList<JTextArea> infoBonus(Bonus item) {
        ArrayList<JTextArea> info = new ArrayList<JTextArea>();
        info.add(new JTextArea(item.getNom()));
        info.add(new JTextArea("Description : " + item.getDescription()));
        info.add(new JTextArea("Prix : " + item.getPrix()));
        return info;
    }

    public JPanel afficheBonus(int x, int y, int w, int h, Bonus item) {
        JPanel itemPanel = new JPanel(null);
        itemPanel.setBackground(new Color(25, 125, 150));
        itemPanel.setBounds(x, y, w, h);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // // Assuming ShopItem has a getSprite() method returning ImageIcon
        ImageIcon itemSprite = item.getSprite();
        // Create a label to display the item sprite
        JLabel itemLabel = new JLabel(itemSprite);
        itemLabel.setBounds(25, 5, 100, 100);
        itemPanel.add(itemLabel);

        int yBis = 100;
        for (JTextArea info : infoBonus(item)) {
            info.setBounds(5, yBis, 150, 45);
            info.setEditable(false);
            info.setLineWrap(true);
            info.setWrapStyleWord(true);
            info.setOpaque(false);
            info.setForeground(Color.WHITE);
            yBis += 45;
            itemPanel.add(info);
        }

        return itemPanel;
    }

    public ImageIcon getSprite() {
        return this.sprite;
    }

    public JLabel getSpriteContainer() {
        return this.spriteContainer;
    }

}