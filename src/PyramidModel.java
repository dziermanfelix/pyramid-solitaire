import edu.calpoly.spritely.Tile;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class PyramidModel extends Thread implements InterfaceModel {
    private int startX;
    private int startY;
    private PyramidBoardPieceInit pyramidBoardPiece = new PyramidBoardPieceInit();
    private Tile[][] gameBoardTiles;   // the tile that is on the board at a given moment
    private InterfaceMoveState preMoveState;
    private InterfaceMoveState moveState;
    private InterfaceMoveState currentState;
    private ArrayList<InterfaceView> observers = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Config config = Config.getInstance();

    private ArrayList<PyramidPiece> cards = new ArrayList<>();
    private ArrayList<PyramidPiece> cardsInHand = new ArrayList<>();

    public PyramidModel() {
        preMoveState = new PyramidStatePreMove(this);
        moveState = new PyramidStateMove(this);
        currentState = preMoveState;
        gameBoardTiles = pyramidBoardPiece.getPyramidTiles();

        cards = pyramidBoardPiece.getPieceList();
        cardsInHand = pyramidBoardPiece.getCardsInHand();
    }

    @Override
    public void run() {
        PyramidView v = new PyramidView(this);
        v.start();
    }

    public void receiveClick(int x, int y) {
        currentState.receiveClick(x, y);
        changeOccurred();
    }

    @Override
    public void addObserver(InterfaceView observer) {
        lock.lock();
        try {
            ArrayList<InterfaceView> newList = new ArrayList<>(observers);
            newList.add(observer);
            observers = newList;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void removeObserver(InterfaceView observer) {
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

    @Override
    public void notifyObservers() {
        ArrayList<InterfaceView> newList;
        lock.lock();
        try {
            newList = new ArrayList<>(observers);
        } finally {
            lock.unlock();
        }
        for(InterfaceView o : newList) {
            o.update();
        }
    }

    public void highlightTile(int x, int y) {
        try {
            gameBoardTiles[x][y] = new PyramidTile(config.getTileSize(), config.getHighlightColor(), pieceAt(x, y));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void unHighlightTile(int x, int y) {
        try {
            gameBoardTiles[x][y] = new PyramidTile(config.getTileSize(), config.getBlankColor(), pieceAt(x, y));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isComplement(int startX, int startY, int endX, int endY) {
        Tile startTile = gameBoardTiles[startX][startY];
        Tile endTile = gameBoardTiles[endX][endY];
        if(startTile instanceof PyramidTile && endTile instanceof PyramidTile) {
            PyramidTile startPyramidTile = (PyramidTile) startTile;
            PyramidTile endPyramidTile = (PyramidTile) endTile;
            if(startPyramidTile.getPyramidPiece().getCard().getEnumValue().getComplement()
                    .equals(endPyramidTile.getPyramidPiece().getCard().getEnumValue())){
                return true;
            }
        }
        return false;
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        if(isPiece(endX, endY)) {
            if(isComplement(startX, startY, endX, endY)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPiece(int x, int y) {
        if(gameBoardTiles[x][y] instanceof PyramidTile) {
            return true;
        }
        return false;
    }

    public PyramidPiece pieceAt(int x, int y) {
        Tile tile = gameBoardTiles[x][y];
        if(tile instanceof PyramidTile) {
            PyramidTile pyramidTile = (PyramidTile) tile;
            return pyramidTile.getPyramidPiece();
        }
        throw new NullPointerException("Tile at " + x + ", " + y + " is not a PyramidTile");
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
