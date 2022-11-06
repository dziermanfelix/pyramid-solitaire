import edu.calpoly.spritely.MouseClickedHandler;

public class PyramidMouseHandler implements MouseClickedHandler {

    protected PyramidController pyramidController;
    protected int mouseX;
    protected int mouseY;
    protected boolean clicked;

    public PyramidMouseHandler(PyramidController pyramidController) {
        this.pyramidController = pyramidController;
    }

    @Override public void mouseClicked(int x, int y) {
        this.mouseX = x;
        this.mouseY = y;
        this.clicked = true;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void toggleClicked() {
        clicked = !clicked;
    }
}
