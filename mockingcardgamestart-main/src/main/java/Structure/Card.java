package Structure;

public class Card {

    private Suit suit;

    private CardRank cardRank;

    public Card(Suit suit, CardRank cardRank){
        this.suit = suit;
        this.cardRank = cardRank;
    }

    public Card(String card){
        this.suit = Suit.getSuit(card.substring(0,1));
        this.cardRank = CardRank.getCardRank(card.substring(1,card.length()));
    }

    public Suit getSuit(){
        return suit;
    }

    public CardRank getCardRank(){
        return cardRank;
    }

    public String displayCamelCase(){
        return suit.displayCamelCase() + " " + cardRank.displayCamelCase();
    }

    public String displayOf(){
        return cardRank.displayCamelCase() + " of " + suit.displayCamelCase();
    }

    public String toString(){
        return suit + cardRank.toString();
    }

}
