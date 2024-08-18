package Entités;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GestionTerrain.Plateau;

public class TourCuratis extends Tour {

    double soinsAppliques;// cette tour n'inflige pas de dégâts mais soigne les tours aux alentours

    public TourCuratis() {
        this.coutPlacement = 400;
        this.portee = 2;// 2 cases autour d'elle au niveau 1, 3 cases autour d'elle au niveau 2 et 5
                        // cases autour d'elle au niveau 3
        this.degatsInfliges = 0;// cette tour applique des soins
        this.soinsAppliques = 100;// 300 au niveau 2 et 500 au niveau 3
        this.pointsDeVie = 250;// 300 au niveau 2 et 500 au niveau 3
        this.vitesseDAttaque = 6000;// il s'agit ici de la vitesse à laquelle le soin est appliqué,en ms, et 5000ms
                                    // au niveau 2 et 3500ms au niveau 3
        this.coutAmelioration = 100;// et 900 du niveau 2 à 3
        this.niveauActuel = 1;
        this.identifiant = id;
        this.achat = 1000;
        id++;
        this.description = "Pas d'attaque mais permet de soigner les tours autours.";

        // gestion sprite
        this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\TourCuratisNiv1Test.gif")));
        this.spriteContainer = new JLabel(this.sprite);
        this.spriteContainer.setSize(80, 80);

    }

    @Override
    public void ameliorationAuNiveau2() {
        this.pointsDeVie = 300;
        this.degatsInfliges = 10;
        this.coutAmelioration = 900;
        this.vitesseDAttaque = 5000;
        this.sprite = new ImageIcon(this.getClass().getResource("..\\Sprites\\TourCuratisNiv2Test.gif"));
        this.spriteContainer.setIcon(this.sprite);

    }

    @Override
    public void ameliorationAuNiveau3() {
        this.pointsDeVie = 500;
        this.degatsInfliges = 20;
        this.vitesseDAttaque = 3500;
        this.sprite = new ImageIcon(this.getClass().getResource("..\\Sprites\\TourCuratisNiv3Test.gif"));
        this.spriteContainer.setIcon(this.sprite);
    }

    public void attaque(Ennemi e, Plateau plateau) {
        for (Tour t : plateau.getTours()) {
            t.setPointsDeVie(t.getPointsDeVie() + this.soinsAppliques);
        }
    }

    public void attaqueCritique(Ennemi e, Plateau plateau) {
        for (Tour t : plateau.getTours()) {
            t.setPointsDeVie(t.getPointsDeVie() + this.soinsAppliques * 1.5);
        }
    }
}
