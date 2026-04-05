


import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * Custom text field with placeholder capabilities and smooth focus styling.
 */
public class ModernTextField extends JTextField {
    protected String placeholder;
    protected boolean showingPlaceholder = true;

    public ModernTextField(String placeholder) {
        this.placeholder = placeholder;
        
        setOpaque(false);
        setForeground(Theme.TEXT_SECONDARY);
        setCaretColor(Theme.NEON_BLUE);
        setFont(Theme.REGULAR_FONT);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        setPreferredSize(new Dimension(250, 45));
        setText(placeholder);

        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText("");
                    setForeground(Theme.TEXT_PRIMARY);
                    showingPlaceholder = false;
                }
            }
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Theme.TEXT_SECONDARY);
                    setText(placeholder);
                    showingPlaceholder = true;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(Theme.ACCENT_DARK);
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        
        g2.setColor(Theme.NEON_BLUE.darker());
        g2.setStroke(new BasicStroke(1));
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 15, 15));
        
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public String getText() { 
        return showingPlaceholder ? "" : super.getText(); 
    }

    public void clearField() {
        setText(placeholder);
        setForeground(Theme.TEXT_SECONDARY);
        showingPlaceholder = true;
    }
}
