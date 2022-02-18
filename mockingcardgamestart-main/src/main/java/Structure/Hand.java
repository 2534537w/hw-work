package Structure;

import java.util.*;

public class Hand{
    protected ArrayList<Card> handOfCards;


    public Hand(){
        this.handOfCards = new ArrayList<Card>();
    }

    public Hand(String[] listOfCards){
        this.handOfCards = new ArrayList<Card>();
        for (String card : listOfCards){
            this.handOfCards.add(new Card(card));
        }
    }

    public Hand(String listOfCards){
        this.handOfCards = new ArrayList<Card>();
        for (String card : listOfCards.split(",")){
            this.handOfCards.add(new Card(card));
        }
    }

    public ArrayList<Card> getHandOfCards(){
        return handOfCards;
    }

    public Card getFirstCard() {
        return handOfCards.get(0);
    }

    public Card getLastCard() {
        return handOfCards.get(size()-1);
    }

    public Card findACard(String cardShortCode) {
        boolean cardFound = false;
        int counter = 0;
        Card card = null;
        while (!cardFound && counter < size()){
            if (handOfCards.get(counter).toString().equals(cardShortCode)){
                cardFound = true;
                card = handOfCards.get(counter);
            }
            counter += 1;
        }
        return card;
    }

    public Boolean playACard(Card card){
        return handOfCards.remove(card);
    }

    public Card playACard(){
        return playACard(handOfCards.size()-1);
    }

    public Card playACard(int userCard){
        return handOfCards.remove(userCard);
    }

    public void add(Card card){
        handOfCards.add(card);
    }

    public void addAtStart(Card card) {handOfCards.add(0, card);}

    public void set(int index, Card card){
        handOfCards.set(index,card);
    }

    public Hand copy(){
        Hand newHand = new Hand();
        for (Card card : getHandOfCards()){
            newHand.add(card);
        }
        return newHand;
    }

    public boolean isEmpty(){
        boolean result = false;
        if (handOfCards.size() == 0 ){
            result = true;
        }
        return result;
    }

    public void remove(Card card){
        handOfCards.remove(card);
    }

    public void clear(){
        handOfCards.clear();
    }

    public Integer size(){
        return handOfCards.size();
    }

    public void sortHand(){
        Collections.sort(handOfCards, Comparator.comparing(Card::toString));
    }

    public void sortHandByFace(){

        try {
            Collections.sort(handOfCards, Comparator.comparing(Card::getCardRank));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String toString(){
        String display = "";
        String prefix = "";
        for (Card card : handOfCards){
            display += prefix + card;
            prefix = ", ";
        }
        return display;
    }
}
