package Entités;

public class Ectoplasma extends Entites {

    // 1er Boss du mode histoire
    // récompense : tour Vindicta, 500 pièces et bonus "Santé améliorée"

    public Ectoplasma() {
        this.pointsDeVie = 3000;
        this.vitesseDAttaque = 5000; // en ms
        this.portee = 1; // attaque au hasard 5 cases
        this.degatsInfliges = 500;

        // // gestion sprite
        // this.sprite = new
        // ImageIcon((this.getClass().getResource("..\\Sprites\\Ectoplasma.gif")));
        // this.spriteContainer = new JLabel(this.sprite);
        // this.spriteContainer.setSize(80, 80);
    }

}
