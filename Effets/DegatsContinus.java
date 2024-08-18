package Effets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Entités.Ennemi;

public class DegatsContinus extends Bonus {

    public DegatsContinus() {
        this.nom = "Dégâts continus";
        this.prix = 3500;
        this.description = "Les ennemis subissent 100 points de dégâts toutes les 10 secondes";

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\bonusdegatscontinus.gif")));
        // JLabel label = new JLabel(sprite);
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);

    }

    public void appliquerBonus(Ennemi e) {
        e.setPointsDeVie(e.getPointsDeVie() - 100);
    }

}
