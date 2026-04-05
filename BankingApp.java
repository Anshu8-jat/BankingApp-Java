





import javax.swing.*;
import java.awt.CardLayout;
import java.net.URL;

/**
 * Main application class controlling the application window, state, and routing.
 */
public class BankingApp {
    
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private double balance = 0.0;
    private String currentUser = "";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Improves font rendering quality on modern displays
                System.setProperty("awt.useSystemAAFontSettings", "on");
                System.setProperty("swing.aatext", "true");
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new BankingApp().startApp();
        });
    }

    public void startApp() {
        DatabaseManager.initDB(); 

        // Apply clean global component aesthetics so we don't repeat this code
        UIManager.put("OptionPane.background", Theme.SECONDARY_DARK);
        UIManager.put("Panel.background", Theme.SECONDARY_DARK);
        UIManager.put("OptionPane.messageForeground", Theme.TEXT_PRIMARY);
        UIManager.put("Button.background", Theme.ACCENT_DARK);
        UIManager.put("Button.foreground", Theme.TEXT_PRIMARY);

        frame = new JFrame("💳 Secure Java Bank - Modern Edition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        try {
            URL iconURL = getClass().getResource("/icon.png");
            if (iconURL != null) {
                frame.setIconImage(new ImageIcon(iconURL).getImage());
            }
        } catch (Exception e) {
            // Silently fail if icon is unreadable
        }

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Theme.PRIMARY_DARK);

        // Load specific screens into routing framework
        mainPanel.add(new LoginScreen(this), "Login");
        mainPanel.add(new DashboardScreen(this), "Dashboard");
        mainPanel.add(new TransactionScreen(this, true), "Deposit"); 
        mainPanel.add(new TransactionScreen(this, false), "Withdraw"); 
        mainPanel.add(new BalanceScreen(this), "Balance");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    /**
     * Helper to navigate between JPanel cards.
     */
    public void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }

    // -- State Management -- 
    
    public double getBalance() { 
        return balance; 
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public String getCurrentUser() { 
        return currentUser; 
    }
    
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
    
    /**
     * Modifies current balance by depositing amount and updating DB.
     */
    public void deposit(double amount) { 
        this.balance += amount; 
        DatabaseManager.updateDatabaseBalance(this.currentUser, this.balance);
    }
    
    /**
     * Verifies withdraw threshold and updates DB, returning success metric.
     */
    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            DatabaseManager.updateDatabaseBalance(this.currentUser, this.balance);
            return true;
        }
        return false;
    }
    
    /**
     * Secures app by purging local variables and sending user back to front door.
     */
    public void logout() {
        this.currentUser = "";
        this.balance = 0.0;
        showScreen("Login");
    }
}
