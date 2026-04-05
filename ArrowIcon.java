

import java.awt.*;
import javax.swing.Icon;

/**
 * Custom-drawn Arrow Icon for progression buttons.
 */
public class ArrowIcon implements Icon {
    private int size;
    private Color color;

    public ArrowIcon(int size, Color color) { 
        this.size = size; 
        this.color = color; 
    }

    public int getIconWidth() { return size; }
    public int getIconHeight() { return size; }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        int pad = 2;
        g2.drawLine(x + pad, y + size / 2, x + size - pad, y + size / 2);
        g2.drawLine(x + size - pad, y + size / 2, x + size / 2, y + pad);
        g2.drawLine(x + size - pad, y + size / 2, x + size / 2, y + size - pad);
        
        g2.dispose();
    }
}
