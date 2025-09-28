import java.util.Scanner;

public class Keypad {
    private Scanner scanner;
    
    public Keypad() {
        this.scanner = new Scanner(System.in);
    }
    
    public String getInput(String message) {
        System.out.print("Keypad: " + message + " ");
        return scanner.nextLine();
    }
    
    public String getPin() {
        System.out.print("Keypad: Please enter your PIN: ");
        // In a real ATM, this would mask the input
        return scanner.nextLine();
    }
    
    public double getAmount() {
        System.out.print("Keypad: Enter amount: $");
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Keypad: Invalid amount entered.");
            return -1;
        }
    }
    
    public int getChoice() {
        System.out.print("Keypad: Enter your choice: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Keypad: Invalid choice entered.");
            return -1;
        }
    }
}