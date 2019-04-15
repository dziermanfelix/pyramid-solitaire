public class PyramidPiece {
    private Card card;
    private BoardPosition boardPosition;
    private char c;

    public PyramidPiece(Card card, BoardPosition boardPosition) {
        this.card = card;
        this.boardPosition = boardPosition;
    }

    public BoardPosition getBoardPosition() {
        return boardPosition;
    }

    public Card getCard() {
        return card;
    }

    public char getC() {
        return c;
    }
}
