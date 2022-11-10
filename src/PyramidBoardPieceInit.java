import edu.calpoly.spritely.SolidColorTile;
import edu.calpoly.spritely.Tile;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class PyramidBoardPieceInit {
    private final Config config = Config.getInstance();
    private Tile[][] pyramidTiles;
    private final Deck deck = new Deck();

    public PyramidBoardPieceInit() {
        initBoard();
    }

    private void initBoard() {
        // init pyramid tiles array
        pyramidTiles = new Tile[config.getTileX()][config.getTileY()];
        // start with all blank tiles
        for (int x = 0; x < config.getTileX(); x++) {
            for (int y = 0; y < config.getTileY(); y++) {
                pyramidTiles[x][y] = new SolidColorTile(Color.BLACK, '.');
            }
        }
        addGamePieces();
        addPlayingPieces();
    }

    protected void addGamePieces() {
        Common.debugPrint("OG DECK");
        deck.printDeck();
        List<BoardPosition> boardPositions = EnumPosition.getBoardPositions();
        for (BoardPosition position : boardPositions) {
            Card card = deck.remove(0);
            PyramidPiece piece = new PyramidPiece(card, position);
            pyramidTiles[position.getPositionX()][position.getPositionY()] = PyramidTile.createPyramidTile(piece);
            Common.debugPrint("Adding: " + card + " to " + position);
        }
        Common.debugPrint("GAME PLAYING DECK");
        deck.printDeck();
    }

    protected void addPlayingPieces() {
        BoardPosition unseenPosition = EnumPosition.UNSEEN.getPosition();
        Card unseenCard = deck.getUnseenCard();
        PyramidPiece unseenPiece = new PyramidPiece(unseenCard, unseenPosition);
        pyramidTiles[unseenPosition.getPositionX()][unseenPosition.getPositionY()] =
                PyramidTile.createPyramidTile(unseenPiece);
        BoardPosition seenPosition = EnumPosition.SEEN.getPosition();
        Card seenCard = deck.remove(0);
        PyramidPiece seenPiece = new PyramidPiece(seenCard, seenPosition);
        pyramidTiles[seenPosition.getPositionX()][seenPosition.getPositionY()] = PyramidTile.createPyramidTile(seenPiece);
    }

    private Integer indexInRange(Integer min, Integer max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public SolidColorTile generateBlankTile() {
        return new SolidColorTile(Color.BLACK, '.');
    }

    public Tile[][] getPyramidTiles() {
        return pyramidTiles;
    }
}
