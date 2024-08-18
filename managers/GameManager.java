package managers;

import java.awt.*;
import javax.swing.*;
import java.util.Iterator;

import AffichePlateau.Game;
import Entités.Ennemi;
import Entités.Tour;
import Entités.TourCaemento;
import Entités.TourIanis;
import Entités.TourVindicta;
import Game.Joueur;
import Game.Partie;
import Design.MyButtons;
import Design.MyFont;
import GestionTerrain.Plateau;
import Effets.Bonus;

public class GameManager {

	private Game game;
	private Bonus bonusChoisi = null;
	private JPanel gameFin = new JPanel(null);
	private Font myFont = null;

	public GameManager(Game game) {
		this.game = game;
		iniGameFin();
		myFont = MyFont.getGameOverFont(125);
	}

	// ------- Gestion du Game Over ---------
	public void gameOver() {
		if (isGameOver()) {
			updateScore();
			// game.getContentPane().removeAll();
			getJoueur().addGold(20);
			gameOverText();
			game.getWindow().replacePanel(gameFin, "");
			game.reset();
			// setGameOver();
		}
	}

	private void updateScore() {
		if (game.getScore() > getJoueur().getMeilleurScore()) {
			// System.out.println("Score : " + game.getScore());
			// System.out.println("Meilleur score : " + getJoueur().getMeilleurScore());
			getJoueur().setMeilleurScore(game.getScore());
			// System.out.println("Meilleur score : " + getJoueur().getMeilleurScore());
		}
	}

	// ------- Gestion de la victoire ---------
	public void winGame() {
		if (getPlateau().getEnnemis().isEmpty() && getEnnemiManager().getEnnemis().isEmpty()
				&& game.getGameScreen().getVaguesRestantes() == 0) {
			win();
			game.reset();
			// game.getContentPane().removeAll();
			victoireText();
			game.getWindow().replacePanel(gameFin, "");
		}
	}

	public void win() {
		switch (getDifficulte()) {
			case "facile":
				getJoueur().addGold(80);
				break;
			case "moyen":
				getJoueur().addGold(150);
				break;
			case "difficile":
				getJoueur().addGold(300);
				break;
		}

		if (getTypeGame().equals("histoire")) {
			if (getJoueur().getLvl() == game.getLvl()) {
				getJoueur().setLvl();
				if (getJoueur().getLvl() == 4) {
					getJoueur().addTour(new TourVindicta());
					getJoueur().addGold(100);

				} else if (getJoueur().getLvl() == 7) {
					getJoueur().addTour(new TourCaemento());
					getJoueur().addGold(150);
				} else if (getJoueur().getLvl() == 8) {
					getJoueur().addTour(new TourIanis());
					getJoueur().addGold(100);
				}
			}
		}
	}

	public void deplaceEnnemi() {
		Iterator<Ennemi> iterator = game.getPlateau().getEnnemis().iterator();
		while (iterator.hasNext()) {
			Ennemi ennemi = iterator.next();
			ennemi.deplacement(game.getPlateau());
			if (ennemi.getX() == 0) {
				// L'ennemi atteint la position -1
				iterator.remove();
			}
		}
	}

	// --------- Affichage dans le game -----------
	public void setGameOver() {
		game.setGameOver(true);
	}

	public void iniGameFin() {
		gameFin.setBackground(Color.BLACK);
		JButton menu = MyButtons.initButton("Menu", 500, 600, 100, 40, Color.RED, Color.BLACK);
		menu.addActionListener(e -> {
			// window.switchToPage("Home");
			gameFin.removeAll();
			JPanel t = game.getWindow().getHomePanel();
			game.getWindow().replacePanel(t, "Home");
			game.reset();

		});
		gameFin.add(menu);

		JButton retour = MyButtons.initButton("Retour", 650, 600, 100, 40, Color.RED, Color.BLACK);
		retour.addActionListener(e -> {
			// window.switchToPage("Home");
			if (game.getTypeGame().equals("histoire")) {
				gameFin.removeAll();
				game.getWindow().replacePanel(game.getWindow().getHistoirePanel(), "Histoire");
			} else {
				gameFin.removeAll();
				game.getWindow().replacePanel(game.getWindow().getNormalPanel(), "Normal");
			}
			game.reset();

		});
		gameFin.add(retour);

		JButton recommencer = MyButtons.initButton("Recommencer", 850, 600, 150, 40, Color.RED, Color.BLACK);
		recommencer.addActionListener(e -> {
			// window.switchToPage("Home");
			gameFin.removeAll();
			Partie newPartie = new Partie(game.getWindow(), getJoueur(), getDifficulte(), getTypeGame(), game.getLvl());
			game.getWindow().replacePanel(newPartie.getPanel(), "");
			game.reset();
			
		});
		gameFin.add(recommencer);

	}

	public void victoireText() {
		JLabel textArea = new JLabel("GAGNE !!!");
		textArea.setFont(myFont);
		textArea.setBounds(300, 75, 350, 250);
		textArea.setForeground(Color.WHITE);
		gameFin.add(textArea);
	}

	public void gameOverText() {
		JLabel textArea = new JLabel("GAME OVER");
		textArea.setFont(myFont);
		textArea.setBounds(300, 75, 585, 250);
		textArea.setFont(myFont);
		textArea.setForeground(Color.RED);
		gameFin.add(textArea);
	}

	public void startTimerDegatsContinus() {
		if (getJoueur().getBonusChoisi() != null
				&& getJoueur().getBonusChoisi().getClass().getSimpleName().equals("DegatsContinus")) {
			game.startTimerDegatsContinus();
		}
	}

	// --------- Gestion des tours -----------
	public void acheterTour(Tour tour) {
		if (game.isAmeEnough(tour.getCoutPlacement())) {
			game.removeAme(tour.getCoutPlacement());
		}
	}

	public void amelioreTour(Tour tour) {
		if (game.isAmeEnough(tour.getCoutAmelioration())) {
			game.removeAme(tour.getCoutAmelioration());
			tour.setNiveauActuel();
		}
	}

	// --------- Getters et Setters ---------
	public Game getGame() {
		return this.game;
	}

	public int getAme() {
		return game.getAme();
	}

	public void setAme(int ame) {
		game.setAme(ame);
	}

	public boolean isAmeEnough(int ame) {
		return game.isAmeEnough(ame);
	}

	public void addAme(int ame) {
		game.addAme(ame);
	}

	public void removeAme(int ame) {
		game.removeAme(ame);
	}

	public Joueur getJoueur() {
		return game.getJoueur();
	}

	public void setBonus(Bonus bonus) {
		this.bonusChoisi = bonus;
	}

	public Bonus getBonus() {
		return this.bonusChoisi;
	}

	public String getDifficulte() {
		return game.getDifficulte();
	}

	public String getTypeGame() {
		return game.getTypeGame();
	}

	public boolean isGameOver() {
		return game.getGameOver();
	}

	public Plateau getPlateau() {
		return game.getPlateau();
	}

	public EnnemiManager getEnnemiManager() {
		return game.getEnnemiManager();
	}

}
