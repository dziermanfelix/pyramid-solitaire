import edu.calpoly.spritely.Size;

import java.awt.*;

public final class Config {
    private static Config instance;
    private final int tileX;
    private final int tileY;
    private final Size tileSize;
    private final Color blankColor;
    private final Color highlightColor;
    private final Integer cardsInHandX;
    private final Integer cardsInHandY;
    private final Integer unturnedCardsX;
    private final Integer unturnedCardsY;

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
        cardsInHandX = 5;
        cardsInHandY = 7;
        unturnedCardsX = 7;
        unturnedCardsY = 7;
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

    public Integer getCardsInHandX() {
        return cardsInHandX;
    }

    public Integer getCardsInHandY() {
        return cardsInHandY;
    }

    public Integer getUnturnedCardsX() {
        return unturnedCardsX;
    }

    public Integer getUnturnedCardsY() {
        return unturnedCardsY;
    }
}
