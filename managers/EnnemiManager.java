package managers;

import java.util.ArrayList;
import java.util.Iterator;

import Entités.Ennemi;
import Entités.Tour;
import Entités.TourTutela;
import Game.Joueur;
import GestionTerrain.Case;
import GestionTerrain.Plateau;

public class EnnemiManager {

    private ArrayList<Ennemi> ennemisEnAttente = new ArrayList<>();
    private Plateau plateau;
    private Joueur joueur;
    private GameManager gameManager;

    public EnnemiManager(Plateau plateau, Joueur joueur, GameManager gameManager) {
        this.plateau = plateau;
        this.joueur = joueur;
        this.gameManager = gameManager;
    }

    public void ajouterListeEnnemis(ArrayList<Ennemi> listeEnnemis) {
        ennemisEnAttente.addAll(listeEnnemis);
        // System.out.println("Ennemis attente : " + ennemisEnAttente);
    }


    public void relacherEnnemis() {
        if (!ennemisEnAttente.isEmpty()) {
            Ennemi ennemi = ennemisEnAttente.remove(0);
            plateau.ajouterEnnemi(ennemi);
        }
    }

    // -------- Gestion des déplacements des entités ---------

    // -------- Gestion des entités mortes ---------
    public synchronized void suppEntitesMortes() {
        verifierEtSupprimerEnnemisMorts();
        verifierEtSupprimerToursMortes();
    }

    public void verifierEtSupprimerEnnemisMorts() {
        // Utilisez un itérateur pour éviter les problèmes de modification de la liste
        // pendant la boucle
        Iterator<Ennemi> iterator = plateau.getEnnemis().iterator();
        while (iterator.hasNext()) {
            Ennemi ennemi = iterator.next();

            // Vérifiez si l'ennemi est mort (points de vie <= 0)
            if (!ennemi.getEstVivant()) {
                // Ajoutez des âmes au joueur
                gameManager.addAme(ennemi.getAmesGagnees());
                gameManager.getGame().setScore();
                // Retirez l'ennemi de la case sur laquelle il se trouve;
                Case caseEnnemi = plateau.getCase(ennemi.getX(), ennemi.getY());
                caseEnnemi.removeOccupePar(ennemi);

                // Retirez l'ennemi de la liste totale du plateau
                iterator.remove(); // Utilisez l'itérateur pour supprimer en toute sécurité de la liste
            } else if (ennemi.getX() == 0) {
                gameManager.setGameOver();
                // Retirez l'ennemi de la case sur laquelle il se trouve;
                Case caseEnnemi = plateau.getCase(ennemi.getX(), ennemi.getY());
                caseEnnemi.removeOccupePar(ennemi);
                // System.out.println(caseEnnemi.getOccupeParList());
                iterator.remove(); // Utilisez l'itérateur pour supprimer en toute sécurité de la liste
                gameManager.gameOver();
            }
            // ennemi.getGestionAttaqueEnnemi().arreterEnnemi();
        }
    }

    public void verifierEtSupprimerToursMortes() {
        // Utilisez un itérateur pour éviter les problèmes de modification de la liste
        // pendant la boucle
        Iterator<Tour> iterator = plateau.getTours().iterator();
        while (iterator.hasNext()) {
            Tour tour = iterator.next();

            // Vérifiez si l'ennemi est mort (points de vie <= 0)
            if (!tour.getEstVivant()) {

                if (tour instanceof TourTutela) {
                    // System.out.println("Tour tutela morte : " + tour.getX() + " " + tour.getY());
                    if (plateau.terrain[tour.getY()][tour.getX() + 1].getOccupePar() instanceof Ennemi) {
                        // System.out.println("Ennemi sur la case");
                        Ennemi e = (Ennemi) plateau.terrain[tour.getY()][tour.getX() + 1].getOccupePar();
                        // tour.attaqueCritiqueOuNormale(e, plateau);
                        if (e.creacritique(25) == 3){
                            tour.attaqueCritique(e, plateau);
                        } else {
                            tour.attaque(e, plateau);
                        }
                    }
                }
                // Retirez la tour de la case sur laquelle elle se trouve;
                Case tourCase = plateau.getCase(tour.getX(), tour.getY());
                tourCase.removeOccupePar(tour);

                // Retirez l'ennemi de la liste totale du plateau
                iterator.remove(); // Utilisez l'itérateur pour supprimer en toute sécurité de la liste
            }
        } 
    }

    // ------- Getters et Setters ---------
    public void addEnnemi(Ennemi ennemi) {
        ennemisEnAttente.add(ennemi);
    }

    public void removeEnnemi(Ennemi ennemi) {
        ennemisEnAttente.remove(ennemi);
    }

    public ArrayList<Ennemi> getEnnemis() {
        return ennemisEnAttente;
    }

    public void setEnnemis(ArrayList<Ennemi> ennemisEnAttente) {
        this.ennemisEnAttente = ennemisEnAttente;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

}
