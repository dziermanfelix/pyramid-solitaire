import edu.calpoly.spritely.Size;

public class Config {
    public static Config instance;

    private final int MAX_SQUARE;
    private final Size TILESIZE;

    public static Config getInstance() {
        if(instance == null)
            return instance = new Config();

        return instance;
    }

    public Config() {
        MAX_SQUARE = 13;
        TILESIZE = new Size(50, 70);
    }

    public int getMAX_SQUARE() {
        return MAX_SQUARE;
    }

    public Size getTILESIZE() {
        return TILESIZE;
    }
}
