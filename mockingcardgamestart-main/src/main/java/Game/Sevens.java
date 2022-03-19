package Game;

import Structure.*;

import java.util.ArrayList;

public class Sevens extends CardGame {

    private int noOfCards = 0;
    private Hand[] sevenTableHands = createSevensTableHands();

    protected String generateHelp(){
        return "Please enter a card to play.";
    }

    protected void displaySevensTableHands(){
        int counter = 0;
        for (Hand hand : sevenTableHands){
            output(Suit.values()[counter].displayCamelCase() + " " + hand.toString());
            counter += 1;
        }
    }

    protected void userPlays(Player player, Deck deck){
        displaySevensTableHands();
        output("Your hand is");
        output(player.getHand().toString());
        help();
        boolean cardPlayed = false;
        String userCardRaw = "";
        Card userCard = null;
        while (!cardPlayed && player.getHand().size() > 0) {
            userCardRaw = getString();
            userCard = player.getHand().findACard(userCardRaw);
            if (userCard == null) {
                if (userCardRaw.length() >= 2) {
                    output("Structure.Card not found in your hand");
                }
                cardPlayed = true;
            } else {
                cardPlayed = playToSevenTableHands(userCard, sevenTableHands, true);
                if (cardPlayed) {
                    cardPlayed = player.getHand().playACard(userCard);
                }
            }
        }
        checkIfPlayerHasWon(player);
    }

    protected void computerPlays(Player player, Deck deck){
        boolean cardPlayed = false;
        int counter = 0;
        ArrayList<Card> compupterHand = player.getHand().getHandOfCards();
        while (!cardPlayed && counter < compupterHand.size()){
            cardPlayed = playToSevenTableHands(compupterHand.get(counter), sevenTableHands, true);
            if (cardPlayed){
                compupterHand.remove(counter);
            }
            counter += 1;
        }
        checkIfPlayerHasWon(player);
    }

    protected void checkIfPlayerHasWon(Player player){
        if (player.getHand().size() == 0){
            player.setWinner(true);
        }
    }

    protected Player determineWinner(ArrayList<Player> players) {
        Player winningPlayer = null;
        for (Player player : players) {
            if (player.getHand().size() == 0){
                player.setWinner(true);
                winningPlayer = player;
            }
        }
        return winningPlayer;
    }

    protected Hand[] createSevensTableHands(){
        Hand[] sevensTableHands = new Hand[]{new Hand(), new Hand(), new Hand(), new Hand()};
        return sevensTableHands;
    }

    protected boolean playToSevenHand(Card card, Hand sevensHand, boolean playCard){
        boolean cardHasBeenPlayed = false;
        if (sevensHand.size() == 0 && card.getCardRank() == CardRank.SEVEN){
            cardHasBeenPlayed = true;
            if (playCard) {
                sevensHand.add(card);
            }
        } else if (sevensHand.size() > 0 && card.getCardRank() != CardRank.SEVEN){
            if (sevensHand.getFirstCard().getCardRank().getRank() == (card.getCardRank().getRank() + 1 )){
                cardHasBeenPlayed = true;
                if (playCard) {
                    sevensHand.addAtStart(card);
                }
            } else if ( sevensHand.getLastCard().getCardRank().getRank() == (card.getCardRank().getRank() -1)) {
                cardHasBeenPlayed = true;
                if (playCard) {
                    sevensHand.add(card);
                }
            }
        }
        return cardHasBeenPlayed;
    }

    protected boolean playToSevenTableHands(Card card, Hand[] sevenTableHands, boolean playCard){
        Suit cardSuit = card.getSuit();
        return playToSevenHand(card, sevenTableHands[cardSuit.ordinal()], playCard);
    }

    protected boolean canPlayACard(Card card, Hand[] sevensTableHands){
        return playToSevenTableHands(card, sevensTableHands, false);
    }

    public static void main(String[ ] args) {
        Sevens sevens = new Sevens();
        sevens.setNoOfCards(sevens.noOfCards);
        sevens.play();
    }
}
