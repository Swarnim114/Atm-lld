import java.util.HashMap;
import java.util.Map;

public class BankService {
    private String bankApiUrl;
    private Map<String, Account> accountDatabase;
    private Map<String, String> cardPinDatabase;
    
    public BankService() {
        this.bankApiUrl = "https://api.securebank.com";
        this.accountDatabase = new HashMap<>();
        this.cardPinDatabase = new HashMap<>();
        initializeMockData();
    }
    
    private void initializeMockData() {
        // Mock data for testing
        Account account1 = new Account("ACC001", "John Doe", 5000.0, "BANK001");
        Account account2 = new Account("ACC002", "Jane Smith", 3500.0, "BANK001");
        Account account3 = new Account("ACC003", "Bob Johnson", 1200.0, "BANK002");
        
        accountDatabase.put("1234567890123456", account1);
        accountDatabase.put("9876543210987654", account2);
        accountDatabase.put("5555666677778888", account3);
        
        cardPinDatabase.put("1234567890123456", "1234");
        cardPinDatabase.put("9876543210987654", "5678");
        cardPinDatabase.put("5555666677778888", "9999");
    }
    
    public boolean validatePin(String cardNumber, String pin) {
        System.out.println("BankService: Validating PIN for card ending in " + cardNumber.substring(cardNumber.length() - 4));
        
        // Simulate network delay
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        
        String storedPin = cardPinDatabase.get(cardNumber);
        return storedPin != null && storedPin.equals(pin);
    }
    
    public Account getAccountDetails(String cardNumber) {
        System.out.println("BankService: Retrieving account details for card ending in " + cardNumber.substring(cardNumber.length() - 4));
        
        // Simulate network delay
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
        
        return accountDatabase.get(cardNumber);
    }
    
    public boolean processTransaction(Transaction transaction) {
        System.out.println("BankService: Processing transaction " + transaction.getTransactionId());
        
        // Simulate processing delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        
        // For demo purposes, approve most transactions
        return Math.random() > 0.1; // 90% success rate
    }
    
    public boolean changePin(String cardNumber, String oldPin, String newPin) {
        System.out.println("BankService: Changing PIN for card ending in " + cardNumber.substring(cardNumber.length() - 4));
        
        // Validate old PIN first
        if (!validatePin(cardNumber, oldPin)) {
            return false;
        }
        
        // Simulate processing delay
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        
        // Update PIN in database
        cardPinDatabase.put(cardNumber, newPin);
        System.out.println("BankService: PIN updated successfully");
        return true;
    }
    
    public String getBankApiUrl() {
        return bankApiUrl;
    }
}