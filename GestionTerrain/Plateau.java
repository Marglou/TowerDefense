package GestionTerrain;

import java.util.ArrayList;

import Entités.Ennemi;
import Entités.Tour;
import Game.Joueur;

public class Plateau {
    public Case[][] terrain;
    int hauteur = 5;
    int largeur = 10;
    int largeurReelle = this.largeur + 1;// en réalité, seules 10 colonnes sont accessibles pour les tours, la onzième
                                         // colonne sert de point de départ aux ennemis
    private Joueur joueur;
    private ArrayList<Tour> toursSurLeTerrain = new ArrayList<>();
    private ArrayList<Ennemi> ennemisSurLeTerrain = new ArrayList<>();

    public Plateau(Joueur joueur) {
        this.joueur = joueur;
        this.terrain = new Case[hauteur][largeurReelle];
        for (int i = 0; i < this.terrain.length; i++) {
            for (int j = 0; j < this.terrain[i].length; j++) {

                this.terrain[i][j] = new Case(i, j);
            }
        }

    }

    // ------- Ajout ennemi sur le plateau ---------
    public void ajouterEnnemi(Ennemi ennemi) {
        placerEnnemi(ennemi);
    }

    public void placerEnnemi(Ennemi ennemi) {
        terrain[ennemi.getY()][ennemi.getX()].addOccupePar(ennemi); // liste de la case
        ennemisSurLeTerrain.add(ennemi); // liste du plateau
        // System.out.println(ennemisSurLeTerrain);
        ennemi.creationAttaque(this);
    }

    // ------- Ajout tour sur le plateau ---------
    public void placerTour(Tour tour, int x, int y) {
        tour.setX(x); // Définir la coordonnée X de la tour
        tour.setY(y); // Définir la coordonnée Y de la tour
        tour.setLigne(y);
        tour.setColonne(x);
        toursSurLeTerrain.add(tour); // Ajouter la tour à la liste des tours du plateau
        terrain[y][x].addOccupePar(tour); // Définir la tour comme occupant la case
        tour.creationAttaque(this); // Créer l'attaque de la tour
    }

    public void affichage() {
        for (int i = 0; i < this.largeur; i++) {
            System.out.print("    " + i);
        }
        System.out.println();
        for (int i = 0; i < this.terrain.length; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < this.terrain[i].length; j++) {
                this.terrain[i][j].affichage();
                System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // ------- Getters et Setters ---------
    public int getHauteur() {
        return hauteur;
    }

    public int getLargeurReelle() {
        return largeurReelle;
    }

    public int getLargeur() {
        return largeur;
    }

    public boolean tourDevantOuPas(Case c) {
        if (c.x != 0) {
            return this.terrain[c.x - 1][c.y].tourSurLaCase();
        }
        return false;
    }

    public Case getCase(int row, int column) {
        return terrain[column][row];
    }

    public boolean isValidPosition(Tour tour, int x, int y) {
        // Vérifier si la position est dans le terrain
        if (x < 0 || x >= largeurReelle || y < 0 || y >= hauteur) {
            return false;
        }

        // Vérifier si la case est occupée par une autre tour
        if (terrain[y][x].getOccupePar() != null) {
            return false;
        }

        return true;
    }

    public ArrayList<Tour> getTours() {
        return this.toursSurLeTerrain;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<Ennemi> getEnnemis() {
        return this.ennemisSurLeTerrain;
    }

    // public void deplacer(Ennemi ennemi) {
    // for (int dy = -1; dy <= 1; dy++) {
    // if (this.terrain[ennemi.getY() + dy][ennemi.getX()].tourSurLaCase()) {
    // return;
    // }

    // }
    // if (ennemi.getY() - 1 < 0) {
    // return;
    // }
    // this.terrain[ennemi.getY()][ennemi.getX()].removeOccupePar(ennemi);;
    // ennemi.setY(ennemi.getY() - 1);
    // this.terrain[ennemi.getY()][ennemi.getX()].addOccupePar(ennemi);
    // if (ennemi.getY() != 0) {
    // if (this.terrain[ennemi.getX()][ennemi.getY() - 1].tourSurLaCase()) {
    // return;
    // }
    // this.terrain[ennemi.getX()][ennemi.getY()].occupePar = null;
    // ennemi.setY(ennemi.getY() - 1);
    // this.terrain[ennemi.getX()][ennemi.getY()].addOccupePar(ennemi);
    // }
    // }

    // public void deplacement(Ennemi ennemi) {
    // while (!this.terrain[ennemi.getX()][ennemi.getY() - 1].tourSurLaCase()) {
    // deplacer(ennemi);
    // affichage();
    // }
    // }

}
