import edu.calpoly.spritely.Tile;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class PyramidModel extends Thread implements InterfaceModel {
    private int startX;
    private int startY;
    private final PyramidBoardPieceInit pyramidBoardPiece = new PyramidBoardPieceInit();
    private final Tile[][] gameBoardTiles;
    private final InterfaceMoveState preMoveState;
    private final InterfaceMoveState moveState;
    private InterfaceMoveState currentState;
    private ArrayList<InterfaceView> observers = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Config config = Config.getInstance();
    private final ArrayList<PyramidPiece> cards;
    private final ArrayList<PyramidPiece> cardsInHand;

    public PyramidModel() {
        preMoveState = new PyramidStatePreMove(this);
        moveState = new PyramidStateMove(this);
        currentState = preMoveState;
        gameBoardTiles = pyramidBoardPiece.getPyramidTiles();
        cards = pyramidBoardPiece.getPieceList();
        cardsInHand = pyramidBoardPiece.getCardsInHand();
    }

    @Override public void run() {
        PyramidView v = new PyramidView(this);
        v.start();
    }

    public void receiveClick(int mouseX, int mouseY) {
        currentState.receiveClick(mouseX, mouseY);
        changeOccurred();
    }

    @Override public void addObserver(InterfaceView observer) {
        lock.lock();
        try {
            ArrayList<InterfaceView> newList = new ArrayList<>(observers);
            newList.add(observer);
            observers = newList;
        } finally {
            lock.unlock();
        }
    }

    @Override public void removeObserver(InterfaceView observer) {
        lock.lock();
        try {
            ArrayList<InterfaceView> newList = new ArrayList<>(observers);
            newList.remove(observer);
            observers = newList;
        } finally {
            lock.unlock();
        }
    }

    public void changeOccurred() {
        notifyObservers();
    }

    @Override public void notifyObservers() {
        ArrayList<InterfaceView> newList;
        lock.lock();
        try {
            newList = new ArrayList<>(observers);
        } finally {
            lock.unlock();
        }
        for (InterfaceView o : newList) {
            o.update();
        }
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
        if (isPiece(endX, endY)) {
            return isComplement(startX, startY, endX, endY);
        }
        return false;
    }

    public boolean isPiece(int positionX, int positionY) {
        return gameBoardTiles[positionX][positionY] instanceof PyramidTile;
    }

    public PyramidPiece pieceAt(int positionX, int positionY) {
        Tile tile = gameBoardTiles[positionX][positionY];
        if (tile instanceof PyramidTile) {
            PyramidTile pyramidTile = (PyramidTile) tile;
            return pyramidTile.getPyramidPiece();
        }
        throw new NullPointerException("Tile at " + positionX + ", " + positionY + " is not a PyramidTile");
    }

    public void doMove(int startX, int startY, int endX, int endY) {
        gameBoardTiles[endX][endY] = pyramidBoardPiece.generateBlankTile();
        gameBoardTiles[startX][startY] = pyramidBoardPiece.generateBlankTile();
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

    public ArrayList<PyramidPiece> getCardsInHand() {
        ArrayList<PyramidPiece> newList;
        lock.lock();
        try {
            newList = new ArrayList<>(cardsInHand);
        } finally {
            lock.unlock();
        }
        return newList;
    }

    public ArrayList<PyramidPiece> getCards() {
        ArrayList<PyramidPiece> newList;
        lock.lock();
        try {
            newList = new ArrayList<>(cards);
        } finally {
            lock.unlock();
        }
        return newList;
    }
}
