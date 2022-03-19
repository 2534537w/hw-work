package Game;

import Game.Snap;
import Structure.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnapTest {

    Snap snap = new Snap();

    @Test
    void isSnapWithSuit() {
        assertTrue(snap.isSnap(new Card("D2"), new Card("D5"), true));
    }

    @Test
    void isNotSnapWithRankAndSnapSuitOn() {
        assertFalse(snap.isSnap(new Card("C5"), new Card("D5"), true));
    }


    @Test
    void isSnapRank() {
        assertTrue(snap.isSnap(new Card("C5"), new Card("D5"), false));
    }

    @Test
    void isNotSnapWithSuitAndSnapSuitOff() {
        assertFalse(snap.isSnap(new Card("D4"), new Card("D5"), false));
    }
}