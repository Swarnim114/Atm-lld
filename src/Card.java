import java.util.Date;

public class Card {
    private String cardNumber;
    private String holderName;
    private Date expiryDate;
    private CardType cardType;
    private String bankId;
    private String pin;
    
    public Card(String cardNumber, String holderName, Date expiryDate, CardType cardType, String bankId, String pin) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.expiryDate = expiryDate;
        this.cardType = cardType;
        this.bankId = bankId;
        this.pin = pin;
    }
    
    public boolean validatePin(String enteredPin) {
        return this.pin != null && this.pin.equals(enteredPin);
    }
    
    public boolean isExpired() {
        return new Date().after(expiryDate);
    }
    
    // Getters
    public String getCardNumber() { return cardNumber; }
    public String getHolderName() { return holderName; }
    public Date getExpiryDate() { return expiryDate; }
    public CardType getCardType() { return cardType; }
    public String getBankId() { return bankId; }
    
    // Setter for PIN change
    public void setPin(String newPin) {
        this.pin = newPin;
    }
}