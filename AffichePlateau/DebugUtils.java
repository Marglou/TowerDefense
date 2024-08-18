package AffichePlateau;

import java.awt.Component;
import java.awt.Container;

public class DebugUtils {

    public static void printComponentHierarchy(Component component, int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }

        System.out.println(indent.toString() + component.getClass().getName());

        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                printComponentHierarchy(child, depth + 1);
            }
        }
    }
    
}
