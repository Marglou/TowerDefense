package Entités;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class TourBasique extends Tour {

    public TourBasique() {

        this.coutPlacement = 50;
        this.portee = 10;
        this.degatsInfliges = 20;// 40 au niveau 2 et 80 au niveau 3
        this.pointsDeVie = 100;// 150 au niveau 2 et 250 au niveau 3
        this.vitesseDAttaque = 2000;// en ms, et 1800ms au niveau 2 et 1500ms au niveau 3
        this.coutAmelioration = 150;// et 300 du niveau 2 à 3
        // this.niveauActuel = 1;
        this.identifiant = id;
        id++;
        this.description = "Attaque basique";

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\TourBasiqueNiv1Test.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);
    }

    @Override
    public String toString() {
        return "cette tour a pour identifiant " + this.identifiant;
    }

    @Override
    public void ameliorationAuNiveau2() {
        this.pointsDeVie = 150;
        this.degatsInfliges = 40;
        this.coutAmelioration = 300;
        this.vitesseDAttaque = 1800;
        this.sprite = new ImageIcon(this.getClass().getResource("..\\Sprites\\TourBasiqueNiv2Test.gif"));
        this.spriteContainer.setIcon(this.sprite);
    }

    @Override
    public void ameliorationAuNiveau3() {
        this.pointsDeVie = 250;
        this.degatsInfliges = 80;
        this.vitesseDAttaque = 1500;
        this.sprite = new ImageIcon(this.getClass().getResource("..\\Sprites\\TourBasiqueNiv3Test.gif"));
        this.spriteContainer.setIcon(this.sprite);

    }

    public void attaque(Ennemi e, Plateau plateau) {
        e.setPointsDeVie(e.getPointsDeVie() - this.degatsInfliges);
    }

    public void attaqueCrtique(Ennemi e, Plateau plateau) {
        e.setPointsDeVie(e.getPointsDeVie() - this.degatsInfliges * 1.5);
    }
    

}
