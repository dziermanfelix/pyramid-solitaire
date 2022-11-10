public enum EnumValue {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE, NONE;

    public EnumValue getComplement() {
        switch (this) {
            case TWO:
                return JACK;
            case THREE:
                return TEN;
            case FOUR:
                return NINE;
            case FIVE:
                return EIGHT;
            case SIX:
                return SEVEN;
            case SEVEN:
                return SIX;
            case EIGHT:
                return FIVE;
            case NINE:
                return FOUR;
            case TEN:
                return THREE;
            case JACK:
                return TWO;
            case QUEEN:
                return ACE;
            case KING:
                return KING;
            case ACE:
                return QUEEN;
            default:
                throw new IllegalArgumentException();
        }
    }

    public String toString() {
        switch (this) {
            case TWO:
                return "TWO";
            case THREE:
                return "THREE";
            case FOUR:
                return "FOUR";
            case FIVE:
                return "FIVE";
            case SIX:
                return "SIX";
            case SEVEN:
                return "SEVEN";
            case EIGHT:
                return "EIGHT";
            case NINE:
                return "NINE";
            case TEN:
                return "TEN";
            case JACK:
                return "JACK";
            case QUEEN:
                return "QUEEN";
            case KING:
                return "KING";
            case ACE:
                return "ACE";
            case NONE:
                return "NONE";
            default:
                return null;
        }
    }
}
