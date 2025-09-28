public class PrintingState extends ATMState {
    private Transaction transaction;
    
    public PrintingState(Transaction transaction) {
        this.transaction = transaction;
    }
    
    @Override
    public void insertCard(ATM atm, Card card) {
        showInvalidOperationMessage("insertCard");
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
        if (atm.getReceiptPrinter().printReceipt(this.transaction)) {
            atm.getScreen().displayMessage("Receipt printed. Thank you!");
        } else {
            atm.getScreen().displayMessage("Receipt printing failed, but transaction was successful.");
        }
        
        atm.getScreen().displayThankYou();
        atm.getCardReader().ejectCard();
        atm.setState(new IdleState());
    }
    
    public void skipReceipt(ATM atm) {
        atm.getScreen().displayMessage("Receipt skipped. Thank you!");
        atm.getScreen().displayThankYou();
        atm.getCardReader().ejectCard();
        atm.setState(new IdleState());
    }
}