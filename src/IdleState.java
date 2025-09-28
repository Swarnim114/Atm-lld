public class IdleState extends ATMState {
    
    @Override
    public void insertCard(ATM atm, Card card) {
        if (card == null) {
            atm.getScreen().displayError("No card detected");
            return;
        }
        
        if (card.isExpired()) {
            atm.getScreen().displayError("Card is expired");
            atm.getCardReader().ejectCard();
            return;
        }
        
        atm.getScreen().displayMessage("Card accepted. Please enter your PIN.");
        atm.setState(new AuthenticatingState(card));
    }
    
    @Override
    public void authenticate(ATM atm, String pin) {
        showInvalidOperationMessage("authenticate");
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