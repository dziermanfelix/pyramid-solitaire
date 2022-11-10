import edu.calpoly.spritely.Size;

import java.awt.*;

public final class Config {
    private static Config instance;
    private final int tileX;
    private final int tileY;
    private final Size tileSize;
    private final Color blankColor;
    private final Color highlightColor;
    private final Integer deckX;
    private final Integer deckY;
    private final Integer turnedX;
    private final Integer turnedY;

    public static Config getInstance() {
        if (instance == null) instance = new Config();
        return instance;
    }

    private Config() {
        tileX = 13;
        tileY = 8;
        tileSize = new Size(80, 105);
        blankColor = Color.BLACK;
        highlightColor = Color.GREEN;
        deckX = 5;
        deckY = 7;
        turnedX = 7;
        turnedY = 7;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public Size getTileSize() {
        return tileSize;
    }

    public Color getBlankColor() {
        return blankColor;
    }

    public Color getHighlightColor() {
        return highlightColor;
    }

    public Integer getDeckX() {
        return deckX;
    }

    public Integer getDeckY() {
        return deckY;
    }

    public Integer getTurnedX() {
        return turnedX;
    }

    public Integer getTurnedY() {
        return turnedY;
    }
}
