package Entités;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Entites extends JPanel {
    //cette classe va représenter toutes les entités : tours et ennemis et boss
    protected double pointsDeVie;
    protected double vitesseDAttaque;
    protected int portee;
    protected double degatsInfliges;

    
    protected ImageIcon sprite;
    protected JLabel spriteContainer;

    protected int posX;
    protected int posY;
    protected boolean estVivant = true;


    // --------- GETTERS ET SETTERS ---------

    // Position
    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public void setX(int x) {
        this.posX = x;
    }

    public void setY(int y) {
        this.posY = y;
    }

    // Vie
    public boolean getEstVivant() {
        return estVivant;
    }

    public void setEstVivant(boolean b) {
        this.estVivant = b;
    }

    public double getPointsDeVie() {
        return pointsDeVie;
    }

    public void setPointsDeVie(double pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public static void estVivant (Entites entite) {
        if (entite.getPointsDeVie() <= 0) {
            entite.setEstVivant(false);
        }
    }

    // Vitesse d'attaque
    public double getVitesseDAttaque() {
        return vitesseDAttaque;
    }

    public void setVitesseDAttaque(double vitesseDAttaque) {
        this.vitesseDAttaque = vitesseDAttaque;
    }

    // Portée
    public int getPortee() {
        return portee;
    }

    public void setPortee(int portee) {
        this.portee = portee;
    }

    // Dégats
    public double getDegatsInfliges() {
        return degatsInfliges;
    }

    public void setDegatsInfliges(double degatsInfliges) {
        this.degatsInfliges = degatsInfliges;
    }

    // Sprite
    public ImageIcon getSprite() {
        return sprite;
    }

    public void setSprite(ImageIcon sprite) {
        this.sprite = sprite;
    }

    public JLabel getSpriteContainer() {
        return spriteContainer;
    }

    public void setSpriteContainer(JLabel spriteContainer) {
        this.spriteContainer = spriteContainer;
    }

    // coup critique
    public int creacritique (int limite){
        Random rand = new Random();
        return rand.nextInt(1,limite);
    }



}
