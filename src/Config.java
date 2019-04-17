import edu.calpoly.spritely.Size;

import java.awt.*;

public class Config {
    private static Config instance;
    private final int TILE_X;
    private final int TILE_Y;
    private final Size TILESIZE;
    private final Color blankColor;
    private final Color highlightColor;

    public static Config getInstance() {
        if(instance == null)
            return instance = new Config();

        return instance;
    }

    private Config() {
        TILE_X = 13;
        TILE_Y = 8;
        TILESIZE = new Size(65, 90);
        blankColor = Color.BLACK;
        highlightColor = Color.PINK;
    }

    public int getTILE_X() {
        return TILE_X;
    }

    public int getTILE_Y() {
        return TILE_Y;
    }

    public Size getTILESIZE() {
        return TILESIZE;
    }

    public Color getBlankColor() {
        return blankColor;
    }

    public Color getHighlightColor() {
        return highlightColor;
    }
}
