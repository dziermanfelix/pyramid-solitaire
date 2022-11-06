public enum EnumSuit {
    SPADES, HEARTS, DIAMONDS, CLUBS;

    public String toString() {
        switch (this) {
            case SPADES:
                return "S";
            case HEARTS:
                return "H";
            case DIAMONDS:
                return "D";
            case CLUBS:
                return "C";
            default:
                throw new IllegalArgumentException();
        }
    }
}
