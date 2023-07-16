package model.Lists;

import model.Shapes.Shape;
import model.interfaces.IShapeSubject;
import view.interfaces.IShapeObserver;

import java.util.ArrayList;
import java.util.List;

public class GlobalShapeLists implements IShapeSubject {
    private static GlobalShapeLists instance;
    private List<Shape> shapeList;
    private List<Shape> removedShapes;
    private List<IShapeObserver> observers;
    private List<Shape> selectedShapes;

    public GlobalShapeLists(){
        shapeList = new ArrayList<>();
        removedShapes = new ArrayList<>();
        observers = new ArrayList<>();
        selectedShapes = new ArrayList<>();
    }

    public static synchronized GlobalShapeLists getInstance(){
        if(instance == null){
            instance = new GlobalShapeLists();
        }
        return instance;
    }

    public List<Shape> getList(){
        return shapeList;
    }

    public List<Shape> getRemovedShapes() { return removedShapes; }

    public void AddToList(Shape shape){
        shapeList.add(shape);
        notifyObservers();
    }

    public Shape Remove(){
        int index = shapeList.size() - 1;
        if((index >= 0)) {
            Shape recent = shapeList.get(index);
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

    public Shape Recent() {
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
