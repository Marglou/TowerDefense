package Design;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class MyFont {

    public static Font getFont(String path, int size) {
        Font myFont = null;
        // Load the font file
        try {
            File fontFile = new File(path);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
    
            // Register the font
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
    
            // Create a Font object with the desired style and size
            myFont = new Font(customFont.getFontName(), Font.PLAIN, size);
    
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez cette ligne pour afficher les détails de l'exception
        }
        return myFont; // Ajoutez cette ligne pour retourner la police créée
    }
    

    public static Font getCreepyFont(int size) {
        Font myFont = getFont("Res/creepyGhost.ttf", size);
        return myFont;
    }

    public static Font getGameOverFont(int size) {
        Font myFont = getFont("Res/fontGameOver.ttf", size);
        return myFont;
    }

    public static Font getWhispersFont (int size) {
        Font myFont = getFont("Res/ghostWhispers.ttf", size);
        return myFont;
    }

    public static Font getBrushFont (int size) {
        Font myFont = getFont("Res/ghostBrush.ttf", size);
        return myFont;
    }
}
