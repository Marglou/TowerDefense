package Entités;

public class Darkrai extends Ennemi {

    // 3ème Boss du mode histoire
    // récompense : tour Ianis, 1000 pièces et bonus "Voile protecteur"

    public Darkrai() {
        this.pointsDeVie = 10000;
        this.vitesseDAttaque = 5000; // en ms
        this.portee = 1; // cible 1 tour au hasard
        this.degatsInfliges = 700; 

        // 1/10 de détruire d'un coup une tour
        // 10% des dégâts infliqués le soigne

         // // gestion sprite
        // this.sprite = new ImageIcon((this.getClass().getResource("..\\Sprites\\Darkrai.gif")));
        // this.spriteContainer = new JLabel(this.sprite);
        // this.spriteContainer.setSize(80, 80);
    }
    
}
