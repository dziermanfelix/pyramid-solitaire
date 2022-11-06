public class PyramidStateMove implements InterfaceMoveState {
    private final PyramidModel pyramidModel;

    private int x;
    private int y;

    public PyramidStateMove(PyramidModel pyramidModel) {
        this.pyramidModel = pyramidModel;
    }

    @Override public void receiveClick(int x, int y) {
        this.x = x;
        this.y = y;
        int startX = pyramidModel.getStartX();
        int startY = pyramidModel.getStartY();

        // if valid move, do move -- reset move either way
        if (pyramidModel.isValidMove(startX, startY, x, y)) {
            pyramidModel.doMove(pyramidModel.getStartX(), pyramidModel.getStartY(), x, y);
        } else {
            pyramidModel.unHighlightTile(startX, startY);
        }
        pyramidModel.setCurrentState(pyramidModel.getPreMoveState());
    }
}
