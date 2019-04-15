import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SolidColorTile;
import edu.calpoly.spritely.Tile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PyramidBoardPieces {
    private static PyramidBoardPieces instance;
    private int MAXTILE = Config.getInstance().getMAX_SQUARE();
    private Size TILESIZE = Config.getInstance().getTILESIZE();
    private Tile[][] pyramidTiles = new Tile[MAXTILE][MAXTILE];   // the tile that is on the board at a given moment
    private ArrayList<PyramidPiece> pieceList = new ArrayList<>();
    private Deck deck = new Deck();
    private ArrayList<EnumPosition> seenPositions = new ArrayList<>();

    protected void initBoard() {
        for (int x = 0; x < MAXTILE; x++) {
            for (int y = 0; y < MAXTILE; y++) {
                pyramidTiles[x][y] = new SolidColorTile(Color.BLACK, '.');
            }
            addPyramidPieces();
            addPyramidPiecesToArray();
        }
    }

    private void addPyramidPiecesToArray() {
        for(int x = 0; x < MAXTILE; x++) {
            for(int y = 0; y < MAXTILE; y++) {
                for(int i = 0; i < pieceList.size(); i++) {
                    PyramidPiece piece = pieceList.get(i);
                    if((piece.getBoardPosition().getX() == x) && (piece.getBoardPosition().getY() == y)) {
                        try {
                            pyramidTiles[x][y] = new PyramidTile(TILESIZE, Color.BLACK, piece);
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
    }

    public static PyramidBoardPieces getInstance() {
        if(instance == null) {
            instance = new PyramidBoardPieces();
        }
        return instance;
    }

    private PyramidBoardPieces() {
        init();
    }

    private void init() {
        initBoard();
    }

    private BoardPosition randomBoardPosition() {
        EnumPosition[] positions = EnumPosition.values();
        Integer indx;
        if(seenPositions.size() == positions.length - 1) {
            return EnumPosition.OFF.getPosition();
        }
        else {
            do {
                indx = indxInRange(0, positions.length - 1);
            } while (seenPositions.contains(positions[indx]) || indx == positions.length - 1);
            seenPositions.add(positions[indx]);
            return positions[indx].getPosition();
        }
    }

    /**
     * Index In Range
     * Helper method to get a random in range of the cards.
     * @return Random index.
     */
    private Integer indxInRange(Integer min, Integer max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    protected void addPyramidPieces() {
        for(Card card : deck) {
            pieceList.add(new PyramidPiece(card, randomBoardPosition()));
        }
    }

    public Tile[][] getPyramidTiles() {
        return pyramidTiles;
    }
}
