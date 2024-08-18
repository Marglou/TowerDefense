package Effets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Entités.Tour;

public class CritiqueAccrue extends Bonus {

    // Bonus récupéré une fois le 2e boss vaincu

    public CritiqueAccrue() {
        this.nom = "Critique accrue";
        this.prix = 500;
        this.description = "Taux crtitque de TOUTES les tours augmenté de 20%";

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\bonuscritique.gif")));
        // JLabel label = new JLabel(sprite);
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public void appliquerBonus(Tour t) {
        t.setCoupCritique(t.getCoupCritique() * 1.2);
    }

}
