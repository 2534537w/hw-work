package Game;

import Game.Sevens;
import Structure.Card;
import Structure.Hand;
import Structure.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SevensTest {

    Sevens sevens = new Sevens();
    Hand[] sevensTableHands = sevens.createSevensTableHands();

    @Test
    void createSevensTableHands(){
        assertEquals(4 , sevensTableHands.length);
    }

    @Test
    void canPlayToSevenHandSevenEmpty(){
        assertTrue(sevens.playToSevenHand(new Card("H7"), new Hand(), false));
    }

    @Test
    void canNotPlayToSevenHandSevenEmpty(){
        assertFalse(sevens.playToSevenHand(new Card("H7"), new Hand("H6,H5"), false));
    }

    @Test
    void playToSevenHandSevenEmpty(){
        Card card = new Card("H7");
        Hand tableHearts = new Hand();
        sevens.playToSevenHand(card, tableHearts, true);
        assertEquals(card, tableHearts.getLastCard());
    }

    @Test
    void playToSevenHandCanNotSixEmpty(){
        Card card = new Card("H6");
        Hand tableHearts = new Hand();
        assertFalse(sevens.playToSevenHand(card, tableHearts, false));
    }

    @Test
    void canPlayToSevenHandSixWithH7Hand(){
        assertTrue(sevens.playToSevenHand(new Card("H6"), new Hand("H7"), false));
    }

    @Test
    void canNotPlayToSevenHandFiveWithH7Hand(){
        assertFalse(sevens.playToSevenHand(new Card("H5"), new Hand("H7"), false));
    }

    @Test
    void playToSevenHandSixWithH7Hand(){
        Card card = new Card("H6");
        Hand tableHearts = new Hand("H7");
        sevens.playToSevenHand(card, tableHearts, true);
        assertEquals(card, tableHearts.getFirstCard());
    }

    @Test
    void canPlayToSevenHandEightWithH7Hand(){
        assertTrue(sevens.playToSevenHand(new Card("H8"), new Hand("H7"), false));
    }

    @Test
    void canNotPlayToSevenHandNineWithH7Hand(){
        assertFalse(sevens.playToSevenHand(new Card("H9"), new Hand("H7"), false));
    }

    @Test
    void playToSevenHandEightWithH7Hand(){
        Card card = new Card("H8");
        Hand tableHearts = new Hand("H7");
        sevens.playToSevenHand(card, tableHearts, true);
        assertEquals(card, tableHearts.getLastCard());
    }
    @Test
    void canPlayToSevenTableHandsHeartSevenEmpty(){
        assertTrue(sevens.playToSevenTableHands(new Card("H7"), sevensTableHands, false));
    }

    @Test
    void canPlayToSevenTableHandsHeartSixHandH7(){
        this.sevensTableHands[Suit.HEARTS.ordinal()].add(new Card("H7"));
        assertTrue(sevens.playToSevenTableHands(new Card("H6"), sevensTableHands, false));
    }

    @Test
    void canPlayToSevenTableHandsHeartEightHandH7(){
        this.sevensTableHands[Suit.HEARTS.ordinal()].add(new Card("H7"));
        assertTrue(sevens.playToSevenTableHands(new Card("H8"), sevensTableHands, false));
    }

    @Test
    void canNotPlayToSevenTableHandsHeartFiveHandH7(){
        this.sevensTableHands[Suit.HEARTS.ordinal()].add(new Card("H7"));
        assertFalse(sevens.playToSevenTableHands(new Card("H5"), sevensTableHands, false));
    }

    @Test
    void canNotPlayToSevenTableHandsHeartNineHandH7(){
        this.sevensTableHands[Suit.HEARTS.ordinal()].add(new Card("H7"));
        assertFalse(sevens.playToSevenTableHands(new Card("H9"), sevensTableHands, false));
    }
    @Test
    void canPlayACardHeartSevenEmpty(){
        assertTrue(sevens.canPlayACard(new Card("H7"), sevensTableHands));
    }

    @Test
    void canPlayACardHeartSixHandH7(){
        this.sevensTableHands[Suit.HEARTS.ordinal()].add(new Card("H7"));
        assertTrue(sevens.canPlayACard(new Card("H6"), sevensTableHands));
    }

    @Test
    void canPlayACardHeartEightHandH7(){
        this.sevensTableHands[Suit.HEARTS.ordinal()].add(new Card("H7"));
        assertTrue(sevens.canPlayACard(new Card("H8"), sevensTableHands));
    }

    @Test
    void canNotPlayACardHeartFiveHandH7(){
        this.sevensTableHands[Suit.HEARTS.ordinal()].add(new Card("H7"));
        assertFalse(sevens.canPlayACard(new Card("H5"), sevensTableHands));
    }

    @Test
    void canNotPlayACardHeartNineHandH7(){
        this.sevensTableHands[Suit.HEARTS.ordinal()].add(new Card("H7"));
        assertFalse(sevens.canPlayACard(new Card("H9"), sevensTableHands));
    }
}