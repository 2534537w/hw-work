package Game;

import java.util.ArrayList;
import java.util.List;

import Structure.*;
import Console.ConsoleOutput;
import Console.ConsoleInput;
import Structure.LoadConfig;

public class CardGame {

    protected Integer noOfCards = 2;
    protected Deck deck;
    public ArrayList<Player> players;
    protected ConsoleOutput userOutput = new ConsoleOutput();
    protected ConsoleInput userInput = new ConsoleInput(userOutput);
    protected boolean finshGame = false;
    private LoadConfig loadConfig = new LoadConfig();


    public void setNoOfCards(Integer noOfCards) {
        this.noOfCards = noOfCards;
    }

    public void setFinishGame(boolean finshGame) {
        this.finshGame = finshGame;
    }

    public void setLoadConfig(LoadConfig loadConfig) {
        this.loadConfig = loadConfig;
    }

    public void setUserInput(ConsoleInput userInput) {
        this.userInput = userInput;
    }

    public Deck getDeck() {
        return deck;
    }

    protected void output(String message) {
        userOutput.output(message);
    }

    protected String generateHelp() {
        return "";
    }

    protected void help() {
        output(generateHelp());
    }

    protected String getString() {
        return userInput.getString();
    }

    public int getInteger() {
        return userInput.getInteger();
    }

    public CardGame() {
        this.deck = new Deck();
        this.players = new ArrayList<Player>();
    }

    public CardGame(String override) {
        this.deck = new Deck(override);
        this.players = new ArrayList<Player>();
    }

    protected void createHumanPlayer() {
        output("What is your name");
        String name = getString();
        players.add(new Player(PlayerType.USER, name, 0));
    }

    protected List<String> getComputerPlayersNames() {
        return loadConfig.getConfig();
    }

    protected void createComputerPlayers(Integer noOfPlayers) {
        List<String> computerNames = getComputerPlayersNames();
        Player dealer = new Player(PlayerType.DEALER, computerNames.remove(0), 17);
        players.add(dealer);
        for (int counter = 2; counter < noOfPlayers; counter++) {
            players.add(new Player(PlayerType.COMPUTER, computerNames.remove(0), 0));
        }
    }

    protected int getNumberOfPlayers() {
        int noOfPlayers = 0;
        while (noOfPlayers < 2 || noOfPlayers > 8) {
            output("How many players, minimum of two and max eight?");
            noOfPlayers = getInteger();
        }
        return noOfPlayers;
    }

    public void initiatePlayers() {
        players.clear();
        createHumanPlayer();
        createComputerPlayers(getNumberOfPlayers());
    }

    public void resetPlayers() {
        for (Player player : players) {
            player.setWinner(false);
        }
    }

    public void dealCards() {
        boolean allCards = false;
        int noOfCards;
        if (this.noOfCards == 0) {
            noOfCards = (int) Math.floor(deck.size() / players.size());
            allCards = true;
        } else {
            noOfCards = this.noOfCards;
        }
        for (Player player : players) {
            Hand hand = new Hand();
            hand = dealHand(hand, noOfCards);
            player.setHand(hand);
        }
        if (allCards) {
            for (Player player : players) {
                if (deck.size() > 0) {
                    player.getHand().add(deck.playACard());
                }
            }
        }
    }

    protected Hand dealHand(Hand hand, int noOfCards) {

        for (int cardCounter = 0; cardCounter < noOfCards; cardCounter++) {
            if (deck.size() > 0) {
                hand.add(deck.playACard());
            }
        }
        return hand;
    }


    public void initiate() {
        initiatePlayers();
        deck.shuffleDeck();
        dealCards();
    }

    protected Player determineWinner(ArrayList<Player> players) {
        Integer winningScore = 0;
        Player winningPlayer = null;
        int currentScore = 0;
        for (Player player : players) {
            currentScore = getScore(player.getHand());
            if (currentScore > winningScore) {
                winningScore = currentScore;
                winningPlayer = player;
            }
        }
        return winningPlayer;
    }

    public void play() {
        initiate();
        Integer counterOfPlayers = 0;
        do {
            playerPlaysHand(players.get(counterOfPlayers));
            counterOfPlayers = (counterOfPlayers + 1) % players.size();
        } while (!players.get(counterOfPlayers).hasWon() && !finshGame);
        resetPlayers();
        Player winner = determineWinner(players);
        showPlayers();
    }

    public int getScore(Hand hand) {
        return 0;
    }

    protected void userPlays(Player player, Deck deck) {
    }

    protected void computerPlays(Player player, Deck deck) {
    }

    protected void playerPlaysHand(Player player) {
        if (player.getPlayerType() == PlayerType.USER) {
            userPlays(player, deck);
        } else {
            computerPlays(player, deck);
        }
    }


    public void showPlayers() {
        String theWinner = "";
        for (Player player : players) {
            if (player.hasWon()) {
                theWinner = "The winner is ";
            } else {
                theWinner = "";
            }
            output(theWinner + player.getName() + " - " + player.getHand().toString());
        }
    }

    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        cardGame.play();
        cardGame.showPlayers();
    }
}
