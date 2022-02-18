package Structure;

import java.util.HashMap;
import java.util.Map;

public enum CardRank {
    TWO("Two"),
    THREE( "Three"),
    FOUR("Four"),
    FIVE( "Five"),
    SIX("Six"),
    SEVEN("Seven"),
    EIGHT("Eight"),
    NINE("Nine"),
    TEN( "Ten"),
    JACK("Jack"),
    QUEEN("Queen"),
    KING("King"),
    ACE("Ace");

    private String camelCase;

    protected static final Map<String,CardRank> lookup = new HashMap<String,CardRank>();

    static {
        for (CardRank rank : CardRank.values()) {
            lookup.put(rank.shortDisplay(), rank);
        }
    }

    CardRank(String camelCase){
        this.camelCase = camelCase;
    }

    public int getValue(){
        int value = getRank();
        if (value == CardRank.ACE.getRank()){
            value = 11;
        } else if (value > 10) {
            return 10;
        }
        return value;
    }
    public int getRank(){
        return ordinal() + 2;
    }
    public String displayCamelCase(){
        return camelCase;
    }

    public String shortDisplay(){
        String display;
        int rank = getRank();
        if (rank <= CardRank.TEN.getRank()) {
            display = Integer.toString(rank);
        } else {
            display = camelCase.substring(0,1);
        }
        return display;
    }

    public String toString(){
        return shortDisplay();
    }

    public static CardRank getCardRank(String shortCode){
        return lookup.get(shortCode);
    }
}
