package Entités;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class TourVindicta extends Tour {

    public TourVindicta() {
        this.coutPlacement = 600;
        this.portee = 10;
        this.degatsInfliges = 200;// 400 au niveau 2 et 800 au niveau 3
        this.pointsDeVie = 200;// 450 au niveau 2 et 900 au niveau 3
        this.vitesseDAttaque = 2000;// en ms, et 1500ms au niveau 2 et 1000ms au niveau 3
        this.coutAmelioration = 700;// et 1000 du niveau 2 à 3
        this.niveauActuel = 1;
        this.identifiant = id;
        id++;
        this.description = "Possède un taux critique plus élevé que les autres tours. Une chance sur 6.";

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\TourVindictaNiv1Test.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);

    }

    @Override
    public void ameliorationAuNiveau2() {
        this.pointsDeVie = 450;
        this.degatsInfliges = 400;
        this.coutAmelioration = 1000;
        this.vitesseDAttaque = 1500;
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\TourVindictaNiv2Test.gif")));
        this.spriteContainer.setIcon(this.sprite);
    }

    @Override
    public void ameliorationAuNiveau3() {
        this.pointsDeVie = 900;
        this.degatsInfliges = 800;
        this.vitesseDAttaque = 1000;
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\TourVindictaNiv3Test.gif")));
        this.spriteContainer.setIcon(this.sprite);

    }

    public void attaque(Ennemi e, Plateau plateau) {
        e.setPointsDeVie(e.getPointsDeVie() - this.degatsInfliges);
    }

    public void attaqueCritique(Ennemi e, Plateau plateau) {
        e.setPointsDeVie(e.getPointsDeVie() - this.degatsInfliges * 1.5);
    }
}
