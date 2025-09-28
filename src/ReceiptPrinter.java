import java.util.Date;

public class ReceiptPrinter {
    private PrinterStatus status;
    
    public ReceiptPrinter() {
        this.status = PrinterStatus.READY;
    }
    
    public boolean printReceipt(Transaction transaction) {
        if (!isPaperAvailable()) {
            System.out.println("Receipt Printer: Out of paper. Cannot print receipt.");
            return false;
        }
        
        this.status = PrinterStatus.PRINTING;
        
        System.out.println("Receipt Printer: Printing receipt...");
        System.out.println("================================");
        System.out.println("        SECURE BANK ATM        ");
        System.out.println("================================");
        System.out.println("Transaction ID: " + transaction.getTransactionId());
        System.out.println("Type: " + transaction.getType());
        System.out.println("Amount: $" + String.format("%.2f", transaction.getAmount()));
        System.out.println("Card: ****" + transaction.getCardNumber().substring(transaction.getCardNumber().length() - 4));
        System.out.println("Date: " + transaction.getTimestamp());
        System.out.println("Status: " + transaction.getStatus());
        System.out.println("================================");
        System.out.println("   Thank you for banking with us   ");
        System.out.println("================================");
        
        // Simulate printing delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        this.status = PrinterStatus.READY;
        System.out.println("Receipt Printer: Receipt printed successfully.");
        return true;
    }
    
    public boolean isPaperAvailable() {
        // Simulate paper availability check
        return this.status != PrinterStatus.OUT_OF_PAPER;
    }
    
    public PrinterStatus getStatus() {
        return status;
    }
    
    public void setStatus(PrinterStatus status) {
        this.status = status;
    }
}

enum PrinterStatus {
    READY,
    PRINTING,
    OUT_OF_PAPER,
    ERROR
}