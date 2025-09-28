public abstract class ATMState {
    public abstract void insertCard(ATM atm, Card card);
    public abstract void authenticate(ATM atm, String pin);
    public abstract void selectTransaction(ATM atm, TransactionType type, double amount);
    public abstract void dispenseCash(ATM atm, double amount);
    public abstract void printReceipt(ATM atm, Transaction transaction);
    
    protected void showInvalidOperationMessage(String operation) {
        System.out.println("ATM State: Invalid operation '" + operation + "' in current state");
    }
}