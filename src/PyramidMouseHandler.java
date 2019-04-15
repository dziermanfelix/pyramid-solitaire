import edu.calpoly.spritely.MouseClickedHandler;

public class PyramidMouseHandler implements MouseClickedHandler {

    protected PyramidController pyramidController;
    protected int x;
    protected int y;
    protected boolean clicked;

    public PyramidMouseHandler(PyramidController pyramidController) {
        this.pyramidController = pyramidController;
    }

    @Override
    public void mouseClicked(int x, int y) {
        this.x = x;
        this.y = y;
        this.clicked = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void toggleClicked() {
        clicked = !clicked;
    }
}
