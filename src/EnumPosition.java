public enum EnumPosition {
    ZERO_ZERO,
    ONE_ZERO, ONE_ONE,
    TWO_ZERO, TWO_ONE ,TWO_TWO,
    THREE_ZERO, THREE_ONE, THREE_TWO, THREE_THREE,
    FOUR_ZERO, FOUR_ONE, FOUR_TWO, FOUR_THREE, FOUR_FOUR,
    FIVE_ZERO, FIVE_ONE, FIVE_TWO, FIVE_THREE, FIVE_FOUR, FIVE_FIVE,
    SIX_ZERO, SIX_ONE, SIX_TWO, SIX_THREE, SIX_FOUR, SIX_FIVE, SIX_SIX,
    OFF;

    private Config config = Config.getInstance();

    public BoardPosition getPosition() {
        switch(this) {
            case ZERO_ZERO: return new BoardPosition(6, 0);
            case ONE_ZERO: return new BoardPosition(5, 1);
            case ONE_ONE: return new BoardPosition(7, 1);
            case TWO_ZERO: return new BoardPosition(4, 2);
            case TWO_ONE: return new BoardPosition(6, 2);
            case TWO_TWO: return new BoardPosition(8, 2);
            case THREE_ZERO: return new BoardPosition(3,3);
            case THREE_ONE: return new BoardPosition(5, 3);
            case THREE_TWO: return new BoardPosition(7, 3);
            case THREE_THREE: return new BoardPosition(9, 3);
            case FOUR_ZERO: return new BoardPosition(2, 4);
            case FOUR_ONE: return new BoardPosition(4,4);
            case FOUR_TWO: return new BoardPosition(6, 4);
            case FOUR_THREE: return new BoardPosition(8, 4);
            case FOUR_FOUR: return new BoardPosition(10, 4);
            case FIVE_ZERO: return new BoardPosition(1, 5);
            case FIVE_ONE: return new BoardPosition(3, 5);
            case FIVE_TWO: return new BoardPosition(5,5);
            case FIVE_THREE: return new BoardPosition(7,5);
            case FIVE_FOUR: return new BoardPosition(9, 5);
            case FIVE_FIVE: return new BoardPosition(11, 5);
            case SIX_ZERO: return new BoardPosition(0, 6);
            case SIX_ONE: return new BoardPosition(2, 6);
            case SIX_TWO: return new BoardPosition(4, 6);
            case SIX_THREE: return new BoardPosition(6,6);
            case SIX_FOUR: return new BoardPosition(8, 6);
            case SIX_FIVE: return new BoardPosition(10, 6);
            case SIX_SIX: return new BoardPosition(12, 6);
            case OFF: return new BoardPosition(config.getCardsInHandX(), config.getCardsInHandY());
            default : return null;
        }
    }
}
