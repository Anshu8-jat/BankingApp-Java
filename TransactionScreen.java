





import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Unified smart-screen that changes its layout conditionally depending on Deposit or Withdraw calls.
 */
public class TransactionScreen extends GradientPanel {
    private ModernTextField amountField;

    public TransactionScreen(BankingApp app, boolean isDeposit) {
        super(Theme.PRIMARY_DARK, new Color(25, 25, 35));
        setLayout(new GridBagLayout());

        CardPanel card = new CardPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        Color themeColor = isDeposit ? Theme.NEON_GREEN : Theme.NEON_ORANGE;

        JLabel iconLabel = new JLabel(isDeposit ? "💰" : "💸", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(isDeposit ? "Deposit Funds" : "Withdraw Funds");
        titleLabel.setFont(Theme.SUBTITLE_FONT);
        titleLabel.setForeground(themeColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        amountField = new ModernTextField("Enter amount (₹)");
        amountField.setAlignmentX(Component.CENTER_ALIGNMENT);
        amountField.setHorizontalAlignment(JTextField.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setOpaque(false);
        ModernButton submitBtn = new ModernButton(isDeposit ? "✓ Deposit" : "✓ Withdraw", themeColor);
        ModernButton backBtn = new ModernButton("← Back", Theme.ACCENT_DARK);
        btnPanel.add(submitBtn);
        btnPanel.add(backBtn);

        card.add(Box.createVerticalStrut(20));
        card.add(iconLabel);
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(30));
        card.add(amountField);
        card.add(Box.createVerticalStrut(30));
        card.add(btnPanel);
        add(card);

        submitBtn.addActionListener(e -> {
            try {
                // BUGFIX: Safely removing commas from String so parseDouble doesn't crash on "1,000"
                String sanitizedInput = amountField.getText().replace("₹", "").replace(",", "").trim();
                double amount = Double.parseDouble(sanitizedInput);
                
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "⚠️ Amount must be greater than zero!", "Invalid Amount", JOptionPane.WARNING_MESSAGE);
                } else if (isDeposit) {
                    app.deposit(amount);
                    JOptionPane.showMessageDialog(this, "✅ Deposited ₹" + String.format("%.2f", amount), "Success", JOptionPane.INFORMATION_MESSAGE);
                    amountField.clearField();
                    app.showScreen("Dashboard");
                } else {
                    if (app.withdraw(amount)) {
                        JOptionPane.showMessageDialog(this, "✅ Withdrew ₹" + String.format("%.2f", amount), "Success", JOptionPane.INFORMATION_MESSAGE);
                        amountField.clearField();
                        app.showScreen("Dashboard");
                    } else {
                        JOptionPane.showMessageDialog(this, "❌ Insufficient balance! (Bal: ₹" + String.format("%.2f", app.getBalance()) + ")", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Enter a valid numeric amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backBtn.addActionListener(e -> { amountField.clearField(); app.showScreen("Dashboard"); });
        amountField.addActionListener(e -> submitBtn.doClick());
    }
}
