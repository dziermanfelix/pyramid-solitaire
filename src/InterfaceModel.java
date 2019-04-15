public interface InterfaceModel {
    void addObserver(InterfaceView observer);
    void removeObserver(InterfaceView observer);
    void notifyObservers();
}
