package GestionTerrain;

import java.util.ArrayList;

import Entités.Ennemi;
import Entités.Entites;
import Entités.Tour;

public class Case {
    int x;// position horizontale
    int y;// position verticale
    boolean occupeeOuPas;// vrai si occupée, faux sinon
    ArrayList<Entites> occupePar = new ArrayList<Entites>();// variable qui contient les entités qui occupent la case

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupeeOuPas() {
        return occupeeOuPas;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setOccupeeOuPas(boolean occupeeOuPas) {
        this.occupeeOuPas = occupeeOuPas;
    }

    // retourne la premiere entité de la liste qui occupe la case
    public Entites getOccupePar() {
        if (this.occupePar.isEmpty()) {
            return null;
        }
        return this.occupePar.get(0);
    }

    // retourne la liste des entités qui occupent la case
    public ArrayList<Entites> getOccupeParList() {
        return this.occupePar;
    }

    // ajoute l'entité à la liste
    public void addOccupePar(Entites occupePar) {
        this.occupePar.add(occupePar);
    }

    // remove l'entité de la liste
    public void removeOccupePar(Entites occupePar) {
        if (this.occupePar.contains(occupePar)) {
            this.occupePar.remove(occupePar);
        }
    }
    
    // liste vide
    public boolean isEmpty() {
        return !this.occupePar.isEmpty();
    }

    public Case(int i, int j) {
        this.x = j;
        this.y = i;
        this.occupeeOuPas = false;
    }

    public boolean tourSurLaCase() {
        return !this.occupePar.isEmpty() && this.occupePar.get(0) instanceof Tour;
    }

    public boolean ennemiSurLaCase() {
        return !this.occupePar.isEmpty() && this.occupePar.get(0) instanceof Ennemi;
    }


    public void affichage() {
        if (this.tourSurLaCase()) {
            System.out.print("+ ");
        } else if (this.ennemiSurLaCase()) {
            System.out.print("§ ");
        } else {
            System.out.print("  ");
        }
    }
}
