package model.interfaces;

import view.interfaces.IShapeObserver;

public interface IShapeSubject {
    void addObserver(IShapeObserver observer);
    void removeObservers(IShapeObserver observer);
    void notifyObservers();
}
