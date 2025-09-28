public class Account {
    private String accountNumber;
    private String holderName;
    private double balance;
    private String bankId;
    
    public Account(String accountNumber, String holderName, double balance, String bankId) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.bankId = bankId;
    }
    
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
            return false;
        }
        if (balance < amount) {
            System.out.println("Insufficient funds");
            return false;
        }
        balance -= amount;
        System.out.println("Withdrawal successful. New balance: $" + String.format("%.2f", balance));
        return true;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public String getBankId() { return bankId; }
}