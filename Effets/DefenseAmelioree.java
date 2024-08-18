package Effets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Entités.Tour;

public class DefenseAmelioree extends Bonus {

    public DefenseAmelioree() {
        this.nom = "Défense améliorée";
        this.prix = 1000;
        this.description = "Les tours ont 20% de points de vie supplémentaires";

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\bonusdefense.gif")));
        // JLabel label = new JLabel(sprite);
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public void appliquerBonus(Tour t) {
        t.setPointsDeVie(t.getPointsDeVie() * 1.2);
    }

}
