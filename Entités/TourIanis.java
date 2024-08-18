package Entités;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class TourIanis extends Tour {

    double soinsAppliques;// soins qui s'appliquent à toutes les tours sur le terrain

    public TourIanis() {
        this.coutPlacement = 1500;
        this.portee = 0;// la portée de cette tour est TOUT LE TERRAIN
        this.degatsInfliges = 100;// 400 au niveau 2 et 800 au niveau 3
        this.soinsAppliques = 50;// 100 au niveau 2 et 150 au niveau 3
        // la vitesse de soins devra être gérée de sorte à ce qu'ils soient appliqués à
        // toutes les tours toutes les 5000ms
        this.pointsDeVie = 200;// 400 au niveau 2 et 600 au niveau 3
        this.vitesseDAttaque = 4000;// en ms, et 2500ms au niveau 2 et 1000ms au niveau 3
        this.coutAmelioration = 1300;// et 2000 du niveau 2 à 3
        this.niveauActuel = 1;
        this.identifiant = id;
        id++;
        this.description = "Attaque tous les ennemis sur le terrain. Debloquable après avoir fini tous les niveaux du mode histoire";

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\TourIanisNiv1Test.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);

    }

    @Override
    public void ameliorationAuNiveau2() {
        this.pointsDeVie = 400;
        this.degatsInfliges = 400;
        this.coutAmelioration = 300;
        this.vitesseDAttaque = 2500;
        this.sprite = new ImageIcon(this.getClass().getResource("..\\Sprites\\TourIanisNiv2Test.gif"));
        this.spriteContainer.setIcon(this.sprite);
    }

    @Override
    public void ameliorationAuNiveau3() {
        this.pointsDeVie = 600;
        this.degatsInfliges = 800;
        this.vitesseDAttaque = 1000;
        this.sprite = new ImageIcon(this.getClass().getResource("..\\Sprites\\TourIanisNiv3Test.gif"));
        this.spriteContainer.setIcon(this.sprite);

    }

    public void attaque(Ennemi e, Plateau plateau) {
        for (int i = 0; i < plateau.terrain.length; i++) {
            for (int j = 0; j < plateau.terrain[i].length; j++) {
                if (plateau.terrain[i][j].ennemiSurLaCase()) {
                    plateau.terrain[i][j].getOccupePar().setPointsDeVie(
                            plateau.terrain[i][j].getOccupePar().getPointsDeVie() - this.degatsInfliges);
                } else if (plateau.terrain[i][j].tourSurLaCase()) {
                    plateau.terrain[i][j].getOccupePar().setPointsDeVie(
                            plateau.terrain[i][j].getOccupePar().getPointsDeVie() + this.soinsAppliques);
                }
            }
        }
    }

    public void attaqueCritique(Ennemi e, Plateau plateau) {
        for (int i = 0; i < plateau.terrain.length; i++) {
            for (int j = 0; j < plateau.terrain[i].length; j++) {
                if (plateau.terrain[i][j].ennemiSurLaCase()) {
                    plateau.terrain[i][j].getOccupePar().setPointsDeVie(
                            plateau.terrain[i][j].getOccupePar().getPointsDeVie() - this.degatsInfliges * 1.5);
                } else if (plateau.terrain[i][j].tourSurLaCase()) {
                    plateau.terrain[i][j].getOccupePar().setPointsDeVie(
                            plateau.terrain[i][j].getOccupePar().getPointsDeVie() + this.soinsAppliques);
                }
            }
        }
    }

}
