package Game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Effets.Bonus;
import Effets.SanteAmelioree;
import Entités.Tour;
import Entités.TourBasique;

public class Joueur implements Serializable {
    // cette classe va représenter le joueur
    private int gold = 200;
    private String name;
    private int lvl = 1;
    private ArrayList<Tour> toursPossedes = new ArrayList<Tour>();
    private ArrayList<Bonus> bonusPossedes = new ArrayList<Bonus>();
    private int meilleurScore = 0;
    private Bonus bonusChoisi;

    public Joueur(String name) {
        this.name = name;
        toursPossedes.add(new TourBasique());
        // toursPossedes.add(new TourIanis());
        // toursPossedes.add(new TourVindicta());
        // toursPossedes.add(new TourCaemento());
        // toursPossedes.add(new TourVocator());
        // toursPossedes.add(new TourTutela());
        // toursPossedes.add(new TourCuratis());

        bonusPossedes.add(new SanteAmelioree());
        // bonusPossedes.add(new DefenseAmelioree());
    }

    // Ajoutez ces méthodes pour gérer la sérialisation
    public void sauvegarderJoueur(String cheminDuFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cheminDuFichier))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Joueur chargerJoueur() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Game\\sauvegarde.ser"))) {
            return (Joueur) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getGold() {
        return gold;
    }

    public void setLvl() {
        this.lvl++;
    }

    public void addTour(Tour tour) {
        toursPossedes.add(tour);
    }

    public void addBonus(Bonus bonus) {
        bonusPossedes.add(bonus);
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void removeGold(int gold) {
        this.gold -= gold;
    }

    public boolean isGoldEnough(int gold) {
        return this.gold >= gold;
    }

    public ArrayList<Tour> getToursEnPossession() {
        return toursPossedes;
    }

    public void setMeilleurScore(int meilleurScore) {
        this.meilleurScore = meilleurScore;
    }

    public int getMeilleurScore() {
        return meilleurScore;
    }

    public boolean acheterTourShop(Tour tour) {
        if (isGoldEnough(tour.getAchat())) {
            removeGold(tour.getAchat());
            toursPossedes.add(tour);
            return true;
        }
        return false;

    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public ArrayList<Tour> getToursPossedes() {
        return toursPossedes;
    }

    public void setToursPossedes(ArrayList<Tour> toursPossedes) {
        this.toursPossedes = toursPossedes;
    }

    public ArrayList<Bonus> getBonusPossedes() {
        return bonusPossedes;
    }

    public void setBonusPossedes(ArrayList<Bonus> bonusPossedes) {
        this.bonusPossedes = bonusPossedes;
    }

    public boolean acheterBonus(Bonus bonusAchete) {
        if (isGoldEnough(bonusAchete.getPrix())) {
            removeGold(bonusAchete.getPrix());
            bonusPossedes.add(bonusAchete);
            return true;
        }
        return false;
    }

    public void setBonusChoisi(Bonus bonus) {
        this.bonusChoisi = bonus;
    }

    public Bonus getBonusChoisi() {
        return this.bonusChoisi;
    }

    public boolean isPseudoIdentique(String playerName) {
        return this.name.equals(playerName);
    }

}
