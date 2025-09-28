public class CardReader {
    private CardReaderStatus status;
    
    public CardReader() {
        this.status = CardReaderStatus.IDLE;
    }
    
    public Card readCard() {
        this.status = CardReaderStatus.READING;
        // In a real implementation, this would read from the physical card
        // For demo purposes, we'll simulate a card read
        System.out.println("Card Reader: Reading card...");
        this.status = CardReaderStatus.IDLE;
        return null; // Will be set by the calling method with actual card data
    }
    
    public void ejectCard() {
        System.out.println("Card Reader: Ejecting card...");
        this.status = CardReaderStatus.EJECTING;
        // Simulate ejection delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.status = CardReaderStatus.IDLE;
        System.out.println("Card Reader: Card ejected. Please take your card.");
    }
    
    public void retainCard() {
        System.out.println("Card Reader: Retaining card due to security reasons...");
        this.status = CardReaderStatus.RETAINING;
    }
    
    public CardReaderStatus getStatus() {
        return status;
    }
}

enum CardReaderStatus {
    IDLE,
    READING,
    EJECTING,
    RETAINING,
    ERROR
}