package AffichePlateau;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import Entités.Ennemi;
import Entités.Tour;
import Game.Joueur;
import GestionTerrain.Plateau;
import managers.EnnemiManager;
import managers.GameManager;

import java.util.ArrayList;

public class GameScreen extends JPanel {

    private Joueur joueur;
    private Toolbar toolbar;
    private Plateau plateau;
    private BufferedImage img;
    private int tileSize = 32;
    private MouseClickListener mouseClickListener;
    private int clickedTileX = -1;
    private int clickedTileY = -1;
    private EnnemiManager ennemiManager;
    private GameManager gameManager;
    private ListeEnnemis listeEnnemisGenerator = new ListeEnnemis();

    private String difficulte;

    // Compte à rebours avant la prochaine vague
    private Timer vagueTimer;
    private Timer relacherEnnemiTimer;

    private Timer countdownTimer;
    private JLabel tempsAvantVagueLabel;
    private volatile int tempsRestant;
    private int nbVague;
    private int vaguesRestantes;
    private String typeGame;

    private int delayVague;
    private int delayEnnemi;

    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    // --------- Constructeur --------
    public GameScreen(BufferedImage img, Joueur joueur, Toolbar toolbar, GameManager gameManager) {

        this.toolbar = toolbar;
        this.gameManager = gameManager;
        this.joueur = gameManager.getJoueur();
        this.ennemiManager = gameManager.getGame().getEnnemiManager();
        this.plateau = ennemiManager.getPlateau();

        // niveau de difficulté
        this.difficulte = gameManager.getDifficulte();
        this.typeGame = gameManager.getTypeGame();
        this.nbVague = nbVague(difficulte);
        this.vaguesRestantes = nbVague;
        this.delayVague = delay(difficulte);
        this.delayEnnemi = delayEnnemi(difficulte);

        initGameScreen(img, difficulte);
        initCountdownTimer();
        initEnnemiTimers();

    }

    // --------- vagues d'ennemis --------
    private int nbVague(String s) {
        int n = 0;
        switch (s) {
            case "facile":
                n = 2;
                break;
            case "moyen":
                n = 3;
                break;
            case "difficile":
                n = 4;
                break;
            case "marathon":
                n = 50;
                break;
        }
        return n;
    }

    private int delayEnnemi(String s) {
        int n = 0;
        switch (s) {
            case "facile":
                n = 3000;
                break;
            case "moyen":
                n = 3000;
                break;
            case "difficile":
                n = 3000;
                break;
            case "marathon":
                n = 3000;
                break;
        }
        return n;
    }

    private int delay(String s) {
        int n = 0;
        switch (s) {
            case "facile":
                n = 15000;
                break;
            case "moyen":
                n = 12000;
                break;
            case "difficile":
                n = 10000;
                break;
            case "marathon":
                n = 8000;
                break;
        }
        return n;
    }

    // --------- compte à rebours avant la prochaine vague --------

    // Initialisation du timer de compte à rebours

