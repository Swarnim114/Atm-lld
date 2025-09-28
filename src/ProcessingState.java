public class ProcessingState extends ATMState {
    private Card currentCard;
    
    public ProcessingState(Card card) {
        this.currentCard = card;
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
        Transaction transaction = TransactionFactory.createTransaction(type, currentCard.getCardNumber(), amount);
        
        if (!transaction.process()) {
            atm.getScreen().displayError("Transaction declined");
            atm.setState(new ErrorState());
            return;
        }
        
        // Get transaction strategy and execute
        TransactionStrategy strategy = getTransactionStrategy(type);
        if (strategy.execute(atm, transaction)) {
            transaction.complete();
            atm.getScreen().displayMessage("Transaction completed successfully");
            
            // Ask for receipt
            atm.getScreen().displayMessage("Would you like a receipt? (Press any key for receipt, or wait 5 seconds)");
            atm.setState(new PrintingState(transaction));
        } else {
            atm.getScreen().displayError("Transaction failed");
            atm.setState(new ErrorState());
        }
    }
    
    @Override
    public void dispenseCash(ATM atm, double amount) {
        showInvalidOperationMessage("dispenseCash");
    }
    
    @Override
    public void printReceipt(ATM atm, Transaction transaction) {
        showInvalidOperationMessage("printReceipt");
    }
    
    private TransactionStrategy getTransactionStrategy(TransactionType type) {
        switch (type) {
            case BALANCE_INQUIRY:
                return new BalanceInquiryStrategy();
            case CASH_WITHDRAWAL:
                return new CashWithdrawalStrategy();
            case PIN_CHANGE:
                return new PinChangeStrategy();
            case MINI_STATEMENT:
                return new MiniStatementStrategy();
            default:
                throw new IllegalArgumentException("Unknown transaction type: " + type);
        }
    }
}