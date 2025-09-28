public class PinChangeStrategy implements TransactionStrategy {
    
    @Override
    public boolean execute(ATM atm, Transaction transaction) {
        try {
            atm.getScreen().displayMessage("PIN Change Service");
            
            String oldPin = atm.getKeypad().getInput("Enter current PIN:");
            String newPin = atm.getKeypad().getInput("Enter new PIN:");
            String confirmPin = atm.getKeypad().getInput("Confirm new PIN:");
            
            if (!newPin.equals(confirmPin)) {
                atm.getScreen().displayError("New PIN confirmation does not match");
                return false;
            }
            
            if (newPin.length() != 4) {
                atm.getScreen().displayError("PIN must be 4 digits");
                return false;
            }
            
            boolean success = atm.getBankService().changePin(
                transaction.getCardNumber(), 
                oldPin, 
                newPin
            );
            
            if (success) {
                atm.getScreen().displayMessage("PIN changed successfully");
                return true;
            } else {
                atm.getScreen().displayError("PIN change failed. Please verify your current PIN");
                return false;
            }
            
        } catch (Exception e) {
            atm.getScreen().displayError("Service temporarily unavailable");
            return false;
        }
    }
}