public class PyramidStatePreMove implements InterfaceMoveState {
    private final PyramidModel pyramidModel;

    private int x;
    private int y;

    public PyramidStatePreMove(PyramidModel pyramidModel) {
        this.pyramidModel = pyramidModel;
    }

    @Override public void receiveClick(int x, int y) {
        this.x = x;
        this.y = y;
        validatePiece();
    }

    private void validatePiece() {
        if (pyramidModel.isPiece(x, y)) {
            pyramidModel.highlightTile(x, y);
            pyramidModel.setStartX(x);
            pyramidModel.setStartY(y);
            pyramidModel.setCurrentState(pyramidModel.getMoveState());
        }
    }
}
