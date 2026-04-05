

import java.awt.*;
import javax.swing.Icon;

/**
 * Custom-drawn Power Icon for logout buttons.
 */
public class PowerIcon implements Icon {
    private int size;
    private Color color;

    public PowerIcon(int size, Color color) { 
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
        g2.drawArc(x + pad, y + pad + 2, size - pad * 2, size - pad * 2, -70, 320);
        g2.drawLine(x + size / 2, y + 2, x + size / 2, y + size / 2 + 2);
        
        g2.dispose();
    }
}
