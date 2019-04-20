import edu.calpoly.spritely.SolidColorTile;
import edu.calpoly.spritely.Tile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PyramidBoardPieceInit {
    private Config config = Config.getInstance();
    private Tile[][] pyramidTiles;
    private ArrayList<PyramidPiece> pieceList = new ArrayList<>();
    private ArrayList<PyramidPiece> cardsInHand = new ArrayList<>();
    private Deck deck = new Deck();

    private ArrayList<EnumPosition> seenPositions = new ArrayList<>();
    private ArrayList<Card> seenCards = new ArrayList<>();

    public PyramidBoardPieceInit() {
        pyramidTiles = new Tile[config.getTILE_X()][config.getTILE_Y()];
        initBoard();
    }

    private void initBoard() {
        for(int x = 0; x < config.getTILE_X(); x++) {
            for(int y = 0; y < config.getTILE_Y(); y++) {
                pyramidTiles[x][y] = new SolidColorTile(Color.BLACK, '.');
            }
        }
        addPyramidPieces();
        addPyramidPiecesToArray();
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

    private Integer indxInRange(Integer min, Integer max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
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
            } while(seenPositions.contains(positions[indx]) || indx == positions.length - 1);
            seenPositions.add(positions[indx]);
            return positions[indx].getPosition();
        }
    }

    private Card randomCard() {
        Integer indx;
        do {
            indx = indxInRange(0, deck.size() - 1);
        } while(seenCards.contains(deck.get(indx)));
        seenCards.add(deck.get(indx));
        return deck.get(indx);
    }

    protected void addPyramidPieces() {
        for(int i = 0; i < deck.size(); i++) {
            Card card = randomCard();
            BoardPosition position = randomBoardPosition();
            if ((position.getX() == config.getCardsInHandX()) && (position.getY() == config.getCardsInHandY())) {
                cardsInHand.add(new PyramidPiece(card, position));
            } else {
                pieceList.add(new PyramidPiece(card, position));
            }
        }
    }

    public SolidColorTile generateBlankTile() {
        return new SolidColorTile(Color.BLACK, '.');
    }

    public Tile[][] getPyramidTiles() {
        return pyramidTiles;
    }
}
