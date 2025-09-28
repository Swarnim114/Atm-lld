public interface TransactionStrategy {
    boolean execute(ATM atm, Transaction transaction);
}