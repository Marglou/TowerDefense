package AffichePlateau;
import java.util.ArrayList;
import Entités.*;

// Enumération des types d'ennemis
enum TypeEnnemi {
    POLTERGEIST,
    YUREI,
    ONI,
    DAME_BLANCHE,
    DOPPELGANGER,
    INFIRMIERE,
    DJINN,
    LA_PESTE,
    DRAKUL
}


public class ListeEnnemis {

    private TypeEnnemi[] ennemisDisponibles;

     // Tableau de pourcentages pour la liste facile
    private double[] pourcentagesFacile = {0.50, 0.30, 0.20, 0, 0, 0, 0, 0, 0};

    // Tableau de pourcentages pour la liste moyen
    private double[] pourcentagesMoyen = {0.05, 0.1, 0.1, 0.3, 0.3, 0.15, 0, 0, 0};

    // Tableau de pourcentages pour la liste difficile
    private double[] pourcentagesDifficile = {0, 0, 0, 0.125, 0.125, 0.15, 0.2, 0.2, 0.2};

    public ListeEnnemis() {
        ennemisDisponibles = TypeEnnemi.values(); // Récupérer tous les types d'ennemis
    }

    // Fonction pour retourner le type d'ennemi à créer en fonction des pourcentages
    private TypeEnnemi getTypeEnnemi(double[] pourcentages) {
        double rand = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < pourcentages.length; i++) {
            // additionne les probabilités du tableau donné en argumant
            cumulativeProbability += pourcentages[i];
            // si la probabilité cumulée est plus grande que le nombre aléatoire
            // renvoie le type d'ennemi correspondant
            if (rand <= cumulativeProbability) {
                return ennemisDisponibles[i];
            }
        }

        // Par défaut, retourner le dernier type d'ennemi
        return ennemisDisponibles[ennemisDisponibles.length - 1];
    }

    // crée un ennemi selon les pourceentages donnés en argument
    public Ennemi genererEnnemi(double[] pourcentages) {
        // Récupérer le type d'ennemi à créer
        TypeEnnemi typeEnnemi = getTypeEnnemi(pourcentages);

        // Créer une instance spécifique selon le type d'ennemi
        switch (typeEnnemi) {
            case ONI:
                return new Oni();
            // Ajouter d'autres cas pour chaque type d'ennemi
            case POLTERGEIST:
                return new Poltergeist();
            case YUREI:
                return new Yurei();
            case DAME_BLANCHE:
                return new DameBlanche();
            case DOPPELGANGER:
                return new Doppelganger();
            case INFIRMIERE:
                return new Infirmiere();
            case DJINN:
                return new Djinn();
            case LA_PESTE:
                return new LaPeste();
            case DRAKUL:
                return new Drakul();
            default:
                throw new IllegalArgumentException("Type d'ennemi non reconnu");
        }
    }

    // Fonction pour générer une liste d'ennemis en fonction des pourcentages
    public ArrayList<Ennemi> genererListeEnnemis(int n, double[] pourcentages) {
        ArrayList<Ennemi> listeEnnemisGeneres = new ArrayList<>();

        for (int j = 0; j < n; j++) {
            // Récupérer le type d'ennemi à créer
            TypeEnnemi typeEnnemi = getTypeEnnemi(pourcentages);

            // Créer une instance spécifique selon le type d'ennemi
            switch (typeEnnemi) {
                case ONI:
                    listeEnnemisGeneres.add(new Oni());
                    break;
                // Ajouter d'autres cas pour chaque type d'ennemi
                case POLTERGEIST:
                    listeEnnemisGeneres.add(new Poltergeist());
                    break;
                case YUREI:
                    listeEnnemisGeneres.add(new Yurei());
                    break;
                case DAME_BLANCHE:
                    listeEnnemisGeneres.add(new DameBlanche());
                    break;
                case DOPPELGANGER:
                    listeEnnemisGeneres.add(new Doppelganger());
                    break;
                case INFIRMIERE:
                    listeEnnemisGeneres.add(new Infirmiere());
                    break;
                case DJINN:
                    listeEnnemisGeneres.add(new Djinn());
                    break;
                case LA_PESTE:

                    listeEnnemisGeneres.add(new LaPeste());
                    break;
                case DRAKUL:
                    listeEnnemisGeneres.add(new Drakul());
                    break;
                
                default:
                    // Utiliser la classe de base Ennemi si le type n'est pas reconnu
                    listeEnnemisGeneres.add(new Ennemi());
                    break;
            }
        }

        return listeEnnemisGeneres;
    }

    private ArrayList<Ennemi> genererListeFacile() {
        return genererListeEnnemis(5, pourcentagesFacile);
    }

    private ArrayList<Ennemi> genererListeMoyen() {
        return genererListeEnnemis(7, pourcentagesMoyen);
    }

    private ArrayList<Ennemi> genererListeDifficile() {
        return genererListeEnnemis(10, pourcentagesDifficile);
    }

    public  ArrayList<Ennemi> genererListeEnnemis(String difficulte) {
        switch (difficulte) {
            case "facile":
                return genererListeFacile();
            case "moyen":
                return genererListeMoyen();
            case "difficile":
                return genererListeDifficile();
            default:
                return genererListeFacile();
        }
    }
}

