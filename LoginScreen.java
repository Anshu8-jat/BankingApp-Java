






import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;

/**
 * Authentication screen managing user front-end interactions.
 * Submits queries properly to DatabaseManager and routes upon success.
 */
public class LoginScreen extends GradientPanel {
    public LoginScreen(BankingApp app) {
        super(Theme.PRIMARY_DARK, new Color(25, 25, 35));
        setLayout(new GridBagLayout());

        CardPanel card = new CardPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel logoLabel = createCenteredLabel("🏦", new Font("Segoe UI Emoji", Font.PLAIN, 60), null);
        JLabel titleLabel = createCenteredLabel("Secure Bank", Theme.TITLE_FONT, Theme.NEON_BLUE);
        JLabel subtitleLabel = createCenteredLabel("Sign in to continue", Theme.REGULAR_FONT, Theme.TEXT_SECONDARY);

        ModifierField userField = new ModifierField(); // We use the proper names below
        ModernTextField finalUserField = new ModernTextField("👤 Username");
        finalUserField.setAlignmentX(Component.CENTER_ALIGNMENT);

        ModernPasswordField passField = new ModernPasswordField("🔒 Password");
        passField.setAlignmentX(Component.CENTER_ALIGNMENT);

        ModernButton loginBtn = new ModernButton("LOGIN", Theme.NEON_BLUE);
        loginBtn.setIcon(new ArrowIcon(18, Color.WHITE));
        loginBtn.setIconTextGap(10);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        ModernButton exitBtn = new ModernButton("EXIT", Theme.NEON_PINK);
        exitBtn.setIcon(new PowerIcon(18, Color.WHITE));
        exitBtn.setIconTextGap(10);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (Component c : new Component[]{logoLabel, titleLabel, subtitleLabel, finalUserField, passField, loginBtn, exitBtn}) {
            card.add(Box.createVerticalStrut(10));
            card.add(c);
        }
        add(card);

        loginBtn.addActionListener(e -> {
            String username = finalUserField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            } else {
                if (DatabaseManager.authenticateUser(app, username, password)) {
                    finalUserField.clearField();
                    passField.clearField();
                    app.showScreen("Dashboard");
                } else {
                    JOptionPane.showMessageDialog(this, "Password incorrect! If new user, use a unique username.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitBtn.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        passField.addActionListener(e -> loginBtn.doClick());
    }

    /** Helper class defined above for generic placeholder references */
    private static class ModifierField {}

    private JLabel createCenteredLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        if (color != null) label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
}
