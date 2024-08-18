package Entités;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class TourVocator extends Tour {

    public TourVocator() {
        this.coutPlacement = 300;
        this.portee = 10;
        this.degatsInfliges = 60;// 100 au niveau 2 et 130 au niveau 3
        this.pointsDeVie = 120;// 170 au niveau 2 et 270 au niveau 3
        this.vitesseDAttaque = 2000;// en ms, et 1800ms au niveau 2 et 1500ms au niveau 3
        this.coutAmelioration = 200;// et 300 du niveau 2 à 3
        this.niveauActuel = 1;// à chaque amélioration, une mini tour doit être invoquée si possible
        this.identifiant = id;
        this.achat = 500;
        id++;
        this.description =  "Permet d'attaquer plusieurs fois.";

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\TourVocatorNiv1Test.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);

    }

    @Override
    public void ameliorationAuNiveau2() {
        this.pointsDeVie = 170;
        this.degatsInfliges = 100;
        this.coutAmelioration = 300;
        this.vitesseDAttaque = 1800;
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\TourVocatorNiv2Test.gif")));
        this.spriteContainer.setIcon(this.sprite);
    }

    @Override
    public void ameliorationAuNiveau3() {
        this.pointsDeVie = 270;
        this.degatsInfliges = 130;
        this.vitesseDAttaque = 1500;
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\TourVocatorNiv3Test.gif")));
        this.spriteContainer.setIcon(this.sprite);

    }

    public void attaque(Ennemi e, Plateau plateau) {
        e.setPointsDeVie(e.getPointsDeVie() - this.degatsInfliges);
        if (this.getNiveauActuel() == 2) {
            attaqueNiv2(plateau);
        } else if (this.getNiveauActuel() == 3) {
            attaqueNiv3(plateau);
        }
    }

    public void attaqueCritique(Ennemi e, Plateau plateau) {
        e.setPointsDeVie(e.getPointsDeVie() - this.degatsInfliges * 1.5);
        if (this.getNiveauActuel() == 2) {
            attaqueNiv2(plateau);
        } else if (this.getNiveauActuel() == 3) {
            attaqueNiv3(plateau);
        }
    }

    public void attaqueNiv2(Plateau plateau) {
        for (int i = this.colonne; i < plateau.getLargeur(); i++) {
            if (plateau.terrain[this.ligne][i].ennemiSurLaCase()) {
                plateau.terrain[this.ligne][i].getOccupeParList().get(0)
                        .setPointsDeVie(plateau.terrain[this.ligne][i].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges / 2);
                break;
            }
        }
    }

    public void attaqueNiv3(Plateau plateau) {
        int cmpt = 0;
        for (int i = this.colonne; i < plateau.getLargeur(); i++) {
            if (plateau.terrain[this.ligne][i].ennemiSurLaCase()) {
                plateau.terrain[this.ligne][i].getOccupeParList().get(0)
                        .setPointsDeVie(plateau.terrain[this.ligne][i].getOccupeParList().get(0).getPointsDeVie()
                                - this.degatsInfliges / 2);
                cmpt++;
                if (cmpt == 2) {
                    break;
                }
            }
        }
    }
}
