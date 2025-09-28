public class BalanceInquiryStrategy implements TransactionStrategy {
    
    @Override
    public boolean execute(ATM atm, Transaction transaction) {
        try {
            Account account = atm.getBankService().getAccountDetails(transaction.getCardNumber());
            if (account != null) {
                atm.getScreen().showBalance(account.getBalance());
                return true;
            } else {
                atm.getScreen().displayError("Unable to retrieve account information");
                return false;
            }
        } catch (Exception e) {
            atm.getScreen().displayError("Service temporarily unavailable");
            return false;
        }
    }
}