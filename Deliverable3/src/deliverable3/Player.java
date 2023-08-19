import java.util.List;
import java.util.ArrayList;


class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public String displayHand() {
        StringBuilder sb = new StringBuilder();
        for (Card card : hand) {
            sb.append(card).append(" ");
        }
        sb.append("(").append(getHandValue()).append(")");
        return sb.toString();
    }

    public int getHandValue() {
        int value = 0;
        int aces = 0;
        for (Card card : hand) {
            value += card.getValue();
            if (card.getRank().equals("Ace")) aces++;
        }
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }
        return value;
    }

    public int getHandSize() {
        return hand.size();
    }
    public List<Card> getHand() {
	    return hand;
}
}
