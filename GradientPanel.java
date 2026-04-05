

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Background panel that renders a smooth gradient drop instead of solid color.
 */
public class GradientPanel extends JPanel {
    private Color startColor;
    private Color endColor;

    public GradientPanel(Color start, Color end) {
        this.startColor = start;
        this.endColor = end;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        g2.setPaint(new GradientPaint(0, 0, startColor, 0, getHeight(), endColor));
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        g2.dispose();
        super.paintComponent(g);
    }
}
