package Structure;

import Structure.Hand;
import Structure.Player;
import Structure.PlayerType;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player = new Player(PlayerType.USER, "Derek",0);
    @Test
    void hasWon() {
        player.setWinner(true);
        assertTrue(player.hasWon());
    }

    @Test
    void hasWonNot() {
        player.setWinner(false);
        assertFalse(player.hasWon());
    }

    @Test
    void hasHand() {
        Hand hand = new Hand("S3,S4,D5");
        player.setHand(hand);
        assertTrue(player.hasHand());
    }

    @Test
    void hasHandNot() {
        Hand hand = new Hand("S3,S4,D5");
        assertFalse(player.hasHand());
    }

    @Test
    void getPlayerType() {
        assertEquals(PlayerType.USER, player.getPlayerType());
    }

    @Test
    void getName() {
        assertEquals("Derek", player.getName());
    }

    @Test
    void getHand() {
        Hand hand = new Hand("S3,S4,D5");
        player.setHand(hand);
        assertEquals(hand, player.getHand());
    }

    @Test
    void riskLevelSix(){
        Random mockRand = mock(Random.class);
        when(mockRand.nextInt(8)).thenReturn(6);
        player.setRand(mockRand);
        assertEquals(17, player.determineRiskLevel(0));
    }

    @Test
    void riskLevelZero(){
        Random mockRand = mock(Random.class);
        when(mockRand.nextInt(8)).thenReturn(0);
        player.setRand(mockRand);
        assertEquals(11, player.determineRiskLevel(0));
    }
}