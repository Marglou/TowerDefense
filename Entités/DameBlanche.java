package Entités;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class DameBlanche extends Ennemi {

    // dégâts modérés, taux critique de 1/3

    public DameBlanche() {
        this.pointsDeVie = 650;
        this.degatsInfliges = 60; // 10 pour les dégats de zone
        this.portee = 4; // portée à gauche
        this.amesGagnees = 230;
        this.vitesseDAttaque = 3000; // en ms
        // this.vitesseDeplacement = 1;

        // taux critique de 1/3 au lieu de 1/24

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\DameBlanche.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void attaque (Tour t, Plateau plateau) {
        Random r = new Random();
        int uneChanceSur3 = r.nextInt(1, 4);
        if (uneChanceSur3 == 1) {
            t.setPointsDeVie(t.getPointsDeVie() - this.degatsInfliges * 1.5);
            ;
        } else {
            t.setPointsDeVie(t.getPointsDeVie() - this.degatsInfliges);
        }
    }
}
