package Entités;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class Poltergeist extends Ennemi {

    // ennemi de base avec dégâts basiques

    public Poltergeist() {
        this.pointsDeVie = 200;
        this.vitesseDAttaque = 3000; // en ms
        this.degatsInfliges = 20;
        this.portee = 1; // portée en nombre de cases à gauche
        // this.vitesseDeplacement = 1;
        this.vitesseDeplacement = 5000; // en ms
        this.amesGagnees = 40; // nombre d'âmes gagnées par le joueur une fois l'ennemi tué

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\Poltergeist.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void attaque (Tour t, Plateau plateau) {
        // System.out.println("Tour attaque " + t.getPointsDeVie());
        Random r = new Random();
        int uneChanceSur24 = r.nextInt(1, 25);
        if (uneChanceSur24 == 1) {
            t.setPointsDeVie(t.getPointsDeVie() - this.degatsInfliges * 1.5);
            // System.out.println("Tour attaque " + t.getPointsDeVie());
        } else {
            t.setPointsDeVie(t.getPointsDeVie() - this.degatsInfliges);
            // System.out.println("Tour attaque " + t.getPointsDeVie());
        }
    }

}
