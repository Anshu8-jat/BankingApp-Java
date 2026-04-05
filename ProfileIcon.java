

import java.awt.*;
import javax.swing.Icon;

/**
 * Custom-drawn User Profile Icon.
 */
public class ProfileIcon implements Icon {
    private int size;
    private Color color;

    public ProfileIcon(int size, Color color) { 
        this.size = size; 
        this.color = color; 
    }

    public int getIconWidth() { return size; }
    public int getIconHeight() { return size; }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        
        int headSize = (int) (size * 0.45);
        g2.fillOval(x + (size - headSize) / 2, y + 2, headSize, headSize);
        g2.fillArc(x + 2, y + headSize + 4, size - 4, size, 0, 180);
        
        g2.dispose();
    }
}
