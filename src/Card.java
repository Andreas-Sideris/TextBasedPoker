public class Card {
    public final int suit;

    public final int type;

    public String suitval;

    public String typeval;

    public Card(int new_suit, int new_type) {
        suit = new_suit;
        type = new_type;
        switch (new_suit) {
            case 1:
                suitval = "♥";
                break;
            case 2:
                suitval = "♠";
                break;
            case 3:
                suitval = "♦";
                break;
            case 4:
                suitval = "♣";
                break;
        }
        switch (new_type) {
            case 1:
                typeval = "2";
                break;
            case 2:
                typeval = "3";
                break;
            case 3:
                typeval = "4";
                break;
            case 4:
                typeval = "5";
                break;
            case 5:
                typeval = "6";
                break;
            case 6:
                typeval = "7";
                break;
            case 7:
                typeval = "8";
                break;
            case 8:
                typeval = "9";
                break;
            case 9:
                typeval = "10";
                break;
            case 10:
                typeval = "J";
                break;
            case 11:
                typeval = "Q";
                break;
            case 12:
                typeval = "K";
                break;
            case 13:
                typeval = "A";
                break;
        }
    }

    public int getSuit() {
        return suit;
    }

    public int getType() {
        return type;
    }

    public String getSuitVal() {
        return suitval;
    }

    public String getTypeVal() {
        return typeval;
    }

    public String dispCard() {
        return suitval + " " + typeval;
    }
}
