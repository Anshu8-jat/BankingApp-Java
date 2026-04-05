



import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

/**
 * A sleek, modern custom button with gradient and hover effects.
 */
public class ModernButton extends JButton {
    private Color baseColor;
    private Color hoverColor;
    private boolean isHovered = false;

    public ModernButton(String text, Color color) {
        super(text);
        this.baseColor = color;
        this.hoverColor = color.brighter();
        
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(Theme.BUTTON_FONT);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(180, 50));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { 
                isHovered = true; 
                repaint(); 
            }
            public void mouseExited(MouseEvent e) { 
                isHovered = false; 
                repaint(); 
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color topColor = isHovered ? hoverColor : baseColor;
        Color bottomColor = isHovered ? baseColor : baseColor.darker();
        g2.setPaint(new GradientPaint(0, 0, topColor, 0, getHeight(), bottomColor));
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));

        if (isHovered) {
            g2.setColor(new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 50));
            g2.setStroke(new BasicStroke(3));
            g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, 25, 25));
        }
        g2.dispose();
        super.paintComponent(g);
    }
}