    private void initCountdownTimer() {
        tempsRestant = delayVague / 1000; // Divisez par 1000 pour obtenir le temps en secondes
        tempsAvantVagueLabel = new JLabel("Temps avant la prochaine vague : " +
                tempsRestant + " secondes");
        tempsAvantVagueLabel.setForeground(Color.WHITE);
        add(tempsAvantVagueLabel);

        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tempsRestant > 0 && vaguesRestantes > 0) {
                    tempsRestant--;
                    tempsAvantVagueLabel.setText("Temps avant la prochaine vague : " +
                            tempsRestant + " secondes");
                } else if (tempsRestant <= 0 && vaguesRestantes > 0) {
                    // Réinitialisez tempsRestant à la valeur initiale du délai
                    tempsRestant = delayVague / 1000;
                    tempsAvantVagueLabel.setText("Temps avant la prochaine vague : " +
                            tempsRestant + " secondes");

                    // Utilisez SwingUtilities.invokeLater() pour mettre à jour l'interface
                    // graphique
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            relacherEnnemiTimer.restart();

                            // Générez une nouvelle liste d'ennemis en attente
                            genereVague(difficulte, typeGame);

                            // Décrémentez le nombre de vagues restantes
                            if (typeGame.equals("marathon") && vaguesRestantes == 20) {
                                vaguesRestantes++;
                            } else {
                                vaguesRestantes--;
                            }
                            // System.out.println("Vagues restantes : " + vaguesRestantes);
                            if (vaguesRestantes > 0) {
                                countdownTimer.restart();
                            } else {
                                tempsAvantVagueLabel.setText("Aucune vague restante");
                            }
                        }
                    });
                }
            }
        });
        countdownTimer.start();

    }

    // Initialisation des timers
    private void initEnnemiTimers() {
        initRelacherEnnemiTimer();
        // Démarrer les timers
        relacherEnnemiTimer.start();
    }

    // Générer une nouvelle vague d'ennemis
    public void genereVague(String difficulte, String typeGame) {
        if (typeGame.equals("marathon")) {

            if (vaguesRestantes >= nbVague - 10) {
                ArrayList<Ennemi> nouvelleListeEnnemis = listeEnnemisGenerator.genererListeEnnemis("facile");
                ennemiManager.ajouterListeEnnemis(nouvelleListeEnnemis);
                // System.out.println("vague marathon" + nouvelleListeEnnemis);
            } else if (vaguesRestantes >= nbVague - 20) {
                ArrayList<Ennemi> nouvelleListeEnnemis = listeEnnemisGenerator.genererListeEnnemis("moyen");
                ennemiManager.ajouterListeEnnemis(nouvelleListeEnnemis);
                // System.out.println("vague marathon" + nouvelleListeEnnemis);

            } else {
                ArrayList<Ennemi> nouvelleListeEnnemis = listeEnnemisGenerator.genererListeEnnemis("difficile");
                ennemiManager.ajouterListeEnnemis(nouvelleListeEnnemis);
                // System.out.println("vague marathon" + nouvelleListeEnnemis);

            }

        } else {
            if (vaguesRestantes > 0) {
                ArrayList<Ennemi> nouvelleListeEnnemis = listeEnnemisGenerator.genererListeEnnemis(difficulte);

                if (!nouvelleListeEnnemis.isEmpty()) {
                    ennemiManager.ajouterListeEnnemis(nouvelleListeEnnemis);

                }
            }
        }
    }

    private void initRelacherEnnemiTimer() {
        relacherEnnemiTimer = new Timer(delayEnnemi, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // long currentTime = System.currentTimeMillis();

                if (!ennemiManager.getEnnemis().isEmpty()) {
                    // Retirez l'ennemi de la liste et ajoutez-le au plateau
                    ennemiManager.relacherEnnemis();
                }
            }
        });
    }

    // Arrêter les timers (appelé lorsque le jeu est en pause ou terminé)
    protected void stopTimers() {

        if (vagueTimer != null && vagueTimer.isRunning()) {
            vagueTimer.stop();
            // System.out.println("Vague timer stopped");
        }

        if (relacherEnnemiTimer != null && relacherEnnemiTimer.isRunning()) {
            relacherEnnemiTimer.stop();
            // System.out.println("Relacher ennemi timer stopped");
        }

    }

    // Appeler cette méthode pour arrêter les deux timers lorsque nécessaire
    public void stopEnnemiTimers() {
        stopTimers();
    }

    // ------ initialisation du panneau de jeu ---------

    private void initGameScreen(BufferedImage img, String difficulte) {

        // Initialisation du panneau de jeu
        this.img = img;

        // Charger les sprites du terrain
        switch (difficulte) {
            case "facile":
                loadSpritesFacile();
                break;
            case "moyen":
                loadSpritesMoyen();
                break;
            case "difficile":
                loadSpritesDifficile();
                break;
            default:
                loadSpritesFacile();
                break;
        }

        // Création de l'écouteur de clic de souris
        mouseClickListener = new MouseClickListener(this, toolbar);

        // Enregistrement du panneau de jeu comme MouseListener
        addMouseListener(mouseClickListener);
    }

    // ------- Mise à jour du jeu -------
    public synchronized void updateGame() {
        ennemiManager.suppEntitesMortes();
    }

    // ---- chargement des sprites du terrain ----
    private void loadSpritesFacile() {
        sprites.add(img.getSubimage(6 * tileSize, 0 * tileSize, tileSize, tileSize));
        sprites.add(img.getSubimage(7 * tileSize, 0 * tileSize, tileSize, tileSize));
    }

    private void loadSpritesMoyen() {
        sprites.add(img.getSubimage(6 * tileSize, 6 * tileSize, tileSize, tileSize));
        sprites.add(img.getSubimage(1 * tileSize, 3 * tileSize, tileSize, tileSize));
    }

    private void loadSpritesDifficile() {
        sprites.add(img.getSubimage(13 * tileSize, 0 * tileSize, tileSize, tileSize));
        sprites.add(img.getSubimage(13 * tileSize, 0 * tileSize, tileSize, tileSize));
    }

    // ------- Dessiner le plateau -------
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tileSize = 80;

        // Dessiner le plateau
        for (int y = 0; y < plateau.getHauteur(); y++) {
            for (int x = 0; x < plateau.getLargeurReelle(); x++) {
                // Dessiner le fond du plateau
                if (y % 2 == 0) {
                    g.drawImage(sprites.get(1), x * tileSize, y * tileSize, tileSize, tileSize, null);
                } else {
                    g.drawImage(sprites.get(0), x * tileSize, y * tileSize, tileSize, tileSize, null);
                }
            }
        }

        // Dessiner les tours
        drawTours(g);

        // Dessiner les ennemis
        drawEnnemis(g);

        // Dessiner le contour blanc uniquement si le clic est dans le terrain/plateau
        if (clickedTileX != -1 && clickedTileY != -1 && isClickInTerrain(clickedTileX, clickedTileY)) {
            g.setColor(Color.WHITE);
            g.drawRect(clickedTileX * tileSize, clickedTileY * tileSize, tileSize, tileSize);
        }
    }

    // ------- Dessiner les tours -------
    public void drawTours(Graphics g) {
        // Dessiner les tours
        ArrayList<Tour> tours = new ArrayList<>(plateau.getTours());

        for (Tour tour : tours) {
            int tourX = tour.getX();
            int tourY = tour.getY();
            // Obtenez le JLabel associé à la tour
            JLabel tourLabel = tour.getSpriteContainer();

            // Obtenez l'icône (sprite) du JLabel
            ImageIcon tourSprite = (ImageIcon) tourLabel.getIcon();

            // Dessinez le sprite à l'emplacement souhaité sur le plateau
            g.drawImage(tourSprite.getImage(), tourX * tileSize, tourY * tileSize, null);
        }
    }

    // ------- Dessiner les ennemis -------
    public void drawEnnemis(Graphics g) {
        // Create a copy of the list to avoid ConcurrentModificationException
        ArrayList<Ennemi> ennemisCopy = new ArrayList<>(plateau.getEnnemis());

        for (Ennemi ennemi : ennemisCopy) {
            // ennemi.getGestionAttaqueEnnemi().updateEnnemiPosition(); // Mettez à jour
            // graduellement la position

            int ennemiX = ennemi.getX();
            int ennemiY = ennemi.getY();

            // Obtenez le JLabel associé à l'ennemi
            JLabel ennemiLabel = ennemi.getSpriteContainer();

            // Obtenez l'icône (sprite) du JLabel
            ImageIcon ennemiSprite = (ImageIcon) ennemiLabel.getIcon();

            // Dessinez le sprite à l'emplacement souhaité sur le plateau
            g.drawImage(ennemiSprite.getImage(), ennemiX * tileSize, ennemiY * tileSize, null);
        }
    }

    // Méthode pour vérifier si le clic est dans le terrain/plateau
    public boolean isClickInTerrain(int x, int y) {
        // déterminer si les coordonnées (x, y) appartiennent au plateau
        return x >= 0 && x < 10 && y >= 0 && y < 5;
    }

    // ------- Getters et Setters -------
    public Plateau getPlateau() {
        return plateau;
    }

    public Joueur getJoueur() {
        return plateau.getJoueur();
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setClickedTile(int x, int y) {
        clickedTileX = x;
        clickedTileY = y;
    }

    public int getDelayEnnemi() {
        return delayEnnemi;
    }

    public void setDelayEnnemi(int delayEnnemi) {
        this.delayEnnemi = delayEnnemi;
    }

    public int getDelayVague() {
        return delayVague;
    }

    public void setDelayVague(int delayVague) {
        this.delayVague = delayVague;
    }

    public int getNbVague() {
        return nbVague;
    }

    public void setNbVague(int nbVague) {
        this.nbVague = nbVague;
    }

    public int getVaguesRestantes() {
        return vaguesRestantes;
    }

    public void setVaguesRestantes(int vaguesRestantes) {
        this.vaguesRestantes = vaguesRestantes;
    }

    public int getTempsRestant() {
        return tempsRestant;
    }

    public void setTempsRestant(int tempsRestant) {
        this.tempsRestant = tempsRestant;
    }

    public Timer getCountdownTimer() {
        return countdownTimer;
    }

    public void setCountdownTimer(Timer countdownTimer) {
        this.countdownTimer = countdownTimer;
    }

    public JLabel getTempsAvantVagueLabel() {
        return tempsAvantVagueLabel;
    }

    public void setTempsAvantVagueLabel(JLabel tempsAvantVagueLabel) {
        this.tempsAvantVagueLabel = tempsAvantVagueLabel;
    }

    public ArrayList<BufferedImage> getSprites() {
        return sprites;
    }

    public void setSprites(ArrayList<BufferedImage> sprites) {
        this.sprites = sprites;
    }

    public MouseClickListener getMouseClickListener() {
        return mouseClickListener;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
