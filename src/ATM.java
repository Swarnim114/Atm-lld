import java.util.*;

public class ATM {
    private String atmId;
    private String location;
    private double cashBalance;
    private ATMState currentState;
    private CardReader cardReader;
    private Keypad keypad;
    private Screen screen;
    private CashDispenser cashDispenser;
    private ReceiptPrinter receiptPrinter;
    private BankService bankService;
    
    public ATM(String atmId, String location, double cashBalance) {
        this.atmId = atmId;
        this.location = location;
        this.cashBalance = cashBalance;
        this.cardReader = new CardReader();
        this.keypad = new Keypad();
        this.screen = new Screen();
        this.cashDispenser = new CashDispenser(cashBalance);
        this.receiptPrinter = new ReceiptPrinter();
        this.bankService = new BankService();
        this.currentState = new IdleState();
    }
    
    public void setState(ATMState state) {
        this.currentState = state;
    }
    
    public void insertCard(Card card) {
        currentState.insertCard(this, card);
    }
    
    public void authenticate(String pin) {
        currentState.authenticate(this, pin);
    }
    
    public void selectTransaction(TransactionType type, double amount) {
        currentState.selectTransaction(this, type, amount);
    }
    
    public void dispenseCash(double amount) {
        currentState.dispenseCash(this, amount);
    }
    
    public void printReceipt(Transaction transaction) {
        currentState.printReceipt(this, transaction);
    }
    
    // Getters
    public String getAtmId() { return atmId; }
    public String getLocation() { return location; }
    public double getCashBalance() { return cashBalance; }
    public CardReader getCardReader() { return cardReader; }
    public Keypad getKeypad() { return keypad; }
    public Screen getScreen() { return screen; }
    public CashDispenser getCashDispenser() { return cashDispenser; }
    public ReceiptPrinter getReceiptPrinter() { return receiptPrinter; }
    public BankService getBankService() { return bankService; }
    public ATMState getCurrentState() { return currentState; }
    
    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }
}