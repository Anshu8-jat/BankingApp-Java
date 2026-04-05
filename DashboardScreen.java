





import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Main switch-board hub where users can launch into deeper operations.
 */
public class DashboardScreen extends GradientPanel {
    private JLabel welcomeLabel;

    public DashboardScreen(BankingApp app) {
        super(Theme.PRIMARY_DARK, new Color(25, 25, 35));
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(30, 40, 30, 40));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setIcon(new ProfileIcon(28, Theme.NEON_BLUE));
        welcomeLabel.setIconTextGap(15);
        welcomeLabel.setFont(Theme.TITLE_FONT);
        welcomeLabel.setForeground(Theme.TEXT_PRIMARY);

        JLabel dateTimeLabel = new JLabel("💳 Secure Banking Portal");
        dateTimeLabel.setFont(Theme.REGULAR_FONT);
        dateTimeLabel.setForeground(Theme.TEXT_SECONDARY);

        headerPanel.add(welcomeLabel);
        headerPanel.add(dateTimeLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel menuGrid = new JPanel(new GridLayout(2, 2, 20, 20));
        menuGrid.setOpaque(false);

        ModernButton[] btns = {
            new ModernButton("💰 Deposit", Theme.NEON_GREEN),
            new ModernButton("💸 Withdraw", Theme.NEON_ORANGE),
            new ModernButton("📊 Balance", Theme.NEON_BLUE),
            new ModernButton("🚪 Logout", Theme.NEON_PINK)
        };
        
        for (ModernButton btn : btns) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            menuGrid.add(btn);
        }

        CardPanel menuCard = new CardPanel();
        menuCard.setLayout(new BorderLayout());
        menuCard.add(menuGrid, BorderLayout.CENTER);
        add(menuCard, BorderLayout.CENTER);

        JLabel footerLabel = new JLabel("© " + java.time.Year.now().getValue() + " Secure Bank | All Rights Reserved", SwingConstants.CENTER);
        footerLabel.setForeground(Theme.TEXT_SECONDARY);
        add(footerLabel, BorderLayout.SOUTH);

        // Dynamically update greeting data dynamically every time screen opens
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) { 
                welcomeLabel.setText("Welcome, " + app.getCurrentUser() + "!"); 
            }
        });

        // Event routing map
        btns[0].addActionListener(e -> app.showScreen("Deposit"));
        btns[1].addActionListener(e -> app.showScreen("Withdraw"));
        btns[2].addActionListener(e -> app.showScreen("Balance"));
        
        btns[3].addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                app.logout();
            }
        });
    }
}
