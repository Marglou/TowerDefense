package Entités;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class Yurei extends Ennemi {

    // paralyse pendant 10000 ms la tour qui le tue
    // dégats basiques

    public Yurei() {
        this.pointsDeVie = 320;
        this.vitesseDAttaque = 4000; // en ms
        this.degatsInfliges = 50;
        this.portee = 1; // portée en nombre de cases à gauche
        this.vitesseDeplacement = 5000;
        this.amesGagnees = 50; // nombre d'âmes gagnées par le joueur une fois l'ennemi tué

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\Yurei.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void attaquer(Tour t, Plateau p) {
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
