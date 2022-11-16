import edu.calpoly.spritely.SolidColorTile;
import edu.calpoly.spritely.Tile;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class PyramidModel extends AbstractModel implements Runnable, InterfaceModel {
    private final Config config = Config.getInstance();
    private Tile[][] gameBoardTiles;
    private int startX;
    private int startY;
    private final InterfaceMoveState preMoveState;
    private final InterfaceMoveState moveState;
    private InterfaceMoveState currentState;
    private final ReentrantLock lock = new ReentrantLock();
    private final CardManager cardManager = new CardManager();

    public PyramidModel() {
        preMoveState = new PyramidStatePreMove(this);
        moveState = new PyramidStateMove(this);
        currentState = preMoveState;
        initBoard();
    }

    @Override public void run() {
        PyramidView v = new PyramidView(this);
        v.start();
    }

    private void initBoard() {
        // init pyramid tiles array
        gameBoardTiles = new Tile[config.getTileX()][config.getTileY()];
        // start with all blank tiles
        for (int x = 0; x < config.getTileX(); x++) {
            for (int y = 0; y < config.getTileY(); y++) {
                gameBoardTiles[x][y] = new SolidColorTile(Color.BLACK, '.');
            }
        }
        addGamePieces();
        addDeckAndTurnedPieces();
    }

    protected void addGamePieces() {
        List<BoardPosition> boardPositions = EnumPosition.getBoardPositions();
        for (BoardPosition position : boardPositions) {
            Card card = cardManager.getDeck().getNextCard();
            PyramidPiece piece = new PyramidPiece(card, position);
            gameBoardTiles[position.getPositionX()][position.getPositionY()] = PyramidTile.createPyramidTile(piece);
            Common.debugPrint("Adding: " + card + " to " + position);
        }
    }

    protected void addDeckAndTurnedPieces() {
        BoardPosition deckPosition = EnumPosition.DECK.getPosition();
        Card deckCard = cardManager.getDeck().getBackOfDeck();
        PyramidPiece deckPiece = new PyramidPiece(deckCard, deckPosition);
        gameBoardTiles[deckPosition.getPositionX()][deckPosition.getPositionY()] =
                PyramidTile.createPyramidTile(deckPiece);
        BoardPosition turnedPosition = EnumPosition.TURNED.getPosition();
        Card turnedCard = cardManager.getDeck().getNextCard();
        cardManager.getTurnedCards().add(turnedCard);
        PyramidPiece seenPiece = new PyramidPiece(turnedCard, turnedPosition);
        gameBoardTiles[turnedPosition.getPositionX()][turnedPosition.getPositionY()] = PyramidTile.createPyramidTile(seenPiece);
    }

    public void receiveClick(int mouseX, int mouseY) {
        currentState.receiveClick(mouseX, mouseY);
        changeOccurred();
    }

    public void highlightTile(int positionX, int positionY) {
        try {
            gameBoardTiles[positionX][positionY] = new PyramidTile(config.getTileSize(), config.getHighlightColor(),
                    pieceAt(positionX, positionY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unHighlightTile(int positionX, int positionY) {
        try {
            gameBoardTiles[positionX][positionY] = new PyramidTile(config.getTileSize(), config.getBlankColor(),
                    pieceAt(positionX, positionY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isComplement(int startX, int startY, int endX, int endY) {
        Tile startTile = gameBoardTiles[startX][startY];
        Tile endTile = gameBoardTiles[endX][endY];
        if (startTile instanceof PyramidTile && endTile instanceof PyramidTile) {
            PyramidTile startPyramidTile = (PyramidTile) startTile;
            PyramidTile endPyramidTile = (PyramidTile) endTile;
            return startPyramidTile.getPyramidPiece().getCard().getEnumValue().getComplement().equals(endPyramidTile.getPyramidPiece().getCard().getEnumValue());
        }
        return false;
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        Common.debugPrint("Start: " + pieceAt(startX, startY));
        Common.debugPrint("End: " + pieceAt(endX, endY));
        if (isPiece(endX, endY)) {
            if (startX == endX && startY == endY) {
                return isDeckPosition(startX, startY, endX, endY) || isKing(startX, startY, endX, endY);
            }
            return isComplement(startX, startY, endX, endY);
        }
        return false;
    }

    private boolean isDeckPosition(int startX, int startY, int endX, int endY) {
        return startX == config.getDeckX() && startY == config.getDeckY();
    }

    private boolean isKing(int startX, int startY, int endX, int endY) {
        return pieceAt(startX, startY).getCard().getEnumValue().equals(EnumValue.KING);
    }

    public PyramidPiece pieceAt(int positionX, int positionY) {
        if (isPiece(positionX, positionY)) {
            PyramidTile pyramidTile = (PyramidTile) gameBoardTiles[positionX][positionY];
            return pyramidTile.getPyramidPiece();
        }
        throw new NullPointerException("Tile at " + positionX + ", " + positionY + " is not a PyramidTile");
    }

    public boolean isPiece(int positionX, int positionY) {
        return gameBoardTiles[positionX][positionY] instanceof PyramidTile;
    }

    public void doMove(int startX, int startY, int endX, int endY) {
        if (isDeckPosition(startX, startY, endX, endY)) {
            Card newCard = cardManager.getDeck().getNextCard();
            cardManager.getTurnedCards().add(newCard);
        } else if (isTurnedCardPosition(startX, startY, endX, endY)) {
            cardManager.getTurnedCards().remove(cardManager.getTurnedCards().size() - 1);
            gameBoardTiles[endX][endY] = PyramidTile.generateBlankTile();
            gameBoardTiles[startX][startY] = PyramidTile.generateBlankTile();
        } else {
            gameBoardTiles[endX][endY] = PyramidTile.generateBlankTile();
            gameBoardTiles[startX][startY] = PyramidTile.generateBlankTile();
        }
        // add turned card
        Card card = cardManager.getTurnedCards().get(cardManager.getTurnedCards().size() - 1);
        PyramidPiece newCardPiece = new PyramidPiece(card, new BoardPosition(config.getTurnedX(), config.getTurnedY()));
        gameBoardTiles[config.getTurnedX()][config.getTurnedY()] = PyramidTile.createPyramidTile(newCardPiece);
    }

    private boolean isTurnedCardPosition(int startX, int startY, int endX, int endY) {
        return (startX == config.getTurnedX() && startY == config.getTurnedY()) || (endX == config.getTurnedX() && endY == config.getTurnedY());
    }

    public InterfaceMoveState getPreMoveState() {
        return preMoveState;
    }

    public InterfaceMoveState getMoveState() {
        return moveState;
    }

    public void setCurrentState(InterfaceMoveState currentState) {
        this.currentState = currentState;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public Tile[][] getBoardTiles() {
        Tile[][] newArray;
        lock.lock();
        try {
            newArray = gameBoardTiles;
        } finally {
            lock.unlock();
        }
        return newArray;
    }
}
