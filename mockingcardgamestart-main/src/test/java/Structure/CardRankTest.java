package Structure;

import Structure.CardRank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardRankTest {

    @Test
    void getValueAce() { assertEquals(11, CardRank.ACE.getValue());
    }

    @Test
    void getValueKing() { assertEquals(10, CardRank.KING.getValue());
    }

    @Test
    void getValueQueen() { assertEquals(10, CardRank.QUEEN.getValue());
    }

    @Test
    void getValueJack() { assertEquals(10, CardRank.JACK.getValue());
    }

    @Test
    void getValueTen() { assertEquals(10, CardRank.TEN.getValue());
    }

    @Test
    void getValueNine() { assertEquals(9, CardRank.NINE.getValue());
    }

    @Test
    void getRankTen() {
        assertEquals(10, CardRank.TEN.getRank());
    }

    @Test
    void displayCamelCase() {assertEquals("Three", CardRank.THREE.displayCamelCase());
    }

    @Test
    void shortDisplayTen() {
        assertEquals("10", CardRank.TEN.shortDisplay());
    }

    @Test
    void testToStringThree() { assertEquals("3", CardRank.THREE.toString());
    }

    @Test
    void testCardRankTwo(){
        assertEquals(CardRank.TWO, CardRank.getCardRank("2"));
    }
    @Test
    void testCardRankTen(){
        assertEquals(CardRank.TEN, CardRank.getCardRank("10"));
    }
    @Test
    void testCardRankJack(){
        assertEquals(CardRank.JACK, CardRank.getCardRank("J"));
    }
    @Test
    void testCardRankQueen(){
        assertEquals(CardRank.QUEEN, CardRank.getCardRank("Q"));
    }
    @Test
    void testCardRankKing(){
        assertEquals(CardRank.KING, CardRank.getCardRank("K"));
    }
    @Test
    void testCardRankAce(){
        assertEquals(CardRank.ACE, CardRank.getCardRank("A"));
    }
}