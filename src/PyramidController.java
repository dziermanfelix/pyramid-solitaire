
public class PyramidController implements InterfaceController {
    private PyramidModel pyramidModel;
    private PyramidView pyramidView;
    private PyramidMouseHandler pyramidMouseHandler;

    public PyramidController(PyramidModel pyramidModel, PyramidView pyramidView) {
        this.pyramidModel = pyramidModel;
        this.pyramidView = pyramidView;
    }

    @Override
    public void matchLoop() {
        Config config = Config.getInstance();
        int x, y;
        while(pyramidView.getWindow().isRunning()) {
            pyramidView.setFrame(pyramidView.getWindow().waitForNextFrame());
            if(pyramidView.getFrame() == null) {
                pyramidModel.removeObserver(pyramidView);
                break;
            }

            if(pyramidMouseHandler.isClicked()) {
                x = pyramidMouseHandler.getX();
                y = pyramidMouseHandler.getY();

                // bounds check
                if((x >= 0 && x < config.getTILE_X()) && (y >= 0 && y < config.getTILE_Y())) {
                    pyramidModel.receiveClick(x, y);
                    pyramidMouseHandler.toggleClicked();
                }
            }

            if(pyramidView.isBoardChanged()) {
                pyramidView.drawBoard(pyramidView.getFrame());
                pyramidView.getWindow().showNextFrame();
                pyramidView.toggleBoardChanged();
            }
        }
    }

    @Override
    public void setUpMouseHandler(InterfaceView pyramidView) {
        PyramidView view = (PyramidView) pyramidView;
        pyramidMouseHandler = new PyramidMouseHandler(this);
        view.getWindow().setMouseClickedHandler(pyramidMouseHandler);
    }
}
