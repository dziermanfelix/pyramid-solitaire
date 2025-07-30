import java.util.ArrayList;

public class PyramidPieceManager {
    private final Deck deck = new Deck();
    private final ArrayList<Card> turnedCards = new ArrayList<>();
    private final ArrayList<PyramidPiece> pyramidPieces = new ArrayList<>();

    public PyramidPieceManager() {
        turnedCards.add(deck.getBackOfDeck());
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Card> getTurnedCards() {
        return turnedCards;
    }

    public ArrayList<PyramidPiece> getPyramidPieces() {
        return pyramidPieces;
    }
}
