import java.util.Objects;

public class BoardPosition {
    private final int positionX;
    private final int positionY;

    public BoardPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof BoardPosition))
            return false;
        BoardPosition move = (BoardPosition) obj;
        return this.positionX == move.getPositionX() && this.positionY == move.getPositionY();
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    @Override
    public String toString() {
        return "[" + positionX + "," + positionY + "]";
    }
}
