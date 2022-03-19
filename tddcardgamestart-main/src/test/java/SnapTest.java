import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnapTest {

    Snap snap = new Snap();

    @Test
    void isSnapWithSuit() {
        Card pre = new Card("D3");
        Card curr = new Card("D4");
        assertTrue(snap.isSnap(pre, curr, true));
    }

    @Test
    void isNotSnapWithRankAndSnapSuitOn() {
        Card pre = new Card("C5");
        Card curr = new Card("H5");
        assertFalse(snap.isSnap(pre, curr, true));
    }


    @Test
    void isSnapRank() {
        Card pre = new Card("C5");
        Card curr = new Card("H5");
        assertTrue(snap.isSnap(pre, curr, false));
    }

    @Test
    void isNotSnapWithSuitAndSnapSuitOff() {
        Card pre = new Card("D3");
        Card curr = new Card("D4");
        assertFalse(snap.isSnap(pre, curr, false));
    }
}