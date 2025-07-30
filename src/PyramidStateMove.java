public class PyramidStateMove implements InterfaceMoveState {
    private final PyramidModel pyramidModel;

    public PyramidStateMove(PyramidModel pyramidModel) {
        this.pyramidModel = pyramidModel;
    }

    @Override
    public void receiveClick(int clickX, int clickY) {
        int startX = pyramidModel.getStartX();
        int startY = pyramidModel.getStartY();
        // if valid move, do move, reset move either way
        if (pyramidModel.isValidMove(startX, startY, clickX, clickY)) {
            pyramidModel.doMove(pyramidModel.getStartX(), pyramidModel.getStartY(), clickX, clickY);
        } else {
            pyramidModel.unHighlightTile(startX, startY);
        }
        pyramidModel.setCurrentState(pyramidModel.getPreMoveState());
    }
}
