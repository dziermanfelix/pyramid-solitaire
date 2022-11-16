import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractModel implements InterfaceModel {
    private ArrayList<InterfaceView> observers = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();

    @Override public void addObserver(InterfaceView observer) {
        lock.lock();
        try {
            ArrayList<InterfaceView> newList = new ArrayList<>(observers);
            newList.add(observer);
            observers = newList;
        } finally {
            lock.unlock();
        }
    }

    @Override public void removeObserver(InterfaceView observer) {
        lock.lock();
        try {
            ArrayList<InterfaceView> newList = new ArrayList<>(observers);
            newList.remove(observer);
            observers = newList;
        } finally {
            lock.unlock();
        }
    }

    public void changeOccurred() {
        notifyObservers();
    }

    @Override public void notifyObservers() {
        ArrayList<InterfaceView> newList;
        lock.lock();
        try {
            newList = new ArrayList<>(observers);
        } finally {
            lock.unlock();
        }
        for (InterfaceView o : newList) {
            o.update();
        }
    }
}
