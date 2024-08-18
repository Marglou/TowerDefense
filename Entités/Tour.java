package Entités;

// import java.util.ArrayList;


// import Design.Projectile;
import GestionTerrain.Plateau;

public class Tour extends Entites {
    // cette classe représente toutes les tours
    GestionAttaqueTour gestionAttaqueTour;

    // private ArrayList<Projectile> projectiles = new ArrayList<>();
    int coutPlacement;// représente le nombre d'âmes qu'il faut pour placer la tour
    int coutAmelioration;// représente le nombre d'âmes qu'il faut pour améliorer la tour en question
    int niveauActuel = 1;// représente le niveau d'amélioration de la tour
    int achat;// représente le prix d'achat de la tour

    boolean paralysieOuNon;// true

    int identifiant;
    double coupCritique = 1;

    int ligne = getX();
    int colonne = getY();
    String description;

    static int id = 0;// identifiant de la tour (peut par exemple permettre de reconnaître quelle tour
                      // a été améliorée)

    // ------- Amélioration de la tour -------
    public void améliorerTour() {
        if (this.niveauActuel == 2) {
            ameliorationAuNiveau2();

        } else if (this.niveauActuel == 3) {
            ameliorationAuNiveau3();
        }
    }

    public void ameliorationAuNiveau3() {
    }

    public void ameliorationAuNiveau2() {
    }

    // ------ Attacque de la tour ------
    public void attaqueCritique(Ennemi e, Plateau plateau) {
    }

    public void attaque(Ennemi e, Plateau plateau) {
    }

    public GestionAttaqueTour getGestionAttaqueTour() {
        return this.gestionAttaqueTour;
    }

    public void attaqueCritiqueOuNormale(Ennemi e, Plateau plateau) {
        if (this instanceof TourVindicta) {
            if (creacritique(7) == 4) {
                this.attaqueCritique(e, plateau);
            } else {
                this.attaque(e, plateau);
            }
        } else if (!(this instanceof TourTutela)) {
            if (creacritique(25) == 4) {
                this.attaqueCritique(e, plateau);
            } else {
                this.attaque(e, plateau);
            }
        } 
    }

    public void creationAttaque(Plateau p) {
        this.gestionAttaqueTour = new GestionAttaqueTour(this, p);
        this.gestionAttaqueTour.lancerAttaque(this);
    }

    // Class interne
    public static class GestionAttaqueTour {
        private final Plateau plateau;
        private final Tour tour;

        public GestionAttaqueTour(Tour t, Plateau plateau) {
            this.tour = t;
            this.plateau = plateau;
        }

        public void verificationPourAttaque() {
            for (int i = this.tour.getColonne(); i < plateau.getLargeur(); i++) {
                // System.out.println(this.tour.getLigne() + " " + this.tour.getX());
                // System.out.println("Ligne " + this.tour.getLigne());
                
                if (plateau.terrain[this.tour.getLigne()][i].ennemiSurLaCase()) {

                    // System.out.println("Ennemi touché " + plateau.terrain[this.tour.getLigne()][i].getOccupeParList().get(0) + " : " + plateau.terrain[this.tour.getLigne()][i].getOccupeParList().get(0).getPointsDeVie());
                    this.tour.attaqueCritiqueOuNormale(
                            (Ennemi) plateau.terrain[this.tour.getLigne()][i].getOccupeParList().get(0), plateau);
                    estVivant(plateau.terrain[this.tour.getLigne()][i].getOccupeParList().get(0));
                    // System.out.println("Ennemi touché " + plateau.terrain[this.tour.getLigne()][i].getOccupeParList().get(0) + " : " + plateau.terrain[this.tour.getLigne()][i].getOccupeParList().get(0).getPointsDeVie());

                    break; // nécessaire de mettre un break pour ne pas attaquer plusieurs ennemis à la fois
                }
            }
        }

