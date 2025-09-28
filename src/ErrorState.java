public class ErrorState extends ATMState {
    
    @Override
    public void insertCard(ATM atm, Card card) {
        atm.getScreen().displayError("ATM is currently in error state. Please try again later.");
        if (card != null) {
            atm.getCardReader().ejectCard();
        }
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
    
    public void reset(ATM atm) {
        atm.getScreen().displayMessage("ATM reset. Returning to service.");
        atm.setState(new IdleState());
    }
}