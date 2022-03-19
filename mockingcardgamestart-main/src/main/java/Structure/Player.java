package Structure;

import java.util.Random;

public class Player {
    public int levelOfRisk;
    private Hand hand;
    private PlayerType playerType;
    private String name;
    private Boolean winner = false;
    private Random rand = new Random();
    private int minimumRisk = 11;

    public void setRand(Random rand){
        this.rand = rand;
    }

    public Player(PlayerType playerType, String name,Integer levelOfRisk){
        this.playerType = playerType;
        this.name = name;
        this.levelOfRisk = determineRiskLevel(levelOfRisk);
        this.winner = false;
    }

    protected int determineRiskLevel(int levelOfRisk){
        int risk;
        if (levelOfRisk == 0){
            risk = minimumRisk + rand.nextInt(8);
        } else {
            risk = levelOfRisk;
        }
        return risk;
    }

    public void setWinner(Boolean status){
        this.winner = status;
    }

    public Boolean hasWon(){
        return winner;
    }

    public boolean hasHand(){
        return hand != null;
    }

    public PlayerType getPlayerType(){
        return playerType;
    }

    public void setHand(Hand hand){
        this.hand = hand;
    }

    public String getName() { return this.name;}

    public Hand getHand(){
        return this.hand;
    }

}
