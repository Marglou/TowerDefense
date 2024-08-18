package Entités;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class TourTutela extends Tour {

    public TourTutela() {
        this.coutPlacement = 100;
        this.portee = 1;
        /*
         * Cette tour ne doit infliger de dégâts uniquement lorsqu'elle est
         * détruite(quand ses points de vie)
         */
        this.degatsInfliges = 150;// 250 au niveau 2 et 600 au niveau 3
        this.pointsDeVie = 250;// 500 au niveau 2 et 1000 au niveau 3
        this.vitesseDAttaque = 0;// une attaque lorsque la tour est détruite dont les dégâts dépendent du niveau
                                 // d'amélioration
        this.coutAmelioration = 400;// et 600 du niveau 2 à 3
        this.niveauActuel = 1;
        this.identifiant = id;
        this.achat = 500;
        id++;
        this.description = "N'inflige des dégâts qu'à sa destruction, sur l'ennemi le plus proche." ;

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\TourTutelaNiv1Test.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);

    }

    @Override
    public void ameliorationAuNiveau2() {
        this.pointsDeVie = 500;
        this.degatsInfliges = 250;
        this.coutAmelioration = 600;
        // this.vitesseDAttaque = 1800;
        this.sprite = new ImageIcon(this.getClass().getResource("..\\Sprites\\TourTutelaNiv2Test.gif"));
        this.spriteContainer.setIcon(this.sprite);
    }

    @Override
    public void ameliorationAuNiveau3() {
        this.pointsDeVie = 1000;
        this.degatsInfliges = 600;
        // this.vitesseDAttaque = 1500;
        this.sprite = new ImageIcon(this.getClass().getResource("..\\Sprites\\TourTutelaNiv3Test.gif"));
        this.spriteContainer.setIcon(this.sprite);

    }

    public void attaque(Ennemi e, Plateau plateau) {
        e.setPointsDeVie(e.getPointsDeVie() - this.degatsInfliges);

    }

    public void attaqueCritique(Ennemi e, Plateau plateau) {
        e.setPointsDeVie(e.getPointsDeVie() - this.degatsInfliges * 1.5);

    }
}
