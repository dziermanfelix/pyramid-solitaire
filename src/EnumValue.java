public enum EnumValue {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

    public EnumValue getComplement() {
        switch(this) {
            case TWO : return JACK;
            case THREE : return TEN;
            case FOUR : return NINE;
            case FIVE : return EIGHT;
            case SIX : return SEVEN;
            case SEVEN : return SIX;
            case EIGHT : return FIVE;
            case NINE : return FOUR;
            case TEN : return THREE;
            case JACK : return TWO;
            case QUEEN : return ACE;
            case KING : return KING;
            case ACE : return QUEEN;
            default : return null;
        }
    }

    public String toString() {
        switch(this) {
            case TWO : return "02";
            case THREE : return "03";
            case FOUR : return "04";
            case FIVE : return "05";
            case SIX : return "06";
            case SEVEN : return "07";
            case EIGHT : return "08";
            case NINE : return "09";
            case TEN : return "10";
            case JACK : return "JK";
            case QUEEN : return "QN";
            case KING : return "KG";
            case ACE : return "AC";
            default : return null;
        }
    }
}
