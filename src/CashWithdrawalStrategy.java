public class CashWithdrawalStrategy implements TransactionStrategy {
    
    @Override
    public boolean execute(ATM atm, Transaction transaction) {
        try {
            double amount = transaction.getAmount();
            
            // Validate withdrawal amount
            if (amount <= 0) {
                atm.getScreen().displayError("Invalid withdrawal amount");
                return false;
            }
            
            // Check if ATM can dispense the amount
            if (!atm.getCashDispenser().canDispense(amount)) {
                atm.getScreen().displayError("Unable to dispense requested amount");
                return false;
            }
            
            // Verify account has sufficient funds
            Account account = atm.getBankService().getAccountDetails(transaction.getCardNumber());
            if (account == null) {
                atm.getScreen().displayError("Unable to retrieve account information");
                return false;
            }
            
            if (account.getBalance() < amount) {
                atm.getScreen().displayError("Insufficient funds");
                return false;
            }
            
            // Process withdrawal
            if (account.withdraw(amount) && atm.getCashDispenser().dispenseCash(amount)) {
                atm.getScreen().displayMessage("Please take your cash");
                return true;
            } else {
                atm.getScreen().displayError("Withdrawal failed");
                return false;
            }
            
        } catch (Exception e) {
            atm.getScreen().displayError("Service temporarily unavailable");
            return false;
        }
    }
}