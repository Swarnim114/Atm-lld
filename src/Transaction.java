import java.util.Date;
import java.util.UUID;

public class Transaction {
    private String transactionId;
    private TransactionType type;
    private double amount;
    private Date timestamp;
    private String cardNumber;
    private TransactionStatus status;
    
    public Transaction(TransactionType type, String cardNumber, double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.type = type;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.timestamp = new Date();
        this.status = TransactionStatus.PENDING;
    }
    
    public boolean process() {
        // In a real implementation, this would connect to the bank's processing system
        System.out.println("Processing transaction: " + transactionId);
        
        // Simulate processing delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.status = TransactionStatus.ERROR;
            return false;
        }
        
        // For demo purposes, approve most transactions
        if (Math.random() > 0.1) { // 90% success rate
            this.status = TransactionStatus.APPROVED;
            return true;
        } else {
            this.status = TransactionStatus.DECLINED;
            return false;
        }
    }
    
    public void complete() {
        this.status = TransactionStatus.COMPLETED;
    }
    
    // Getters
    public String getTransactionId() { return transactionId; }
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public Date getTimestamp() { return timestamp; }
    public String getCardNumber() { return cardNumber; }
    public TransactionStatus getStatus() { return status; }
    
    // Setter for status
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}