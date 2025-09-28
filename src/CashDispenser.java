import java.util.*;

public class CashDispenser {
    private double availableCash;
    private Map<Denomination, Integer> cashInventory;
    
    public CashDispenser(double initialCash) {
        this.availableCash = initialCash;
        this.cashInventory = new HashMap<>();
        initializeCashInventory();
    }
    
    private void initializeCashInventory() {
        // Initialize with some default denominations
        cashInventory.put(Denomination.HUNDRED, 50);      // 50 x $100 = $5000
        cashInventory.put(Denomination.FIVE_HUNDRED, 20); // 20 x $500 = $10000  
        cashInventory.put(Denomination.TWO_THOUSAND, 10); // 10 x $2000 = $20000
    }
    
    public boolean dispenseCash(double amount) {
        if (!canDispense(amount)) {
            System.out.println("Cash Dispenser: Unable to dispense $" + amount);
            return false;
        }
        
        Map<Denomination, Integer> dispensedNotes = calculateNotes(amount);
        if (dispensedNotes == null) {
            System.out.println("Cash Dispenser: Cannot dispense exact amount with available denominations");
            return false;
        }
        
        // Update inventory
        for (Map.Entry<Denomination, Integer> entry : dispensedNotes.entrySet()) {
            Denomination denom = entry.getKey();
            int count = entry.getValue();
            cashInventory.put(denom, cashInventory.get(denom) - count);
        }
        
        availableCash -= amount;
        
        System.out.println("Cash Dispenser: Dispensing $" + amount);
        displayDispensedNotes(dispensedNotes);
        
        return true;
    }
    
    public boolean canDispense(double amount) {
        return amount > 0 && amount <= availableCash && amount % 100 == 0;
    }
    
    private Map<Denomination, Integer> calculateNotes(double amount) {
        Map<Denomination, Integer> result = new HashMap<>();
        double remaining = amount;
        
        // Try to dispense using largest denominations first
        Denomination[] denominations = {Denomination.TWO_THOUSAND, Denomination.FIVE_HUNDRED, Denomination.HUNDRED};
        
        for (Denomination denom : denominations) {
            int available = cashInventory.get(denom);
            int needed = (int) (remaining / denom.getValue());
            int toDispense = Math.min(needed, available);
            
            if (toDispense > 0) {
                result.put(denom, toDispense);
                remaining -= toDispense * denom.getValue();
            }
        }
        
        return remaining == 0 ? result : null;
    }
    
    private void displayDispensedNotes(Map<Denomination, Integer> notes) {
        System.out.println("Cash Dispenser: Notes dispensed:");
        for (Map.Entry<Denomination, Integer> entry : notes.entrySet()) {
            System.out.println("  $" + entry.getKey().getValue() + " x " + entry.getValue());
        }
    }
    
    public double getAvailableCash() {
        return availableCash;
    }
    
    public Map<Denomination, Integer> getCashInventory() {
        return new HashMap<>(cashInventory);
    }
}