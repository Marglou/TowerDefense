package Entités;

public class Sulfura extends Entites {

    // 2ème Boss du mode histoire
    // récompense : tour Caemento, 700 pièces et bonus "Critique accrue"

    public Sulfura() {
        this.pointsDeVie = 6000;
        this.degatsInfliges = 700;
        this.portee = 1; // cible 1 tour au hasard, attaque de zone sur les 4 cases autour
        this.vitesseDAttaque = 5000; // en ms

        // // gestion sprite
        // this.sprite = new
        // ImageIcon((this.getClass().getResource("..\\Sprites\\Sulfura.gif")));
        // this.spriteContainer = new JLabel(this.sprite);
        // this.spriteContainer.setSize(80, 80);
    }

}
