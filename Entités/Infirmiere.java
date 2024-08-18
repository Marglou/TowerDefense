package Entités;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class Infirmiere extends Ennemi {

    // dégâts basiques mais soigne tout les ennemis sur le terrain
    private int soinsAppliques;

    public Infirmiere() {
        this.pointsDeVie = 800;
        this.vitesseDAttaque = 3000; // en ms
        this.portee = 1; // portée à gauche, dégats de zone donc une case en haut et en bas de la touchée
        this.degatsInfliges = 50; // 10 pour les dégats de zone
        this.vitesseDeplacement = 1;
        this.amesGagnees = 380;
        this.soinsAppliques = 100;
        // soin de 100 pv toute les 8000ms à tous les ennemis sur le terrain

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\Infirmiere.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void attaque(Tour t, Plateau plateau) {
        Random r = new Random();
        int uneChanceSur24 = r.nextInt(0, 25);
        if (uneChanceSur24 == 1) {
            t.pointsDeVie = t.pointsDeVie - (int) (this.degatsInfliges * 1.5);
        } else {
            t.pointsDeVie -= this.degatsInfliges;
        }
        for (Ennemi e : plateau.getEnnemis()) {
            e.setPointsDeVie(e.getPointsDeVie() + this.soinsAppliques);
        }
    }

}
