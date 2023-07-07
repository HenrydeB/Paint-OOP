package model;

import view.gui.PaintCanvas;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class GlobalShapeList {
    private static GlobalShapeList instance;
    private List<Shape> shapeList;
    private List<Shape> removedShapes;

    private GlobalShapeList(){
        shapeList = new ArrayList<>();
        removedShapes = new ArrayList<>();
    }

    public static synchronized GlobalShapeList getInstance(){
        if(instance == null){
            instance = new GlobalShapeList();
        }
        return instance;
    }

    public List<Shape> getList(){
        return shapeList;
    }

    public List<Shape> getRemovedShapes() { return removedShapes; }


    public Shape Remove(){
        int index = shapeList.size() - 1;

        if((index >= 0)) {
            Shape recent = shapeList.get(index);
            shapeList.remove(index);
            removedShapes.add(recent);
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
}
