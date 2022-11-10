public class PyramidPiece {
    private final Card card;
    private final BoardPosition boardPosition;
    private char pyramidPieceCharacter;

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

    public char getPyramidPieceCharacter() {
        return pyramidPieceCharacter;
    }

    @Override public String toString() {
        return card + "@" + boardPosition;
    }
}
