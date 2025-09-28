public class Screen {
    
    public void displayMessage(String message) {
        System.out.println("Screen: " + message);
    }
    
    public void displayMenu() {
        System.out.println("Screen: ===== ATM Main Menu =====");
        System.out.println("Screen: 1. Balance Inquiry");
        System.out.println("Screen: 2. Cash Withdrawal");
        System.out.println("Screen: 3. Change PIN");
        System.out.println("Screen: 4. Mini Statement");
        System.out.println("Screen: 5. Exit");
        System.out.println("Screen: ========================");
    }
    
    public void showBalance(double balance) {
        System.out.println("Screen: Your current balance is: $" + String.format("%.2f", balance));
    }
    
    public void displayWelcome() {
        System.out.println("Screen: Welcome to SecureBank ATM");
        System.out.println("Screen: Please insert your card");
    }
    
    public void displayError(String error) {
        System.out.println("Screen: ERROR - " + error);
    }
    
    public void displayThankYou() {
        System.out.println("Screen: Thank you for using SecureBank ATM");
        System.out.println("Screen: Have a great day!");
    }
    
    public void clearScreen() {
        // Simulate clearing screen
        System.out.println("\n".repeat(3));
    }
}