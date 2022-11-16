public class Simulator {
    PyramidModel pyramidModel = new PyramidModel();
    PyramidView pyramidView = new PyramidView(pyramidModel);

    public Simulator() {
        pyramidModel.run();
    }
}
