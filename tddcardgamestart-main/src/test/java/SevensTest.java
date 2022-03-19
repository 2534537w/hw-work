import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SevensTest {

    Sevens sevens = new Sevens();
    Hand[] sevensTableHands = sevens.createSevensTableHands();

    @Test
    void createSevensTableHands() {
        assertEquals(4, sevensTableHands.length);
    }

    @Test
    void playToSevenHandCanPlaySevenToEmptyHand() {
        Card card = new Card(Suit.HEARTS, CardRank.SEVEN);
        Hand hand = new Hand();
        sevens.playToSevenHand(card, hand, false);
    }

    @Test
    void playToSevenHandCanNotPlaySevenToHandWithCards() {
        Card card = new Card(Suit.HEARTS, CardRank.SEVEN);
        Hand hand = new Hand("H4");
        assertFalse(sevens.playToSevenHand(card, hand, false));
    }

    @Test
    void playToSevenHandSevenToEmptyHand() {
        Card card = new Card(Suit.HEARTS, CardRank.SEVEN);
        Hand hand = new Hand();
        assertTrue(sevens.playToSevenHand(card, hand, false));
    }

    @Test
    void canPlayToSevenHandSixWithH7Hand() {
        Card card = new Card(Suit.HEARTS, CardRank.SIX);
        Hand hand = new Hand("H7");
        assertTrue(sevens.playToSevenHand(card, hand, false));
    }

    @Test
    void canNotPlayToSevenHandFiveWithH7Hand() {
        Card card = new Card(Suit.HEARTS, CardRank.FIVE);
        Hand hand = new Hand("H7");
        assertFalse(sevens.playToSevenHand(card, hand, false));
    }

    @Test
    void playToSevenHandSixWithH7Hand() {
        Card card = new Card(Suit.HEARTS, CardRank.SIX);
        Hand hand = new Hand("H7");
        assertTrue(sevens.playToSevenHand(card, hand, false));
    }

    @Test
    void canPlayToSevenHandEightWithH7Hand() {
        Card card = new Card(Suit.HEARTS, CardRank.EIGHT);
        Hand hand = new Hand("H7");
        assertTrue(sevens.playToSevenHand(card, hand, false));
    }

    @Test
    void canNotPlayToSevenHandNineWithH7Hand() {
        Card card = new Card(Suit.HEARTS, CardRank.NINE);
        Hand hand = new Hand("H7");
        assertFalse(sevens.playToSevenHand(card, hand, false));
    }

    @Test
    void playToSevenHandEightWithH7Hand() {
        Card card = new Card(Suit.HEARTS, CardRank.EIGHT);
        Hand hand = new Hand("H7");
        assertTrue(sevens.playToSevenHand(card, hand, false));
    }

}