        public void lancerAttaque(Tour t) {
            Thread thread = new Thread(() -> {
                while (this.tour.getEstVivant()) {
                    // System.out.println("Attaque de la tour " + this.tour.getIdentifiant() + " en
                    // cours");
                    // Vérifie s'il y a des ennemis devant la tour
                    verificationPourAttaque();
                    try {
                        Thread.sleep((long) this.tour.getVitesseDAttaque());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

    // // ------- Gestion des projectiles -------
    // public void addProjectile(Projectile projectile) {
    //     this.projectiles.add(projectile);
    // }

    // public ArrayList<Projectile> getProjectiles() {
    //     return this.projectiles;
    // }

    // public void removeProjectile(Projectile projectile) {
    //     this.projectiles.remove(projectile);
    // }



    /*
     * public int verifAmelioration(int ames) {
     * if (this instanceof TourBasique) {
     * if (this.niveauActuel == 1 && ames >= this.coutAmelioration) {
     * return ((TourBasique) this).ameliorationAuNiveau2(ames);
     * } else if (this.niveauActuel == 2 && ames >= this.coutAmelioration) {
     * return ((TourBasique) this).ameliorationAuNiveau3(ames);
     * } else {
     * System.out.println(
     * "La tour ne peut pas être améliorée car elle a soit atteinte le niveau maximal, soit vous n'avez pas assez d'âmes."
     * );
     * }
     * } else if (this instanceof TourVocator) {
     * if (this.niveauActuel == 1 && ames >= this.coutAmelioration) {
     * return ((TourVocator) this).ameliorationAuNiveau2(ames);
     * } else if (this.niveauActuel == 2 && ames >= this.coutAmelioration) {
     * return ((TourVocator) this).ameliorationAuNiveau3(ames);
     * } else {
     * System.out.println(
     * "La tour ne peut pas être améliorée car elle a soit atteinte le niveau maximal, soit vous n'avez pas assez d'âmes."
     * );
     * }
     * } else if (this instanceof TourCuratis) {
     * if (this.niveauActuel == 1 && ames >= this.coutAmelioration) {
     * return ((TourCuratis) this).ameliorationAuNiveau2(ames);
     * } else if (this.niveauActuel == 2 && ames >= this.coutAmelioration) {
     * return ((TourCuratis) this).ameliorationAuNiveau3(ames);
     * } else {
     * System.out.println(
     * "La tour ne peut pas être améliorée car elle a soit atteinte le niveau maximal, soit vous n'avez pas assez d'âmes."
     * );
     * }
     * } else if (this instanceof TourTutela) {
     * if (this.niveauActuel == 1 && ames >= this.coutAmelioration) {
     * return ((TourTutela) this).ameliorationAuNiveau2(ames);
     * } else if (this.niveauActuel == 2 && ames >= this.coutAmelioration) {
     * return ((TourTutela) this).ameliorationAuNiveau3(ames);
     * } else {
     * System.out.println(
     * "La tour ne peut pas être améliorée car elle a soit atteinte le niveau maximal, soit vous n'avez pas assez d'âmes."
     * );
     * }
     * } else if (this instanceof TourVindicta) {
     * if (this.niveauActuel == 1 && ames >= this.coutAmelioration) {
     * return ((TourVindicta) this).ameliorationAuNiveau2(ames);
     * } else if (this.niveauActuel == 2 && ames >= this.coutAmelioration) {
     * return ((TourVindicta) this).ameliorationAuNiveau3(ames);
     * } else {
     * System.out.println(
     * "La tour ne peut pas être améliorée car elle a soit atteinte le niveau maximal, soit vous n'avez pas assez d'âmes."
     * );
     * }
     * } else if (this instanceof TourCaemento) {
     * if (this.niveauActuel == 1 && ames >= this.coutAmelioration) {
     * return ((TourCaemento) this).ameliorationAuNiveau2(ames);
     * } else if (this.niveauActuel == 2 && ames >= this.coutAmelioration) {
     * return ((TourCaemento) this).ameliorationAuNiveau3(ames);
     * } else {
     * System.out.println(
     * "La tour ne peut pas être améliorée car elle a soit atteinte le niveau maximal, soit vous n'avez pas assez d'âmes."
     * );
     * }
     * } else if (this instanceof TourIanis) {
     * if (this.niveauActuel == 1 && ames >= this.coutAmelioration) {
     * return ((TourIanis) this).ameliorationAuNiveau2(ames);
     * } else if (this.niveauActuel == 2 && ames >= this.coutAmelioration) {
     * return ((TourIanis) this).ameliorationAuNiveau3(ames);
     * } else {
     * System.out.println(
     * "La tour ne peut pas être améliorée car elle a soit atteinte le niveau maximal, soit vous n'avez pas assez d'âmes."
     * );
     * }
     * }
     * return ames;
     * }
     * 
     * 
     */

    // -----------------GETTERS ET SETTERS-----------------
    public int getLigne() {
        return this.ligne;
    }

    public int getColonne() {
        return this.colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getIdentifiant() {
        return this.identifiant;
    }

    public double getDegats() {
        return this.degatsInfliges;
    }

    public void setDegats(double d) {
        this.degatsInfliges = d;
    }

    public int getCoutPlacement() {
        return this.coutPlacement;
    }

    public int getCoutAmelioration() {
        return this.coutAmelioration;
    }

    public int getNiveauActuel() {
        return this.niveauActuel;
    }

    public void setNiveauActuel() {
        this.niveauActuel++;
    }

    // public JLabel getSpriteContainer() {
    // return this.spriteContainer;
    // }

    // public void setPv(double pv) {
    // this.pointsDeVie = pv;
    // }

    // public double getPv() {
    // return this.pointsDeVie;
    // }

    public double getCoupCritique() {
        return this.coupCritique;
    }

    public void setCoupCritique(double c) {
        this.coupCritique = c;
    }

    public int getAchat() {
        return this.achat;
    }

    public void setAchat(int a) {
        this.achat = a;
    }

    public String getDescription() {
        return this.description;
    }
}
