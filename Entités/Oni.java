package Entités;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class Oni extends Ennemi {

    // ennemi peu commun, dégats de zone et plus de pv

    public Oni() {
        this.pointsDeVie = 450;
        this.vitesseDAttaque = 3000; // en ms
        this.portee = 1; // portée à gauche, dégats de zone donc une case en haut et en bas de la tour
                         // touchée
        this.degatsInfliges = 40; // 10 pour les dégats de zone
        this.vitesseDeplacement = 5000;
        this.amesGagnees = 80;

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\Oni.gif")));
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
            t.pointsDeVie = t.pointsDeVie - (int) (this.degatsInfliges * 1.5);
            degatsCritiqueZone(t, plateau);
        } else {
            t.pointsDeVie -= this.degatsInfliges;
            degatsZone(t, plateau);
        }

    }

    private void degatsCritiqueZone(Tour t, Plateau plateau) {
        if (t.getLigne() == 0)// si la tour est sur la ligne 0
        {
            if (plateau.terrain[t.getLigne() + 1][t.getColonne()].tourSurLaCase()) {
                plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges * 1.5);
            }
        } else if (t.getLigne() == 4)// si la tour est sur la ligne 4
        {
            if (plateau.terrain[t.getLigne() - 1][t.getColonne()].tourSurLaCase()) {
                plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges * 1.5);
            }
        } else // si la tour est sur une des lignes du milieu
        {
            if (plateau.terrain[t.getLigne() - 1][t.getColonne()].tourSurLaCase()) {
                plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges * 1.5);
            }
            if (plateau.terrain[t.getLigne() + 1][t.getColonne()].tourSurLaCase()) {
                plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges * 1.5);
            }

        }
    }

    private void degatsZone(Tour t, Plateau plateau) {
        if (t.getLigne() == 0)// si la tour est sur la ligne 0
        {
            if (plateau.terrain[t.getLigne() + 1][t.getColonne()].tourSurLaCase()) {
                plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges);
            }
        } else if (t.getLigne() == 4)// si la tour est sur la ligne 4
        {
            if (plateau.terrain[t.getLigne() - 1][t.getColonne()].tourSurLaCase()) {
                plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges);
            }
        } else // si la tour est sur une des lignes du milieu
        {
            if (plateau.terrain[t.getLigne() - 1][t.getColonne()].tourSurLaCase()) {
                plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges);
            }
            if (plateau.terrain[t.getLigne() + 1][t.getColonne()].tourSurLaCase()) {
                plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges);
            }

        }
    }

}
