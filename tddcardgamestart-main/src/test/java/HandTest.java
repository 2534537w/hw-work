import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    Hand hand = new Hand("C5,D3,S7");

    @Test
    void getFirstCard() {
        assertEquals("C5", this.hand.getFirstCard().toString());
    }

    @Test
    void getLastCard() {
        assertEquals("S7", this.hand.getLastCard().toString());
    }

    @Test
    void getHandOfCards(){
        assertEquals(3, hand.getHandOfCards().size());
    }

    @Test
    void playACardNoParam(){
        assertEquals("S7", hand.playACard().toString());
    }

    @Test
    void playACardInteger(){
        assertEquals("D3", hand.playACard(1).toString());
    }

    @Test
    void copy(){
        Hand newHand = hand.copy();
        assertNotEquals(newHand, hand);
    }

    @Test
    void copySize(){
        Hand newHand = hand.copy();
        assertEquals(newHand.size(), hand.size());
    }

    @Test
    void isEmpty(){
        assertFalse(hand.isEmpty());
    }

    @Test
    void size(){
        assertEquals(3, hand.size());
    }

    @Test
    void sizeZero(){
        Hand zeroHand = new Hand();
        assertEquals(0, zeroHand.size());
    }
    @Test
    void findACard(){
        assertEquals("D3", hand.findACard("D3").toString());
    }
}