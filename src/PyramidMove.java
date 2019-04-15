import java.util.Objects;

public class PyramidMove {
    private BoardPosition start;
    private BoardPosition end;

    public PyramidMove() {}

    public PyramidMove(BoardPosition start, BoardPosition end) {
        this.start = start;
        this.end = end;
    }

    public PyramidMove(PyramidMove matchMove) {
        this.start = matchMove.getStart();
        this.end = matchMove.getEnd();
    }

    public BoardPosition getStart() {
        return start;
    }

    public void setStart(BoardPosition start) {
        this.start = start;
    }

    public BoardPosition getEnd() {
        return end;
    }

    public void setEnd(BoardPosition end) {
        this.end = end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }

        if(!(obj instanceof BoardPosition)) {
            return false;
        }

        PyramidMove move = (PyramidMove) obj;
        int moveStartX = move.start.getX();
        int moveStartY = move.start.getY();
        int moveEndX = move.end.getX();
        int moveEndY = move.end.getY();

        int thisStartX = this.start.getX();
        int thisStartY = this.start.getY();
        int thisEndX = this.end.getX();
        int thisEndY = this.end.getY();

        return moveStartX == thisStartX && moveStartY == thisStartY
                && moveEndX == thisEndX && moveEndY == thisEndY;
    }
}
