import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackTest {

    // Deck Class tests
    @Test
    public void testShuffleGood() {
        Deck deck = new Deck();
        assertEquals(52, deck.size());
    }

    @Test
    public void testShuffleBoundary() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        deck2.shuffle();
        assertFalse(deck1.draw().toString().equals(deck2.draw().toString())); 
    }

    @Test
    public void testDrawGood() {
        Deck deck = new Deck();
        deck.draw();
        assertEquals(51, deck.size());
    }

    @Test
    public void testDrawBad() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThrows(RuntimeException.class, deck::draw); // Assuming an exception is thrown when trying to draw from an empty deck
    }

    @Test
    public void testDrawBoundary() {
        Deck deck = new Deck();
        for (int i = 0; i < 51; i++) {
            deck.draw();
        }
        assertNotNull(deck.draw());
        assertEquals(0, deck.size());
    }

    // Player Class tests
    @Test
    public void testAddCardToHandGood() {
        Player player = new Player("Test");
        Deck deck = new Deck();
        player.addCardToHand(deck.draw());
        assertEquals(1, player.getHandSize());
    }

    @Test
    public void testAddCardToHandBoundary() {
        Player player = new Player("Test");
        player.addCardToHand(new Card("Hearts", "Ace"));
        assertEquals(11, player.getHandValue());
    }

    @Test
    public void testGetHandValueGood() {
        Player player = new Player("Test");
        player.addCardToHand(new Card("Diamonds", "5"));
        assertEquals(5, player.getHandValue());
    }

    @Test
    public void testGetHandValueBoundary() {
        Player player = new Player("Test");
        player.addCardToHand(new Card("Hearts", "Ace"));
        player.addCardToHand(new Card("Diamonds", "10"));
        player.addCardToHand(new Card("Clubs", "10"));
        assertEquals(21, player.getHandValue()); 
    }

    // Card Class tests
    @Test
    public void testGetValueGood() {
        Card two = new Card("Diamonds", "2");
        assertEquals(2, two.getValue());
    }

    @Test
    public void testGetValueBad() {
        Card invalidCard = new Card("Diamonds", "InvalidRank");
        assertThrows(IllegalArgumentException.class, invalidCard::getValue);
    }

    @Test
    public void testGetValueBoundary() {
        Card ace = new Card("Hearts", "Ace");
        assertEquals(11, ace.getValue());
    }
}
