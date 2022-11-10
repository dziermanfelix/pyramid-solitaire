public class PyramidStatePreMove implements InterfaceMoveState {
    private final PyramidModel pyramidModel;

    private int clickX;
    private int clickY;

    public PyramidStatePreMove(PyramidModel pyramidModel) {
        this.pyramidModel = pyramidModel;
    }

    @Override public void receiveClick(int clickX, int clickY) {
        this.clickX = clickX;
        this.clickY = clickY;
        validatePiece();
    }

    private void validatePiece() {
        if (pyramidModel.isPiece(clickX, clickY)) {
            pyramidModel.highlightTile(clickX, clickY);
            pyramidModel.setStartX(clickX);
            pyramidModel.setStartY(clickY);
            pyramidModel.setCurrentState(pyramidModel.getMoveState());
        }
    }
}
