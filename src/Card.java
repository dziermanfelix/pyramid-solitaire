import java.io.File;

public class Card {
    private final EnumSuit enumSuit;
    private final EnumValue enumValue;
    private final File file;

    public Card(EnumSuit enumSuit, EnumValue enumValue, File file) {
        this.enumSuit = enumSuit;
        this.enumValue = enumValue;
        this.file = file;
    }

    public Card(Card card) {
        this.enumSuit = card.getEnumSuit();
        this.enumValue = card.getEnumValue();
        this.file = card.getFile();
    }

    public EnumSuit getEnumSuit() {
        return enumSuit;
    }

    public EnumValue getEnumValue() {
        return enumValue;
    }

    public File getFile() {
        return file;
    }

    @Override public String toString() {
        return enumSuit + "-" + enumValue;
    }
}
