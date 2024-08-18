package Entités;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class TourCaemento extends Tour {

    public TourCaemento() {
        this.coutPlacement = 800;
        this.portee = 10;
        this.degatsInfliges = 500;// 800 au niveau 2 et 1100 au niveau 3
        this.pointsDeVie = 300;// 500 au niveau 2 et 1000 au niveau 3
        this.vitesseDAttaque = 5000;// en ms, et 3500ms au niveau 2 et 1000ms au niveau 3
        this.coutAmelioration = 800;// et 1100 du niveau 2 à 3
        this.niveauActuel = 1;
        this.identifiant = id;
        this.achat = 300;
        id++;
        this.description = "Debloquable apres avoir passé le niveau 6 du mode histoire.";

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\TourCaementoNiv1Test.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);

    }

    @Override
    public void ameliorationAuNiveau2() {
        this.pointsDeVie = 500;
        this.degatsInfliges = 800;
        this.coutAmelioration = 1100;
        this.vitesseDAttaque = 3500;
        this.sprite = new ImageIcon(this.getClass().getResource("..\\Sprites\\TourCaementoNiv2Test.gif"));
        this.spriteContainer.setIcon(this.sprite);
    }

    @Override
    public void ameliorationAuNiveau3() {
        this.pointsDeVie = 1000;
        this.degatsInfliges = 1100;
        this.vitesseDAttaque = 1000;
        this.sprite = new ImageIcon(this.getClass().getResource("..\\Sprites\\TourCaementoNiv3Test.gif"));
        this.spriteContainer.setIcon(this.sprite);

    }

    public void attaque(Ennemi e, Plateau plateau) {
        e.setPointsDeVie(e.getPointsDeVie() - this.degatsInfliges);
        if (e.getX() == 0) {
            if (plateau.terrain[e.getY()][1].ennemiSurLaCase()) {
                plateau.terrain[e.getY()][1].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[e.getY()][1].getOccupeParList().get(0).getPointsDeVie() - this.degatsInfliges / 2);
            }
        } else if (e.getX() == 4) {
            if (plateau.terrain[e.getY()][3].ennemiSurLaCase()) {
                plateau.terrain[e.getY()][3].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[e.getY()][3].getOccupeParList().get(0).getPointsDeVie() - this.degatsInfliges / 2);
            }
        } else {
            if (plateau.terrain[e.getY()][e.getX() - 1].ennemiSurLaCase()) {
                plateau.terrain[e.getY()][e.getX() - 1].getOccupeParList().get(0)
                        .setPointsDeVie(plateau.terrain[e.getY()][e.getX() - 1].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges / 2);
            }
            if (plateau.terrain[e.getY()][e.getX() + 1].ennemiSurLaCase()) {
                plateau.terrain[e.getY()][e.getX() + 1].getOccupeParList().get(0)
                        .setPointsDeVie(plateau.terrain[e.getY()][e.getX() + 1].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges / 2);
            }
        }
    }

    public void attaqueCrtique(Ennemi e, Plateau plateau) {
        e.setPointsDeVie(e.getPointsDeVie() - this.degatsInfliges * 1.5);
        if (e.getX() == 0) {
            if (plateau.terrain[e.getY()][1].ennemiSurLaCase()) {
                plateau.terrain[e.getY()][1].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[e.getY()][1].getOccupeParList().get(0).getPointsDeVie() - this.degatsInfliges / 2);
            }
        } else if (e.getX() == 4) {
            if (plateau.terrain[e.getY()][3].ennemiSurLaCase()) {
                plateau.terrain[e.getY()][3].getOccupeParList().get(0).setPointsDeVie(
                        plateau.terrain[e.getY()][3].getOccupeParList().get(0).getPointsDeVie() - this.degatsInfliges / 2);
            }
        } else {
            if (plateau.terrain[e.getY()][e.getX() - 1].ennemiSurLaCase()) {
                plateau.terrain[e.getY()][e.getX() - 1].getOccupeParList().get(0)
                        .setPointsDeVie(plateau.terrain[e.getY()][e.getX() - 1].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges / 2);
            }
            if (plateau.terrain[e.getY()][e.getX() + 1].ennemiSurLaCase()) {
                plateau.terrain[e.getY()][e.getX() + 1].getOccupeParList().get(0)
                        .setPointsDeVie(plateau.terrain[e.getY()][e.getX() + 1].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges / 2);
            }
        }
    }

}
