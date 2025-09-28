public class AuthenticatingState extends ATMState {
    private Card currentCard;
    private int attemptCount = 0;
    private final int MAX_ATTEMPTS = 3;
    
    public AuthenticatingState(Card card) {
        this.currentCard = card;
    }
    
    @Override
    public void insertCard(ATM atm, Card card) {
        showInvalidOperationMessage("insertCard");
    }
    
    @Override
    public void authenticate(ATM atm, String pin) {
        attemptCount++;
        
        if (currentCard.validatePin(pin)) {
            atm.getScreen().displayMessage("PIN correct. Welcome " + currentCard.getHolderName() + "!");
            atm.setState(new ProcessingState(currentCard));
            atm.getScreen().displayMenu();
        } else {
            if (attemptCount >= MAX_ATTEMPTS) {
                atm.getScreen().displayError("Too many incorrect attempts. Card retained.");
                atm.getCardReader().retainCard();
                atm.setState(new ErrorState());
            } else {
                atm.getScreen().displayError("Incorrect PIN. Attempts remaining: " + (MAX_ATTEMPTS - attemptCount));
            }
        }
    }
    
    @Override
    public void selectTransaction(ATM atm, TransactionType type, double amount) {
        showInvalidOperationMessage("selectTransaction");
    }
    
    @Override
    public void dispenseCash(ATM atm, double amount) {
        showInvalidOperationMessage("dispenseCash");
    }
    
    @Override
    public void printReceipt(ATM atm, Transaction transaction) {
        showInvalidOperationMessage("printReceipt");
    }
}