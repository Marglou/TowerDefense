package AffichePlateau;

import javax.swing.*;

import Entités.Ennemi;
// import Entités.TourBasique;
// import Entités.TourCaemento;
// import Entités.TourCuratis;
// import Entités.TourIanis;
// import Entités.TourTutela;
// import Entités.TourVindicta;
// import Entités.TourVocator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import Game.Joueur;
import Game.Main;
import GestionTerrain.Plateau;
import managers.EnnemiManager;
import managers.GameManager;

public class Game extends JPanel implements Runnable {

	private Joueur joueur;
	private GameScreen gameScreen;
	private Plateau plateau;
	private EnnemiManager ennemiManager;
	private GameManager gameManager;
	private BufferedImage img;
	private BufferedImage decorImage;
	private Thread gameThread;
	private Toolbar toolbar;
	private Main window;
	private JPanel mainPanel;

	private final String typeGame;
	private final String difficulte;
	private Timer ameTime;
	private Timer timerDegatsContinus;
	private int lvl;
	private int ame = 500;
	private int score = 0;

	private boolean gameOver = false;
	private boolean running = true;

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	// Nouveau constructeur avec Main
	public Game(Main window, Joueur joueur, String difficulte, String typeGame, int lvl) {
		this.window = window;
		this.joueur = joueur;
		this.plateau = new Plateau(joueur);
		this.difficulte = difficulte;
		this.typeGame = typeGame;
		this.lvl = lvl;
		this.gameManager = new GameManager(this);
		this.ennemiManager = new EnnemiManager(plateau, joueur, gameManager);
		this.toolbar = new Toolbar(window, gameManager);

		// Load the background image
		importImg(img, "..\\Res\\test1.png");
		importBackground(decorImage, "..\\Res\\fondterraintest.jpg");

		this.gameScreen = new GameScreen(img, joueur, toolbar, gameManager);

		this.mainPanel = new JPanel(new BorderLayout());
		toolbar.setBounds(0, 540, this.getWidth(), 210);

		// GameScreen et plateau
		gameScreen.setBounds(175, 50, 800, 400);
		JPanel background = new JPanel(null) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (decorImage != null) {
					g.drawImage(decorImage, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};

		background.setBounds(0, 0, this.getWidth(), 540);

		// Initialize the timer for the incrementation of ames
		startSoulIncrement();
		gameManager.startTimerDegatsContinus();

		background.add(gameScreen);
		mainPanel.add(toolbar, BorderLayout.SOUTH);
		mainPanel.add(background, BorderLayout.CENTER);
	}

	/*
	 * public Game(Joueur joueur, String difficulte, String typeGame) {
	 * this.joueur = joueur;
	 * this.plateau = new Plateau(joueur);
	 * this.difficulte = difficulte;
	 * this.typeGame = typeGame;
	 * this.gameManager = new GameManager(this);
	 * this.ennemiManager = new EnnemiManager(plateau, joueur, gameManager);
	 * this.toolbar = new Toolbar(window, gameManager);
	 * 
	 * // Load the background image
	 * importBackground(decorImage, "decorTest.png");
	 * importImg(img, "test1.png");
	 * 
	 * this.gameScreen = new GameScreen(img, joueur, toolbar, gameManager);
	 * 
	 * // Panel par defaut
	 * setSize(1200, 750);
	 * setDefaultCloseOperation(EXIT_ON_CLOSE);
	 * setLocationRelativeTo(null);
	 * setResizable(false);
	 * 
	 * // ToolBar
	 * this.mainPanel = new JPanel(null);
	 * toolbar.setBounds(0, 540, this.getWidth(), 210);
	 * mainPanel.add(toolbar);
	 * 
	 * // GameScreen et plateau
	 * gameScreen.setBounds(230, 50, 800, 400);
	 * JPanel background = new JPanel(null) {
	 * 
	 * @Override
	 * protected void paintComponent(Graphics g) {
	 * super.paintComponent(g);
	 * if (decorImage != null) {
	 * g.drawImage(decorImage, 0, 0, getWidth(), getHeight(), this);
	 * }
	 * }
	 * };
	 * background.setBounds(0, 0, this.getWidth(), 540);
	 * background.add(gameScreen);
	 * 
	 * mainPanel.add(background);
	 * 
	 * int topMargin = 35;
	 * mainPanel.setBorder(BorderFactory.createEmptyBorder(topMargin, 0, 0, 0));
	 * 
	 * // Initialize the timer for the incrementation of ames
	 * startSoulIncrement();
	 * 
	 * add(mainPanel);
	 * setVisible(true);
	 * }
	 */

	private void importImg(BufferedImage img, String path) {
		try (InputStream is = getClass().getResourceAsStream(path)) {
			this.img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void importBackground(BufferedImage decorImage, String path) {
		try (InputStream is = getClass().getResourceAsStream(path)) {
			this.decorImage = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// démarre le jeu
	public void start() {
		gameThread = new Thread(this) {
		};

		gameThread.start();
	}

	// update game
	private void updateGame() {
		gameScreen.updateGame();
	}

	// public static void main(String[] args) {
	// Joueur joueur = new Joueur("test");
	// joueur.getToursPossedes().add(new TourBasique());
	// joueur.getToursPossedes().add(new TourIanis());
	// joueur.getToursPossedes().add(new TourVindicta());
	// joueur.getToursPossedes().add(new TourCaemento());
	// joueur.getToursPossedes().add(new TourVocator());
	// joueur.getToursPossedes().add(new TourTutela());
	// joueur.getToursPossedes().add(new TourCuratis());
	// Main window = new Main();
	// Game game = new Game(window, joueur, "facile", "normal", 1);
	// JFrame frame = new JFrame("Tower Defense");
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.setSize(1200, 750);
	// frame.setResizable(false);
	// frame.setLocationRelativeTo(null);
	// frame.add(game.getMainPanel());
	// frame.setVisible(true);
	// // Game game = new Game(joueur, "facile", "marathon");
	// game.start();
	// }

	// public static void play(Joueur joueur, String difficulte, String typeGame) {
	// Game game = new Game(joueur, difficulte, typeGame);
	// game.start();
	// }

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();


		while (this.running) {

			// if (this.pause) {

			// Render
			if (System.nanoTime() - lastFrame >= timePerFrame) {
				SwingUtilities.invokeLater(() -> {
					gameScreen.repaint();
				});

				lastFrame = System.nanoTime();
			}

			// Update
			if (System.nanoTime() - lastUpdate >= timePerUpdate) {
				lastUpdate = System.nanoTime();
			}

			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				updateGame();
				verificationVictoireOuDefaite();
				lastTimeCheck = System.currentTimeMillis();
			}
		}

	}

	// ------- Gestion de la pause ---------

	private void verificationVictoireOuDefaite() {
		// System.out.println("Verification Victoire ou Defaite");
		if (aGagne()) {
			gameManager.winGame();
			running = false;
			stop();

		} else if (gameOver) {
			gameManager.gameOver();
			running = false;
			stop();;
		}
	}

	
	private boolean aGagne() {
		if (plateau.getEnnemis().isEmpty() && ennemiManager.getEnnemis().isEmpty()
				&& gameScreen.getVaguesRestantes() == 0 && !gameOver) {
			return true;
		}
		return false;
	}

	public void reset() {
		this.running = false;
		ameTime.stop();
		if (timerDegatsContinus != null) {
			timerDegatsContinus.stop();
		}
		gameScreen.stopEnnemiTimers();
		this.plateau.getEnnemis().clear();
		this.ennemiManager.getEnnemis().clear();
		this.plateau.getTours().clear();
		this.gameOver = false;
		
	}

	public void stop (){
		gameThread.interrupt();
	}

	// ------- Incrementation des ames ---------

	private void startSoulIncrement() {
		int soulIncrementInterval = 5000; // Set the interval in milliseconds
		ameTime = new Timer(soulIncrementInterval, e -> {
			SwingUtilities.invokeLater(() -> {
				addAme(40); // Adjust the amount of souls to be added
				toolbar.update(); // Update label in toolbar
			});
		});
		ameTime.start();
	}

	public void startTimerDegatsContinus() {
		int temps = 10000; // Set the interval in milliseconds
		timerDegatsContinus = new Timer(temps, e -> {
			for (Ennemi ennemi : plateau.getEnnemis()) {
				System.out.println("Ennemi" + ennemi.getPointsDeVie());
				joueur.getBonusChoisi().appliquerBonus(ennemi);
				if (ennemi.getPointsDeVie() <= 0) {
					ennemi.setEstVivant(false);
				}
				System.out.println("Ennemi" + ennemi.getPointsDeVie());
			}
		});
		timerDegatsContinus.setRepeats(true);
		timerDegatsContinus.start();
	}

	// --------- GETTERS & SETTERS ------------
	public JPanel getMainPanel() {
		return mainPanel;
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void setWindow(Main window) {
		this.window = window;
	}

	public Main getWindow() {
		return this.window;
	}

	public String getDifficulte() {
		return this.difficulte;
	}

	public String getTypeGame() {
		return this.typeGame;
	}

	public int getLvl() {
		return this.lvl;
	}

	public EnnemiManager getEnnemiManager() {
		return this.ennemiManager;
	}

	public GameManager getGameManager() {
		return this.gameManager;
	}

	public GameScreen getGameScreen() {
		return this.gameScreen;
	}

	public Plateau getPlateau() {
		return this.plateau;
	}

	public Joueur getJoueur() {
		return this.joueur;
	}

	public void setAme(int ame) {
		this.ame = ame;
	}

	public int getAme() {
		return this.ame;
	}

	public void addAme(int ame) {
		this.ame += ame;
	}

	public void removeAme(int ame) {
		this.ame -= ame;
	}

	public boolean isAmeEnough(int ame) {
		return this.ame >= ame;
	}

	public void setScore() {
		this.score++;
		toolbar.update();
	}

	public int getScore() {
		return this.score;
	}

}
