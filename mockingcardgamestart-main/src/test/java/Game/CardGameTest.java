package Game;

import Console.ConsoleInput;
import Game.CardGame;
import Structure.Hand;
import Structure.LoadConfig;
import Structure.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class CardGameTest {
    CardGame cardGame = new CardGame();

    @Test
    void getDeck() {
        assertEquals(52, cardGame.getDeck().size());
    }

    @Test
    void cardGameOverride() {
        CardGame overrideCardGame = new CardGame("H3,H4,H5,D6");
        assertEquals(4, overrideCardGame.getDeck().size());
    }

    @Test
    void dealCards() {
        CardGame cardGame = new CardGame("D3,D2,S5,S6");
        Hand hand = new Hand();
        hand = cardGame.dealHand(hand, 3);
        assertEquals("S6, S5, D2", hand.toString());
    }

    @Test
    void outputOne() {
        cardGame.output("Test One");
        assertEquals("Test One", cardGame.userOutput.getStoreOutput().get(0));
    }

    @Test
    void outputTwo() {
        cardGame.output("Test One");
        cardGame.output("Test Two");
        assertEquals("Test Two", cardGame.userOutput.getStoreOutput().get(1));
    }

    @Test
    void outputTwoCount() {
        cardGame.output("Test One");
        cardGame.output("Test Two");
        assertEquals(2, cardGame.userOutput.getStoreOutput().size());
    }


    @Test
    void getNumberOfPlayers() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("3");
        cardGame.userInput.setUserInput(mockScanner);
        assertEquals(3, cardGame.getNumberOfPlayers());
    }

    @Test
    void getInteger() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("3");
        cardGame.userInput.setUserInput(mockScanner);
        assertEquals(3, cardGame.getInteger());
    }

    @Test
    void getIntegerFirstString() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("one");
        when(mockScanner.nextLine()).thenReturn("25");
        cardGame.userInput.setUserInput(mockScanner);
        assertEquals(25, cardGame.getInteger());
    }

    @Test
    void getString() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("yes");
        cardGame.userInput.setUserInput(mockScanner);
        assertEquals("yes", cardGame.getString());
    }

    @Test
    void getComputerPlayersNames() {
        cardGame.setLoadConfig(new LoadConfig());
        assertTrue(cardGame.getComputerPlayersNames().size() > 3);
    }

    @Test
    void createComputerPlayers() {
        cardGame.createComputerPlayers(4);
        assertEquals("Elizabeth Robot", cardGame.players.get(2).getName());
    }

    @Test
    void createComputerPlayersSize() {
        cardGame.createComputerPlayers(3);
        assertEquals(2, cardGame.players.size());
    }

    @Test
    void createHumanPlayer() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("www");
        cardGame.userInput.setUserInput(mockScanner);
        cardGame.createHumanPlayer();
        assertEquals("www", cardGame.players.get(0).getName());
    }

    @Test
    void initiatePlayers() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("5");
        cardGame.userInput.setUserInput(mockScanner);
        cardGame.initiatePlayers();
        assertEquals(5, cardGame.getNumberOfPlayers());
    }

    @Test
    void initiate() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("www");
        when(mockScanner.nextLine()).thenReturn("5");
        cardGame.userInput.setUserInput(mockScanner);
        cardGame.initiate();
        boolean flag = false;
        List<Player> players = cardGame.players;
        for (Player player : players) {
            if (player.getHand().getHandOfCards().size() > 0) {
                flag = true;
            }
        }
        assertTrue(flag);
    }

    @Test
    void play() {
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("www");
        when(mockScanner.nextLine()).thenReturn("5");
        cardGame.userInput.setUserInput(mockScanner);
//        cardGame.play();
        assertTrue(cardGame.finshGame);
    }

}