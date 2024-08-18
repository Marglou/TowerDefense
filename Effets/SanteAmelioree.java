package Effets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Entités.Tour;

public class SanteAmelioree extends Bonus {

    // Bonus récupéré une fois le 1er boss vaincu

    public SanteAmelioree() {
        this.nom = "Santé améliorée";
        this.prix = 100;
        this.description = "Toutes les tours ont 200 pv de plus.";

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\bonussante.gif")));
        // JLabel label = new JLabel(sprite);
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public void appliquerBonus(Tour t) {
        t.setPointsDeVie(t.getPointsDeVie() + 200);
    }

}
