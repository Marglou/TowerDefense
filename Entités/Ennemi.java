package Entités;

import GestionTerrain.Plateau;

import java.util.Random;

public class Ennemi extends Entites {

    // cette classe représente tous les ennemis
    protected GestionAttaqueEnnemi gestionAttaqueEnnemi;
    protected int x = 10;
    protected int y = ligneAleatoire();
    protected double posX = x;
    protected int vitesseDeplacement;// sera toujours égale à 1 case par seconde à part si le bonus ralentissement
    // est activé ou
    // s'il s'agit d'un boss
    protected int amesGagnees;// représente le nombre d'âmes récupérées par le joueur une fois l'ennemi en
    // question tué

    // Ajoutez ces variables
    double targetX;
    double targetY;
    long startTime;
    double posY = y;

    protected int ligneAleatoire() {
        Random random = new Random();
        return random.nextInt(5);
    }

    public void deplacement(Plateau plateau) {
        while (this.y > 0 && !plateau.terrain[this.getX()][this.getY() - 1].tourSurLaCase()) {
            deplacer(plateau);
        }
    }

    public void deplacer(Plateau plateau) {
        if (this.getY() > 0) {
            if (plateau.terrain[this.getX()][this.getY() - 1].tourSurLaCase()) {
                return;
            }
            plateau.terrain[this.getX()][this.getY()].removeOccupePar(this);
            this.y--;
            plateau.terrain[this.x][this.y].addOccupePar(this);
        }
    }

    // ----------- Getters et Setters -------------
    public int getVitesseDeplacement() {
        return vitesseDeplacement;
    }

    public void setVitesseDeplacement(int vitesseDeplacement) {
        this.vitesseDeplacement = vitesseDeplacement;
    }

    public int getAmesGagnees() {
        return amesGagnees;
    }

    public void setAmesGagnees(int amesGagnees) {
        this.amesGagnees = amesGagnees;
    }

    // ----------- Attaque -------------
    public void attaque(Tour t, Plateau plateau) {
    }

    public void creationAttaque(Plateau plateau) {
        this.gestionAttaqueEnnemi = new GestionAttaqueEnnemi(plateau, this);
        this.gestionAttaqueEnnemi.lancerAttaque(this);
        this.gestionAttaqueEnnemi.lancerDeplacement(this);
    }

    public static class GestionAttaqueEnnemi {
        protected final Plateau plateau;
        protected final Ennemi ennemi;

        public GestionAttaqueEnnemi(Plateau plateau, Ennemi ennemi) {
            this.plateau = plateau;
            this.ennemi = ennemi;
        }

        public void verificationAttaqueEnnemi() {
            // System.out.println("Verification attaque ennemi");
            // System.out.println("Ennemi : " + this.ennemi.getX() + " " +
            // this.ennemi.getY());
            if (this.ennemi.getX() > 0 && this.plateau.terrain[ennemi.getY()][ennemi.getX() - 1].tourSurLaCase()) {
                // System.out.println("Tour attaquée");
                this.ennemi.attaque(
                        (Tour) this.plateau.terrain[ennemi.getY()][ennemi.getX() - 1].getOccupeParList().get(0),
                        plateau);
                estVivant(plateau.terrain[this.ennemi.getY()][this.ennemi.getX() - 1].getOccupeParList().get(0));
            }

            // deplacement(plateau);
        }

