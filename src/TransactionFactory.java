public class TransactionFactory {
    
    public static Transaction createTransaction(TransactionType type, String cardNumber, double amount) {
        return new Transaction(type, cardNumber, amount);
    }
    
    public static Transaction createBalanceInquiry(String cardNumber) {
        return createTransaction(TransactionType.BALANCE_INQUIRY, cardNumber, 0.0);
    }
    
    public static Transaction createCashWithdrawal(String cardNumber, double amount) {
        return createTransaction(TransactionType.CASH_WITHDRAWAL, cardNumber, amount);
    }
    
    public static Transaction createPinChange(String cardNumber) {
        return createTransaction(TransactionType.PIN_CHANGE, cardNumber, 0.0);
    }
    
    public static Transaction createMiniStatement(String cardNumber) {
        return createTransaction(TransactionType.MINI_STATEMENT, cardNumber, 0.0);
    }
}