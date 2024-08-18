package Game;

import javax.swing.*;

import Effets.AttaqueAmelioree;
import Effets.Bonus;
import Effets.CritiqueAccrue;
import Effets.DefenseAmelioree;
import Effets.DegatsContinus;
import Effets.SanteAmelioree;
import Entités.Tour;
import Entités.TourBasique;
import Entités.TourCaemento;
import Entités.TourCuratis;
import Entités.TourIanis;
import Entités.TourTutela;
import Entités.TourVindicta;
import Entités.TourVocator;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;

public class Main extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private HomePage homePage;
    private SettingsPage settingsPage;
    private ModeDeJeu modeDeJeu;
    private ShopPage shopPage;
    private Normal normal;
    private Histoire histoire;
    private ReglePage regle;

    private Joueur joueur;
    private ArrayList<Tour> towers = towersAvailable();
    private ArrayList<Bonus> bonus = bonusAvailable();

    // Constructor
    public Main() {
    }

    public Main(Joueur joueur) {
        super("Tower Defense Game"); // Set the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define default close operation
        setSize(1200, 750); // Set the size of the window
        setResizable(false); // Make the window not resizable
        setLocationRelativeTo(null); // Center the window on the screen

        this.joueur = joueur; // Set the player object
        // Create a JPanel with BorderLayout as the layout manager
        JPanel mainContainer = new JPanel(new BorderLayout());
        add(mainContainer);

        // Create a CardLayout for switching between panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainContainer.add(mainPanel, BorderLayout.CENTER);

        // Instantiate different pages of the application
        homePage = new HomePage(this);
        this.modeDeJeu = new ModeDeJeu(this);
        this.histoire = new Histoire(this, joueur);
        this.normal = new Normal(this, joueur);
        this.shopPage = new ShopPage(this, joueur, towers, bonus);
        this.settingsPage = new SettingsPage(this, joueur);
        this.regle = new ReglePage(this);
        JButton quitButton = new JButton("Quitter");

        // Add the pages to the mainPanel with corresponding names
        mainPanel.add(modeDeJeu.getPanel(), "ModeDeJeu");
        mainPanel.add(histoire.getPanel(), "Histoire");
        mainPanel.add(normal.getPanel(), "Normal");
        mainPanel.add(homePage.getPanel(), "Home");
        mainPanel.add(shopPage.getPanel(), "Shop");
        mainPanel.add(settingsPage.getPanel(), "Settings");
        mainPanel.add(regle.getPanel(), "Regles");
        mainPanel.add(quitButton, "Quitter");
        // Show the Home page by default
        cardLayout.show(mainPanel, "Home");

        setVisible(true); // Make the window visible
    }

    private ArrayList<Bonus> bonusAvailable() {
        ArrayList<Bonus> bonus = new ArrayList<Bonus>();
        bonus.add(new AttaqueAmelioree());
        bonus.add(new DefenseAmelioree());
        bonus.add(new SanteAmelioree());
        bonus.add(new DegatsContinus());
        bonus.add(new CritiqueAccrue());
        return bonus;
    }

    private ArrayList<Tour> towersAvailable() {
        ArrayList<Tour> towers = new ArrayList<Tour>();
        towers.add(new TourIanis());
        towers.add(new TourBasique());
        towers.add(new TourCaemento());
        towers.add(new TourCuratis());
        towers.add(new TourVindicta());
        towers.add(new TourVocator());
        towers.add(new TourTutela());
        return towers;

    }

    // Switch to a specific page
    // public void switchToPage(String pageName) {
    //     cardLayout.show(mainPanel, pageName);
    //     // System.out.println("Switched to page: " + pageName);
    //     revalidate();
    //     repaint();
    // }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the Entry window
            Entree entree = new Entree();

            // Add a WindowListener to the Entry window
            entree.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // When the Entry window is closed, get the player and create the Main window
                    Joueur player = entree.getjoueur();
                    new Main(player);
                }
            });
        });
    }

    public void replacePanel(JPanel newPanel, String pageName) {
        // Remplacez complètement le contenu principal par le nouveau panneau
        // setContentPane(newPanel);
        mainPanel.removeAll();
        if (pageName.equals("Shop")) {
            shopPage.updateShopPage();
        } else if (pageName.equals("Settings")) {
            settingsPage.updateSettingsPage();
        } else if (pageName.equals("Histoire")) {
            histoire.updateHistoire();
        }

        mainPanel.add(newPanel);
        // Revalidez et redessinez la fenêtre
        revalidate();
        repaint();
        setVisible(true);
    }

    // fonction véréfiant si le joueur possède déja une tour
    public static boolean possedeTour(ArrayList<Tour> tours, String nomTour) {
        for (Tour tour : tours) {
            if (tour.getClass().getSimpleName().equals(nomTour)) {
                return true;
            }
        }
        return false;
    }

    // fonction véréfiant si le joueur possède déja un bonus
    public static boolean possedeBonus(ArrayList<Bonus> bonus, String nomBonus) {
        for (Bonus bonus2 : bonus) {
            if (bonus2.getClass().getSimpleName().equals(nomBonus)) {
                return true;
            }
        }
        return false;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public JPanel getHomePanel() {
        return homePage.getPanel();
    }

    public JPanel getSettingsPanel() {
        return settingsPage.getPanel();
    }

    public JPanel getShopPanel() {
        return shopPage.getPanel();
    }

    public JPanel getModeDeJeuPanel() {
        return modeDeJeu.getPanel();
    }

    public JPanel getNormalPanel() {
        return normal.getPanel();
    }

    public JPanel getHistoirePanel() {
        return histoire.getPanel();
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<Tour> getTowers() {
        return towers;
    }

    public void setTowers(ArrayList<Tour> towers) {
        this.towers = towers;
    }

    public ArrayList<Bonus> getBonus() {
        return bonus;
    }

    public void setBonus(ArrayList<Bonus> bonus) {
        this.bonus = bonus;
    }

    public SettingsPage getSettingsPage() {
        return settingsPage;
    }

    public JPanel getReglePanel(){
        return regle.getPanel();
    }
}
