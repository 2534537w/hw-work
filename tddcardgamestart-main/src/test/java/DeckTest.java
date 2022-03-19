import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    Deck deck = new Deck();

    @Test
    void generateDeck() {
        assertEquals(52, deck.size());
    }

    @Test
    void playACard() {
        assertEquals("CA",deck.playACard().toString());
    }

    @Test
    void shuffleDeck() {
        Card oldCard = deck.getLastCard();
        deck.shuffleDeck();
        assertNotEquals(oldCard, deck.getLastCard());
    }
}