        public void lancerAttaque(Ennemi e) {
            Thread thread = new Thread(() -> {
                while (this.ennemi.getEstVivant()) {
                    verificationAttaqueEnnemi();
                    try {
                        Thread.sleep((long) this.ennemi.getVitesseDAttaque());
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            });
            thread.start();
        }

      

        // // ----------- Déplacement -------------


        public void lancerDeplacement(Ennemi e) {
            Thread thread = new Thread(() -> {
                while (this.ennemi.getEstVivant()) {
                    deplacement(plateau);
                    try {
                        int framesToWait = 20; // Ajustez ce nombre en fonction de votre cas
                        for (int i = 0; i < framesToWait; i++) {
                            Thread.sleep((long) (6000 / 60.0));
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        Thread.currentThread().interrupt();

                    }
                }
            });
            thread.start();
        }

        public void deplacement(Plateau plateau) {
            // System.out.println("Deplacement ennemi" + this.ennemi.getX() + " " + this.ennemi.getY());
            if (this.ennemi.getX() > 0) {
                if (!plateau.terrain[this.ennemi.getY()][this.ennemi.getX() - 1].tourSurLaCase()) {
                    plateau.terrain[this.ennemi.getY()][this.ennemi.getX()].removeOccupePar(this.ennemi);
                    // Utiliser un pourcentage de la distance entre les cases pour le déplacement
                    double newX = this.ennemi.getX() - 0.1; // Ajustez le pourcentage en fonction de votre cas
                    this.ennemi.setPosX(newX);
                    plateau.terrain[this.ennemi.getY()][(int) newX].addOccupePar(this.ennemi);
                }
            } else {
                plateau.terrain[this.ennemi.getY()][this.ennemi.getX()].removeOccupePar(this.ennemi);
            }
        }
    }

    // public void deplacement(Plateau plateau) {
    // if (this.ennemi.getX() > 0 &&
    // !plateau.terrain[this.ennemi.getY()][this.ennemi.getX() - 1].tourSurLaCase())
    // {
    // // Utilisez setTarget pour définir la nouvelle position cible
    // setTarget(this.ennemi.getX() - 1, this.ennemi.getY());
    // updatePosition(); // Mettez à jour graduellement la position
    // }
    // }

    // public void deplacement(Plateau plateau) {
    // if (this.ennemi.getX() > 0 &&
    // !plateau.terrain[this.ennemi.getY()][this.ennemi.getX() - 1].tourSurLaCase())
    // {
    // // Utilisez setTarget pour définir la nouvelle position cible
    // setTarget(this.ennemi.getX() - 1, this.ennemi.getY());
    // updatePosition(); // Mettez à jour graduellement la position
    // try {
    // Thread.sleep(1000); // Ajoutez un délai d'une seconde après chaque
    // déplacement
    // } catch (InterruptedException ex) {
    // ex.printStackTrace();
    // }
    // }
    // }

    // Ajoutez cette méthode
    // private void setTarget(int targetX, int targetY) {
    // this.ennemi.targetX = targetX;
    // this.ennemi.targetY = targetY;
    // this.ennemi.startTime = System.currentTimeMillis();
    // }

    // private void updatePosition() {
    // // System.out.println("update position");
    // double percentage = 0.1; // Ajustez ce nombre pour contrôler la vitesse de
    // déplacement
    // ennemi.setPosX((1.0 - percentage) * ennemi.getX() + percentage *
    // ennemi.targetX);
    // ennemi.setPosY((1.0 - percentage) * ennemi.getY() + percentage *
    // ennemi.targetY);
    // }
    // private void updatePosition() {
    // long currentTime = System.currentTimeMillis();
    // double elapsedTime = (currentTime - ennemi.startTime) / 1000.0; // Temps
    // écoulé en secondes

    // // Ajustez cette valeur pour contrôler la vitesse de déplacement
    // double speed = 20.0; // Essayez avec d'autres valeurs si nécessaire

    // // Ajustez cette valeur pour contrôler la vitesse de déplacement
    // double step = speed * elapsedTime;

    // if (step >= 1.0) {
    // ennemi.setX((int) ennemi.targetX);
    // ennemi.setY((int) ennemi.targetY);
    // ennemi.setPosX(ennemi.getX());
    // ennemi.setPosY(ennemi.getY());
    // } else {
    // ennemi.setPosX((1.0 - step) * ennemi.getX() + step * ennemi.targetX);
    // ennemi.setPosY((1.0 - step) * ennemi.getY() + step * ennemi.targetY);
    // }
    // }

    // public void updateEnnemiPosition() {
    // updatePosition();
    // }

    // }

    // ----------- Getters et Setters -------------
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
        this.posX = x;
    }

    public void setPosX(double posX) {
        this.posX = posX;
        this.x = (int) posX;
    }

    public double getPosX() {
        return this.posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
        this.y = (int) posY;
    }

    public GestionAttaqueEnnemi getGestionAttaqueEnnemi() {
        return this.gestionAttaqueEnnemi;
    }

    public double getPosY() {
        return this.posY;
    }
}
