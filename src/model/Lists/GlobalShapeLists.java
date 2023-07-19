package model.Lists;

import model.Shapes.Shape;
import model.interfaces.IShape;
import model.interfaces.IShapeSubject;
import view.interfaces.IShapeObserver;

import java.util.ArrayList;
import java.util.List;

public class GlobalShapeLists implements IShapeSubject {
    private static GlobalShapeLists instance;
    private List<IShape> shapeList;
    private List<IShape> removedShapes;
    private List<IShapeObserver> observers;

    public GlobalShapeLists(){
        shapeList = new ArrayList<>();
        removedShapes = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static synchronized GlobalShapeLists getInstance(){
        if(instance == null){
            instance = new GlobalShapeLists();
        }
        return instance;
    }

    public List<IShape> getList(){
        return shapeList;
    }

    public List<IShape> getRemovedShapes() { return removedShapes; }

    public void AddToList(IShape shape){
        shapeList.add(shape);
        notifyObservers();
    }

    public IShape Remove(){
        int index = shapeList.size() - 1;
        if((index >= 0)) {
            IShape recent = shapeList.get(index);
            shapeList.remove(index);
            removedShapes.add(recent);
            notifyObservers();
            return recent;
        }
        return null;
    }

    public void Clear(){
        shapeList.clear();
    }

    public IShape Recent() {
        return shapeList.get((shapeList.size() - 1));
    }

    @Override
    public void addObserver(IShapeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObservers(IShapeObserver observer) {

    }

    @Override
    public void notifyObservers() {
        for (IShapeObserver observer : observers) {
            observer.update();
        }
    }
}
