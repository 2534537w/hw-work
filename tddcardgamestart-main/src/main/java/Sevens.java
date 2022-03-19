import java.util.ArrayList;
import java.util.List;

public class Sevens extends CardGame {

    private int noOfCards = 0;
    private Hand[] sevenTableHands = createSevensTableHands();

    protected String generateHelp() {
        return "Please enter a card to play.";
    }

    protected void displaySevensTableHands() {
        int counter = 0;
        for (Hand hand : sevenTableHands) {
            output(Suit.values()[counter].displayCamelCase() + " " + hand.toString());
            counter += 1;
        }
    }

    protected void userPlays(Player player, Deck deck) {
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
                    output("Card not found in your hand");
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

    protected void computerPlays(Player player, Deck deck) {
        boolean cardPlayed = false;
        int counter = 0;
        ArrayList<Card> compupterHand = player.getHand().getHandOfCards();
        while (!cardPlayed && counter < compupterHand.size()) {
            cardPlayed = playToSevenTableHands(compupterHand.get(counter), sevenTableHands, true);
            if (cardPlayed) {
                compupterHand.remove(counter);
            }
            counter += 1;
        }
        checkIfPlayerHasWon(player);
    }

    protected void checkIfPlayerHasWon(Player player) {
        if (player.getHand().size() == 0) {
            player.setWinner(true);
        }
    }

    protected Player determineWinner(ArrayList<Player> players) {
        Player winningPlayer = null;
        for (Player player : players) {
            if (player.getHand().size() == 0) {
                player.setWinner(true);
                winningPlayer = player;
            }
        }
        return winningPlayer;
    }

    protected Hand[] createSevensTableHands() {
        Hand[] sevensTableHands = new Hand[]{new Hand(), new Hand(), new Hand(), new Hand()};
        return sevensTableHands;
    }

    protected boolean playToSevenHand(Card card, Hand sevensHand, boolean playCard) {
        if (card.getCardRank() == CardRank.SEVEN && card.getSuit().equals(Suit.HEARTS)) {
            if (sevensHand.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        List<Card> listOfCards = sevensHand.getHandOfCards();
        for (Card c : listOfCards) {
            if (c.getSuit().equals((card.getSuit()))) {
                if (c.getCardRank().ordinal() + 1 == card.getCardRank().ordinal() ||
                        c.getCardRank().ordinal() - 1 == card.getCardRank().ordinal()
                )
                    return true;
            }
        }
        return false;
    }

    protected boolean playToSevenTableHands(Card card, Hand[] sevenTableHands, boolean playCard) {
        Suit cardSuit = card.getSuit();
        return playToSevenHand(card, sevenTableHands[cardSuit.ordinal()], playCard);
    }

    protected boolean canPlayACard(Card card, Hand[] sevensTableHands) {
        return playToSevenTableHands(card, sevensTableHands, false);
    }

    public static void main(String[] args) {
        Sevens sevens = new Sevens();
        sevens.setNoOfCards(sevens.noOfCards);
        sevens.play();
    }
}
