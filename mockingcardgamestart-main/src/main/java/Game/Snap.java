package Game;

import Structure.Card;
import Structure.Deck;
import Structure.Hand;
import Structure.Player;

import java.util.ArrayList;

public class Snap extends CardGame {

    private int noOfCards = 0;
    Hand tableHand = new Hand();
    private boolean snapOnSuit = true;

    public void setSnapOnSuit(boolean snapOnSuit){
        this.snapOnSuit = snapOnSuit;
    }

    protected String generateHelp(){
        return "Please enter a card to (P)lay or (S)nap.";
    }

    protected void userPlays(Player player, Deck deck){
        if (tableHand.size() == 0) {
            tableHand.add(player.getHand().playACard(0));
        }
        output("Table hand " + tableHand.getLastCard());
        Card userCard = player.getHand().playACard(0);
        output("User plays " + userCard.toString());
        help();
        String userChoice = getString();
        if (userChoice.equals("S") && isSnap(tableHand.getLastCard(), userCard, snapOnSuit)){
            setFinishGame(true);
        } else if (userChoice.equals("S")) {
            output("It was not snap");
        }
        tableHand.add(userCard);
    }

    protected boolean isSnap(Card previousCard, Card currentCard, boolean snapOnSuit){
        boolean cardsMatch = false;
        if (snapOnSuit && previousCard.getSuit() == currentCard.getSuit()) {
            cardsMatch = true;
        } else if (!snapOnSuit && previousCard.getCardRank() == currentCard.getCardRank()){
            cardsMatch = true;
        }
        return cardsMatch;
    }

    protected void computerPlays(Player player, Deck deck){
        Card computerCard = player.getHand().playACard(0);
        //output("Computer plays " + computerCard.toString());
        tableHand.add(computerCard);
    }

    protected Player determineWinner(ArrayList<Player> players) {
        Player winningPlayer = null;
        for (Player player : players) {
            if (player.hasWon()){
                winningPlayer = player;
            }
        }
        return winningPlayer;
    }

    public static void main(String[ ] args) {
        Snap snap = new Snap();
        snap.setNoOfCards(snap.noOfCards);
        snap.play();
    }
}
