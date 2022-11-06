import java.util.Objects;

public class BoardPosition {
    private int x;
    private int y;

    public BoardPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof BoardPosition)) {
            return false;
        }

        BoardPosition move = (BoardPosition) obj;

        return this.x == move.getX() && this.y == move.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
