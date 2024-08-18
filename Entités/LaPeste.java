package Entités;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class LaPeste extends Ennemi {

    // ennemi très puissant, bcp de pv, lent
    // 1/5 de détruire la tour en un coup

    public LaPeste() {
        this.pointsDeVie = 3000;
        this.vitesseDAttaque = 4000; // en ms
        this.portee = 1; // portée à gauche
        this.degatsInfliges = 300;
        this.vitesseDeplacement = 1;
        this.amesGagnees = 600;

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\LaPeste.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void attaque (Tour t, Plateau plateau) {
        Random r = new Random();
        int oneShot = r.nextInt(1, 6);
        if (oneShot == 1) {
            t.setPointsDeVie(0);
            t.setEstVivant(false);
        } else {
            t.setPointsDeVie(t.getPointsDeVie() - this.degatsInfliges);
        }
    }

}
