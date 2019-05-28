import edu.calpoly.spritely.*;

import java.awt.*;
import java.util.ArrayList;

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

//        Tile[][] gameBoardTiles = new Tile[config.getTILE_X()][config.getTILE_Y()];
//        for(int i = 0; i < config.getTILE_X(); i++) {
//            for(int j = 0; j < config.getTILE_Y(); j++) {
//                gameBoardTiles[i][j] = new SolidColorTile(Color.BLACK, '.');
//            }
//        }
//        try {
//            ArrayList<PyramidPiece> pieces = pyramidModel.getCards();
//            for(PyramidPiece piece : pieces) {
//                gameBoardTiles[piece.getBoardPosition().getX()][piece.getBoardPosition().getY()] = new PyramidTile(config.getTILESIZE(), Color.BLACK, piece);
//            }
//        }  catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        for(int x = 0; x < config.getTILE_X(); x++) {
//            for(int y = 0; y < config.getTILE_Y(); y++) {
//                frame.addTile(x, y, gameBoardTiles[x][y]);
//            }
//         }

        try {
            PyramidPiece p = pyramidModel.getCardsInHand().get(0);
            frame.addTile(config.getCardsInHandX(), config.getCardsInHandY(), new PyramidTile(config.getTILESIZE(), Color.BLACK, p));
            frame.addTile(config.getUnturnedCardsX(), config.getUnturnedCardsY(), new PyramidTile(config.getTILESIZE(), Color.BLACK, p));
        } catch (Exception e) {
            e.printStackTrace();
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
