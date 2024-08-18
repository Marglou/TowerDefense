package Entités;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class Djinn extends Ennemi {

    // ennemi puissant, attaque de zone
    // a voir pour interchanger deux tours de place
    // taux critique de 1/12

    public Djinn() {
        this.pointsDeVie = 2200;
        this.vitesseDAttaque = 4000; // en ms
        this.portee = 1; // portée à gauche, dégats de zone donc une case en haut et en bas de la touchée
        this.degatsInfliges = 350; // 50% pour les dégâts de zone, 4 cases autour de la cible
        this.vitesseDeplacement = 1;
        this.amesGagnees = 500;

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\Djinn.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void attaque(Tour t, Plateau plateau) {
        Random r = new Random();
        int uneChanceSurDouze = r.nextInt(1, 13);
        if (uneChanceSurDouze == 1) {
            t.pointsDeVie = t.pointsDeVie - (int) (this.degatsInfliges * 1.5);
        } else {
            t.pointsDeVie -= this.degatsInfliges;
        }
        verificationCasSpeciaux(t, plateau);
    }

    private void verificationCasSpeciaux(Tour t, Plateau plateau) {
        if (t.getLigne() == 0 && t.getColonne() == 0 && plateau.terrain[1][0].tourSurLaCase())// si la tour se trouve
                                                                                              // dans le coin supérieur
                                                                                              // gauche du terrain
        {
            plateau.terrain[1][0].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[1][0].getOccupeParList().get(0).getPointsDeVie() - this.degatsInfliges / 2);
        } else if (t.getLigne() == 0 && t.getColonne() == plateau.terrain[0].length - 1)// si la tour se trouve dans le
                                                                                        // coin supérieur droit du
                                                                                        // terrain
        {
            degatsCoinHautDroit(t, plateau);
        } else if (t.getLigne() == plateau.getHauteur() - 1 && t.getColonne() == 0)// si la tour se trouve dans le coin
                                                                                   // inférieur gauche du terrain
        {
            degatsCoinBasGauche(t, plateau);
        } else if (t.getLigne() == plateau.getHauteur() - 1 && t.getColonne() == plateau.getLargeur() - 1)// si la tour
                                                                                                          // se trouve
                                                                                                          // dans le
                                                                                                          // coin
                                                                                                          // inférieur
                                                                                                          // droit du
                                                                                                          // terrain
        {
            degatsCoinBasDroit(t, plateau);
        } else {
            verificationCasSemiSpeciaux(t, plateau);
        }
    }

    private void verificationCasSemiSpeciaux(Tour t, Plateau plateau) {
        if (t.getColonne() == 0)// si la tour est sur la bordure gauche mais pas aux extremités hautes/basses
        {
            degatsBordureGauche(t, plateau);
        } else if (t.getColonne() == plateau.getLargeur() - 1)// si la tour est sur la bordure droite mais pas sur les
                                                              // extremités hautes/basses
        {
            degatsBordureDroite(t, plateau);
        } else if (t.getLigne() == 0)// si la tour est sur la bordure haute mais pas sur les extremités gauche/droite
        {
            degatsBordureHaute(t, plateau);
        } else if (t.getLigne() == plateau.getHauteur() - 1)// si la tour est sur la bordure basse mais pas sur les
                                                            // extremités gauche/droite
        {
            degatsBordureBasse(t, plateau);
        } else {
            verificationCasGeneraux(t, plateau);
        }
    }

    private void verificationCasGeneraux(Tour t, Plateau plateau) {
        if (plateau.terrain[t.getLigne() - 1][t.getColonne()].tourSurLaCase()) {
            plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
        if (plateau.terrain[t.getLigne()][t.getColonne() - 1].tourSurLaCase()) {
            plateau.terrain[t.getLigne()][t.getColonne() - 1].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne()][t.getColonne() - 1].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
        if (plateau.terrain[t.getLigne() + 1][t.getColonne()].tourSurLaCase()) {
            plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
    }

    private void degatsCoinHautDroit(Tour t, Plateau plateau) {
        if (plateau.terrain[0][t.getColonne() - 1].tourSurLaCase()) {
            plateau.terrain[0][t.getColonne() - 1].getOccupeParList().get(0)
                    .setPointsDeVie(plateau.terrain[0][t.getColonne() - 1].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
        if (plateau.terrain[1][t.getColonne()].tourSurLaCase()) {
            plateau.terrain[1][t.getColonne()].getOccupeParList().get(0)
                    .setPointsDeVie(plateau.terrain[1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
    }

    private void degatsCoinBasGauche(Tour t, Plateau plateau) {
        if (plateau.terrain[t.getLigne() - 1][t.getColonne()].tourSurLaCase()) {
            plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
        if (plateau.terrain[t.getLigne()][t.getColonne() + 1].tourSurLaCase()) {
            plateau.terrain[t.getLigne()][t.getColonne() + 1].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne()][t.getColonne() + 1].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
    }

    private void degatsCoinBasDroit(Tour t, Plateau plateau) {
        if (plateau.terrain[t.getLigne()][t.getColonne() - 1].tourSurLaCase()) {
            plateau.terrain[t.getLigne()][t.getColonne() - 1].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne()][t.getColonne() - 1].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
        if (plateau.terrain[t.getLigne() - 1][t.getColonne()].tourSurLaCase()) {
            plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
    }

    private void degatsBordureGauche(Tour t, Plateau plateau) {
        if (plateau.terrain[t.getLigne() - 1][t.getColonne()].tourSurLaCase()) {
            plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
        if (plateau.terrain[t.getLigne()][t.getColonne() + 1].tourSurLaCase()) {
            plateau.terrain[t.getLigne()][t.getColonne() + 1].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne()][t.getColonne() + 1].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
        if (plateau.terrain[t.getLigne() + 1][t.getColonne()].tourSurLaCase()) {
            plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
    }

    private void degatsBordureDroite(Tour t, Plateau plateau) {
        if (plateau.terrain[t.getLigne() - 1][t.getColonne()].tourSurLaCase()) {
            plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
        if (plateau.terrain[t.getLigne()][t.getColonne() - 1].tourSurLaCase()) {
            plateau.terrain[t.getLigne()][t.getColonne() - 1].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne()][t.getColonne() - 1].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
        if (plateau.terrain[t.getLigne() + 1][t.getColonne()].tourSurLaCase()) {
            plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
    }

    private void degatsBordureHaute(Tour t, Plateau plateau) {
        if (plateau.terrain[t.getLigne() + 1][t.getColonne()].tourSurLaCase()) {
            plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne() + 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
        if (plateau.terrain[t.getLigne()][t.getColonne() - 1].tourSurLaCase()) {
            plateau.terrain[t.getLigne()][t.getColonne() - 1].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne()][t.getColonne() - 1].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
    }

    private void degatsBordureBasse(Tour t, Plateau plateau) {
        if (plateau.terrain[t.getLigne() - 1][t.getColonne()].tourSurLaCase()) {
            plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne() - 1][t.getColonne()].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
        if (plateau.terrain[t.getLigne()][t.getColonne() - 1].tourSurLaCase()) {
            plateau.terrain[t.getLigne()][t.getColonne() - 1].getOccupeParList().get(0).setPointsDeVie(
                    plateau.terrain[t.getLigne()][t.getColonne() - 1].getOccupeParList().get(0).getPointsDeVie()
                            - this.degatsInfliges / 2);
        }
    }

}
