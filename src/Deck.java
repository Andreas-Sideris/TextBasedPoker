import java.lang.Math;
public class Deck {
    private Card[] deck;

    private int cards_used;

    public Deck() {
        deck = new Card[52];
        int count = 0;
        for (int suit = 1; suit <= 4; suit++) {
            for (int type = 1; type <= 13; type++) {
                deck[count] = new Card(suit, type);
                count++;
            }
        }
        cards_used = 0;
    }

    public void shuffle() {
        for (int i = 51; i > 0; i--) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck [rand] = temp;
        }
        cards_used = 0;
    }

    public Card[] getDeck() {
        return deck;
    }

    public void showDeck() {
        for (int i = 0; i < deck.length; i++) {
            System.out.println(deck[i].getSuit() + "," + deck[i].getType());
        }
    }

    public int cardsUsed() {
        return deck.length - cards_used;
    }

    public Card deal() {
        if (cards_used != 52) {
            cards_used++;
            return deck[cards_used];
        }
        else {
            throw new IllegalArgumentException("No cards left in deck.");
        }
    }
}
