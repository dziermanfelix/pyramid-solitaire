public enum EnumSuit {
    SPADES, HEARTS, DIAMONDS, CLUBS, NONE;

    public String toString() {
        switch (this) {
            case SPADES:
                return "SPADES";
            case HEARTS:
                return "HEARTS";
            case DIAMONDS:
                return "DIAMONDS";
            case CLUBS:
                return "CLUBS";
            case NONE:
                return "NONE";
            default:
                throw new IllegalArgumentException();
        }
    }
}
