import java.util.Date;

public class ATMApplication {
    
    public static void main(String[] args) {
        System.out.println("=== ATM System Demo ===");
        
        // Initialize ATM
        ATM atm = new ATM("ATM001", "Main Street Branch", 50000.0);
        
        // Create sample cards
        Card johnCard = new Card("1234567890123456", "John Doe", 
                                 new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000), 
                                 CardType.DEBIT, "BANK001", "1234");
        
        Card janeCard = new Card("9876543210987654", "Jane Smith", 
                                 new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000), 
                                 CardType.CREDIT, "BANK001", "5678");
        
        // Create users
        User johnUser = new User("USER001", "John Doe");
        johnUser.addCard(johnCard);
        
        User janeUser = new User("USER002", "Jane Smith");
        janeUser.addCard(janeCard);
        
        // Start ATM session
        atm.getScreen().displayWelcome();
        
        // Demo Transaction 1: Balance Inquiry
        System.out.println("\n=== Demo Transaction 1: Balance Inquiry ===");
        simulateCardInsertion(atm, johnCard);
        simulatePinEntry(atm, "1234");
        simulateTransactionSelection(atm, TransactionType.BALANCE_INQUIRY, 0);
        simulateReceiptPrinting(atm, TransactionFactory.createBalanceInquiry(johnCard.getCardNumber()));
        
        // Reset ATM for next transaction
        resetATM(atm);
        
        // Demo Transaction 2: Cash Withdrawal
        System.out.println("\n=== Demo Transaction 2: Cash Withdrawal ===");
        simulateCardInsertion(atm, janeCard);
        simulatePinEntry(atm, "5678");
        simulateTransactionSelection(atm, TransactionType.CASH_WITHDRAWAL, 500.0);
        simulateReceiptPrinting(atm, TransactionFactory.createCashWithdrawal(janeCard.getCardNumber(), 500.0));
        
        // Reset ATM for next transaction
        resetATM(atm);
        
        // Demo Transaction 3: PIN Change
        System.out.println("\n=== Demo Transaction 3: PIN Change ===");
        simulateCardInsertion(atm, johnCard);
        simulatePinEntry(atm, "1234");
        simulatePinChangeTransaction(atm, johnCard);
        
        // Reset ATM for next transaction
        resetATM(atm);
        
        // Demo Transaction 4: Mini Statement
        System.out.println("\n=== Demo Transaction 4: Mini Statement ===");
        simulateCardInsertion(atm, janeCard);
        simulatePinEntry(atm, "5678");
        simulateTransactionSelection(atm, TransactionType.MINI_STATEMENT, 0);
        simulateReceiptPrinting(atm, TransactionFactory.createMiniStatement(janeCard.getCardNumber()));
        
        System.out.println("\n=== ATM System Demo Complete ===");
    }
    
    private static void simulateCardInsertion(ATM atm, Card card) {
        System.out.println("User inserts card...");
        atm.insertCard(card);
    }
    
    private static void simulatePinEntry(ATM atm, String pin) {
        System.out.println("User enters PIN: " + pin);
        atm.authenticate(pin);
    }
    
    private static void simulateTransactionSelection(ATM atm, TransactionType type, double amount) {
        System.out.println("User selects transaction: " + type + 
                          (amount > 0 ? " for $" + amount : ""));
        atm.selectTransaction(type, amount);
    }
    
    private static void simulateReceiptPrinting(ATM atm, Transaction transaction) {
        System.out.println("System printing receipt...");
        atm.printReceipt(transaction);
    }
    
    private static void simulatePinChangeTransaction(ATM atm, Card card) {
        // This is a simplified simulation - in real implementation, 
        // the PinChangeStrategy would handle the keypad interactions
        System.out.println("User selects PIN Change");
        atm.selectTransaction(TransactionType.PIN_CHANGE, 0);
        simulateReceiptPrinting(atm, TransactionFactory.createPinChange(card.getCardNumber()));
    }
    
    private static void resetATM(ATM atm) {
        System.out.println("\\n--- ATM Session Complete ---\\n");
        atm.setState(new IdleState());
        atm.getScreen().displayWelcome();
    }
}