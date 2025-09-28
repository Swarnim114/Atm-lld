public class MiniStatementStrategy implements TransactionStrategy {
    
    @Override
    public boolean execute(ATM atm, Transaction transaction) {
        try {
            Account account = atm.getBankService().getAccountDetails(transaction.getCardNumber());
            if (account == null) {
                atm.getScreen().displayError("Unable to retrieve account information");
                return false;
            }
            
            atm.getScreen().displayMessage("=== MINI STATEMENT ===");
            atm.getScreen().displayMessage("Account: " + maskAccountNumber(account.getAccountNumber()));
            atm.getScreen().displayMessage("Holder: " + account.getHolderName());
            atm.getScreen().displayMessage("Available Balance: $" + String.format("%.2f", account.getBalance()));
            atm.getScreen().displayMessage("Recent Transactions:");
            atm.getScreen().displayMessage("1. Current Transaction - " + transaction.getType() + " - $" + 
                                         String.format("%.2f", transaction.getAmount()));
            atm.getScreen().displayMessage("=====================");
            
            return true;
            
        } catch (Exception e) {
            atm.getScreen().displayError("Service temporarily unavailable");
            return false;
        }
    }
    
    private String maskAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.length() < 4) {
            return "****";
        }
        return "****" + accountNumber.substring(accountNumber.length() - 4);
    }
}