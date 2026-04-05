



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * A layered graphical panel used to enclose screen forms with a drop-shadow effect.
 */
public class CardPanel extends JPanel {
    public CardPanel() {
        setOpaque(false);
        setBorder(new EmptyBorder(25, 30, 25, 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Shadow effect
        g2.setColor(new Color(0, 0, 0, 50));
        g2.fill(new RoundRectangle2D.Double(4, 4, getWidth() - 4, getHeight() - 4, 20, 20));
        
        // Main panel
        g2.setColor(Theme.SECONDARY_DARK);
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 4, getHeight() - 4, 20, 20));
        
        // Border ring
        g2.setColor(new Color(60, 60, 60));
        g2.setStroke(new BasicStroke(1));
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 5, getHeight() - 5, 20, 20));
        
        g2.dispose();
        super.paintComponent(g);
    }
}
