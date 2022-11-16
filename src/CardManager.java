import java.util.ArrayList;

public class CardManager {
    private final Deck deck = new Deck();
    private final ArrayList<Card> turnedCards = new ArrayList<>();

    public CardManager() {
        turnedCards.add(deck.getBackOfDeck());
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Card> getTurnedCards() {
        return turnedCards;
    }
}
