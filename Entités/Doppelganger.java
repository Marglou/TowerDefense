package Entités;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class Doppelganger extends Ennemi {

    // dégâts importants

    public Doppelganger() {
        this.pointsDeVie = 720;
        this.degatsInfliges = 90; // 10 pour les dégats de zone
        this.portee = 1; // portée à gauche
        this.amesGagnees = 280;
        this.vitesseDAttaque = 3000; // en ms
        this.vitesseDeplacement = 1;

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\Doppelganger.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void attaque(Tour t, Plateau plateau) {
        Random r = new Random();
        int uneChanceSur24 = r.nextInt(1, 25);
        if (uneChanceSur24 == 1) {
            t.setPointsDeVie(t.getPointsDeVie() - this.degatsInfliges * 1.5);
            ;
        } else {
            t.setPointsDeVie(t.getPointsDeVie() - this.degatsInfliges);
        }
    }

}
