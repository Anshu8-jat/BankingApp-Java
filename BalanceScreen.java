





import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Screen component displaying the user's current loaded balance.
 */
public class BalanceScreen extends GradientPanel {
    private JLabel balanceValueLabel;
    private JLabel balanceStatusLabel;

    public BalanceScreen(BankingApp app) {
        super(Theme.PRIMARY_DARK, new Color(25, 25, 35));
        setLayout(new GridBagLayout());
        
        CardPanel card = new CardPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Account Balance");
        titleLabel.setFont(Theme.SUBTITLE_FONT);
        titleLabel.setForeground(Theme.NEON_BLUE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel balanceCard = new JPanel();
        balanceCard.setLayout(new BoxLayout(balanceCard, BoxLayout.Y_AXIS));
        balanceCard.setBackground(Theme.ACCENT_DARK);
        balanceCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Theme.NEON_GREEN.darker(), 2),
            new EmptyBorder(20, 40, 20, 40)
        ));

        balanceValueLabel = new JLabel("₹0.00");
        balanceValueLabel.setFont(Theme.BALANCE_FONT);
        balanceValueLabel.setForeground(Theme.NEON_GREEN);
        
        balanceStatusLabel = new JLabel("💳 Account Active");
        balanceStatusLabel.setForeground(Theme.TEXT_SECONDARY);

        balanceCard.add(balanceValueLabel);
        
        ModernButton backBtn = new ModernButton("← Back to Dashboard", Theme.NEON_BLUE);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(20));
        card.add(balanceCard);
        card.add(Box.createVerticalStrut(15));
        card.add(balanceStatusLabel);
        card.add(Box.createVerticalStrut(20));
        card.add(backBtn);
        add(card);

        // Fetch data immediately when screen switches using the Event framework
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                double balance = app.getBalance();
                balanceValueLabel.setText(String.format("₹%.2f", balance));
                
                if (balance == 0) {
                    balanceStatusLabel.setText("⚠️ No funds available");
                    balanceStatusLabel.setForeground(Theme.NEON_ORANGE);
                } else {
                    // Reset to active visually
                    balanceStatusLabel.setText("✅ Account in good standing");
                    balanceStatusLabel.setForeground(Theme.NEON_GREEN);
                }
            }
        });

        backBtn.addActionListener(e -> app.showScreen("Dashboard"));
    }
}
