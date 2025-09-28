import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private List<Card> cards;
    private List<Account> accounts;
    
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.cards = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }
    
    public void addCard(Card card) {
        cards.add(card);
    }
    
    public void addAccount(Account account) {
        accounts.add(account);
    }
    
    // Getters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public List<Card> getCards() { return new ArrayList<>(cards); }
    public List<Account> getAccounts() { return new ArrayList<>(accounts); }
}