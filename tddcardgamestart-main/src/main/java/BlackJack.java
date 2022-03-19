import java.util.ArrayList;

public class BlackJack extends CardGame {
    private int maxScore = 21;
    private int noOfCards = 2;

    protected String generateHelp(){
        String help = "Please select one of the following options\n";
        for (BlackJackActions action : BlackJackActions.values()) {
            help += action.display() + "\n";
        }
        return help;
    }

    protected BlackJackActions getPlayerAction(Player player){
        String userChoice = " ";
        BlackJackActions userAction;
        help();
        if (player.hasHand()) {
            output(player.getHand().toString());
        }
        userChoice = getString();
        userAction = BlackJackActions.getAction(userChoice.substring(0,1).toUpperCase());
        output("You chose " + userAction.display());
        return userAction;
    }

    protected void userPlays(Player player, Deck deck){
        BlackJackActions userAction = BlackJackActions.PLAY;

        while (getScore(player.getHand()) <= maxScore && userAction != BlackJackActions.STICK){
            userAction = getPlayerAction(player);
            if (userAction == BlackJackActions.TWIST){
                player.getHand().add(deck.playACard());
            }
        }
        setFinishGame(true);
    }

    protected void computerPlays(Player player, Deck deck){
        Hand hand = player.getHand();
        while (getScore(hand) <= player.levelOfRisk){
            hand.add(deck.playACard());
        }
    }

    protected Player determineWinner(ArrayList<Player> players){
        Integer winningScore = 0;
        Player winningPlayer = null;
        int currentScore = 0;
        for (Player player : players){
            currentScore = getScore(player.getHand());
            if (currentScore <= maxScore && currentScore > winningScore){
                winningPlayer = player;
                winningScore = currentScore;
            }
        }
        if (winningPlayer != null) {
            winningPlayer.setWinner(true);
        }
        return winningPlayer;
    }


    public int getScore(Hand hand){
        int score = 0;
        Boolean hasAnAce = false;
        for (Card card: hand.getHandOfCards()){
            if (card.getCardRank() == CardRank.ACE){
                hasAnAce = true;
            }
            score += card.getCardRank().getValue();
        }
        if (score > maxScore && hasAnAce){
            score -= CardRank.ACE.getValue() - 1;
        }
        return score;
    }

    public static void main(String[ ] args) {
        BlackJack blackJack = new BlackJack();
        blackJack.setNoOfCards(blackJack.noOfCards);
        blackJack.play();
    }


}
