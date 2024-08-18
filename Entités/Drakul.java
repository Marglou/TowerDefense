package Entités;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class Drakul extends Ennemi {

    // ennemi très puissant, très rapide
    // taux critique de 1/16

    public Drakul() {
        this.pointsDeVie = 1700;
        this.vitesseDAttaque = 2000; // en ms
        this.portee = 1; // portée à gauche
        this.degatsInfliges = 400;
        this.vitesseDeplacement = 2;
        this.amesGagnees = 500;

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\Drakul.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void attaque (Tour t, Plateau plateau) {
        Random r = new Random();
        int uneChanceSur16 = r.nextInt(1, 17);
        if (uneChanceSur16 == 1) {
            t.setPointsDeVie(t.getPointsDeVie() - this.degatsInfliges * 1.5);
            ;
        } else {
            t.setPointsDeVie(t.getPointsDeVie() - this.degatsInfliges);
        }
    }

}
