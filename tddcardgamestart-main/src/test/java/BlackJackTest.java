import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class BlackJackTest {
    BlackJack blackJack = new BlackJack();

    @Test
    void generateHelp() {
        String help = "Please select one of the following options\n";
        help += "Twist\n";
        help += "Stick\n";
        help += "Play New Game\n";
        help += "End\n";
        assertEquals(help,blackJack.generateHelp());
    }

    @Test
    void determineWinner() {
        Player userPlayer = new Player(PlayerType.USER,"Derek",0);
        Hand userHand = new Hand("D3,D5");
        userPlayer.setHand(userHand);
        Player dealer = new Player(PlayerType.DEALER,"Dealer",14);
        Hand dealerHand = new Hand("DA,D5");
        dealer.setHand(dealerHand);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(userPlayer);
        players.add(dealer);
        assertEquals(dealer,blackJack.determineWinner(players));
    }

    @Test
    void getScoreFive() {
        Hand hand = new Hand("D3,D2,DK,DJ,C2");
        assertEquals(27,blackJack.getScore(hand));
    }

    @Test
    void getScoreAceHigh() {
        Hand hand = new Hand("DA,D2");
        assertEquals(13,blackJack.getScore(hand));
    }

    @Test
    void getScoreAceLow() {
        Hand hand = new Hand("DA,D2,CK");
        assertEquals(13,blackJack.getScore(hand));
    }
}