package AffichePlateau;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import Entités.Tour;
import GestionTerrain.Case;

public class MouseClickListener implements MouseListener {

    private GameScreen gameScreen;
    private Toolbar toolbar;

    public MouseClickListener(GameScreen gameScreen, Toolbar toolbar) {
        this.gameScreen = gameScreen;
        this.toolbar = toolbar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        int clickedTileX = mouseX / gameScreen.getTileSize();
        int clickedTileY = mouseY / gameScreen.getTileSize();

        // Mettez à jour la case cliquée dans le GameScreen
        gameScreen.setClickedTile(clickedTileX, clickedTileY);
        
        // Redessiner le panneau de jeu pour refléter les changements
        // gameScreen.repaint();
   
        // si click dans le plateau
        if (gameScreen.isClickInTerrain(clickedTileX, clickedTileY)) {

            Case clickedCase = gameScreen.getPlateau().getCase(clickedTileX, clickedTileY);

            // si case occupée
            if (clickedCase.getOccupePar() != null) {
                // si tour => afficher les infos
                if (clickedCase.getOccupePar() instanceof Tour) {
                    toolbar.afficherInfoTour((Tour) clickedCase.getOccupePar());
                }
            // sinon si tourStockee
            } else if (toolbar.getTour() != null) {
                // Ajouter la tour à la case cliquée si possible
                addTour(toolbar.getTour(), clickedTileX, clickedTileY);
            }

        }
    }

    private void addTour(Tour Tour, int x, int y) {
        if (gameScreen.getPlateau().isValidPosition(Tour, x, y)) {
            gameScreen.getPlateau().placerTour(Tour, x, y);
            toolbar.setTour(null);
            gameScreen.getToolbar().afficherInfoTour(Tour);
        }
    }

    // Les autres méthodes de l'interface MouseListener (non utilisées, mais doivent
    // être implémentées)
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
