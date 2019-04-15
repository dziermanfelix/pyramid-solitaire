import edu.calpoly.spritely.SolidColorTile;
import edu.calpoly.spritely.Tile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PyramidBoardPiece {
    private Config config = Config.getInstance();
    private Tile[][] pyramidTiles;
    private ArrayList<PyramidPiece> pieceList = new ArrayList<>();
    private Deck deck = new Deck();
    private ArrayList<EnumPosition> seenPositions = new ArrayList<>();

    public PyramidBoardPiece() {
        pyramidTiles = new Tile[config.getTILE_X()][config.getTILE_Y()];
        initBoard();
    }

    private void initBoard() {
        for (int x = 0; x < config.getTILE_X(); x++) {
            for (int y = 0; y < config.getTILE_Y(); y++) {
                pyramidTiles[x][y] = new SolidColorTile(Color.BLACK, '.');
            }
            addPyramidPieces();
            addPyramidPiecesToArray();
        }
    }

    private void addPyramidPiecesToArray() {
        for(int x = 0; x < config.getTILE_X(); x++) {
            for(int y = 0; y < config.getTILE_Y(); y++) {
                for(int i = 0; i < pieceList.size(); i++) {
                    PyramidPiece piece = pieceList.get(i);
                    if((piece.getBoardPosition().getX() == x) && (piece.getBoardPosition().getY() == y)) {
                        try {
                            pyramidTiles[x][y] = new PyramidTile(config.getTILESIZE(), Color.BLACK, piece);
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
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

    public SolidColorTile generateBlankTile() {
        return new SolidColorTile(Color.BLACK, '.');
    }

    public Tile[][] getPyramidTiles() {
        return pyramidTiles;
    }
}
