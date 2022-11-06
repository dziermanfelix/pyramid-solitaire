import edu.calpoly.spritely.SolidColorTile;
import edu.calpoly.spritely.Tile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PyramidBoardPieceInit {
    private final Config config = Config.getInstance();
    private final Tile[][] pyramidTiles;
    private final ArrayList<PyramidPiece> pieceList = new ArrayList<>();
    private final ArrayList<PyramidPiece> cardsInHand = new ArrayList<>();
    private final Deck deck = new Deck();

    private final ArrayList<EnumPosition> seenPositions = new ArrayList<>();
    private final ArrayList<Card> seenCards = new ArrayList<>();

    public PyramidBoardPieceInit() {
        pyramidTiles = new Tile[config.getTileX()][config.getTileY()];
        initBoard();
    }

    private void initBoard() {
        for(int x = 0; x < config.getTileX(); x++) {
            for(int y = 0; y < config.getTileY(); y++) {
                pyramidTiles[x][y] = new SolidColorTile(Color.BLACK, '.');
            }
        }
        addPyramidPieces();
        addPyramidPiecesToArray();
    }

    private void addPyramidPiecesToArray() {
        for(int x = 0; x < config.getTileX(); x++) {
            for(int y = 0; y < config.getTileY(); y++) {
                for (PyramidPiece piece : pieceList) {
                    if ((piece.getBoardPosition().getX() == x) && (piece.getBoardPosition().getY() == y)) {
                        try {
                            pyramidTiles[x][y] = new PyramidTile(config.getTileSize(), Color.BLACK, piece);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
    }

    private Integer indexInRange(Integer min, Integer max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private BoardPosition randomBoardPosition() {
        EnumPosition[] positions = EnumPosition.values();
        int index;
        if(seenPositions.size() == positions.length - 1) {
            return EnumPosition.OFF.getPosition();
        }
        else {
            do {
                index = indexInRange(0, positions.length - 1);
            } while(seenPositions.contains(positions[index]) || index == positions.length - 1);
            seenPositions.add(positions[index]);
            return positions[index].getPosition();
        }
    }

    private Card randomCard() {
        int index;
        do {
            index = indexInRange(0, deck.size() - 1);
        } while(seenCards.contains(deck.get(index)));
        seenCards.add(deck.get(index));
        return deck.get(index);
    }

    protected void addPyramidPieces() {
        for(int i = 0; i < deck.size(); i++) {
            Card card = randomCard();
            BoardPosition position = randomBoardPosition();
            if((position.getX() == config.getCardsInHandX()) && (position.getY() == config.getCardsInHandY())) {
                cardsInHand.add(new PyramidPiece(card, position));
            } else {
                pieceList.add(new PyramidPiece(card, position));
            }
        }
    }

    public ArrayList<PyramidPiece> getCardsInHand() {
        return cardsInHand;
    }

    public SolidColorTile generateBlankTile() {
        return new SolidColorTile(Color.BLACK, '.');
    }

    public Tile[][] getPyramidTiles() {
        return pyramidTiles;
    }

    public ArrayList<PyramidPiece> getPieceList() {
        return pieceList;
    }
}
