package Effets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Entités.Tour;

public class AttaqueAmelioree extends Bonus {

    public AttaqueAmelioree() {
        this.nom = "Attaque améliorée";
        this.prix = 1000;
        this.description = "Les tours infligent 20% de dégâts supplémentaires";

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\bonusattaque.gif")));
        // JLabel label = new JLabel(sprite);
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public void appliquerBonus(Tour t) {
        t.setDegats(t.getDegats() * 1.2);
    }

}
