package model.Lists;

import model.Shapes.Shape;
import model.interfaces.IShape;
import model.interfaces.IShapeSubject;
import model.interfaces.ISingletonLists;
import view.interfaces.IShapeObserver;

import java.util.ArrayList;
import java.util.List;

public class GlobalShapeLists implements IShapeSubject, ISingletonLists {
    private static GlobalShapeLists instance;
    private List<IShape> shapeList;
    private List<IShape> removedShapes;
    private List<IShape> deletedShapes;
    private List<IShapeObserver> observers;

    private GlobalShapeLists(){
        shapeList = new ArrayList<>();
        removedShapes = new ArrayList<>();
        observers = new ArrayList<>();
        deletedShapes = new ArrayList<>();
    }

    public static synchronized GlobalShapeLists getInstance(){
        if(instance == null){
            instance = new GlobalShapeLists();
        }
        return instance;
    }

    public void removeSetFromList(List<IShape> toRemove, List<IShape> removeFrom){
        for(IShape shape : toRemove){
            removeFrom.remove(shape);
        }
    }

    @Override
    public void addToList(IShape shape, List<IShape> targetList){
        targetList.add(shape);
        notifyObservers();
    }

    @Override
    public void addSetToList(List<IShape> list, List<IShape> targetList){
        for(IShape shape : list){
            targetList.add(shape);
        }
        notifyObservers();
    }

    @Override
    public void clearList(List<IShape> list){list.clear();}

    public List<IShape> getDeletedShapes(){return deletedShapes;}
    public List<IShape> getMainList(){
        return shapeList;
    }

    public List<IShape> getRemovedShapes() { return removedShapes; }



    public IShape Remove(List<IShape> target){
        int index = target.size() - 1;
        if((index >= 0)) {
            IShape recent = target.get(index);
            target.remove(index);
            removedShapes.add(recent);
            notifyObservers();
            return recent;
        }
        return null;
    }

    @Override
    public void RemoveObject(IShape shape, List<IShape> target){
        target.remove(shape);
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
