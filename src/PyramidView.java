import edu.calpoly.spritely.AnimationFrame;
import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SpriteWindow;
import edu.calpoly.spritely.Tile;

public class PyramidView extends Thread implements InterfaceView {
    private Config config = Config.getInstance();
    private PyramidModel pyramidModel;
    private PyramidController pyramidController;
    private AnimationFrame frame;
    private SpriteWindow window;
    private boolean boardChanged;

    public PyramidView(PyramidModel pyramidModel) {
        this.pyramidModel = pyramidModel;
        this.pyramidController = new PyramidController(pyramidModel, this);
        pyramidModel.addObserver(this);
    }

    public void setupWindow() {
        this.window = new SpriteWindow("Pyramid Solitaire", new Size(config.getTILE_X(), config.getTILE_Y()));
        window.setTileSize(config.getTILESIZE());
        pyramidController.setUpMouseHandler(this);
        drawBoard(window.getInitialFrame());
        window.start();
    }

    @Override
    public synchronized void run() {
        setupWindow();
        pyramidController.matchLoop();
    }

    public void drawBoard(AnimationFrame frame) {
        Tile[][] gameBoardTiles = pyramidModel.getBoardTiles();
        for(int x = 0; x < config.getTILE_X(); x++) {
            for(int y = 0; y < config.getTILE_Y(); y++) {
                frame.addTile(x, y, gameBoardTiles[x][y]);
            }
        }
    }

    @Override
    public void update() {
        boardChanged = true;
    }

    public SpriteWindow getWindow() {
        return window;
    }

    public AnimationFrame getFrame() {
        return frame;
    }

    public void setFrame(AnimationFrame frame) {
        this.frame = frame;
    }

    public boolean isBoardChanged() {
        return boardChanged;
    }

    public void toggleBoardChanged() {
        boardChanged = !boardChanged;
    }
}
