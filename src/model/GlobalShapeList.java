package model;

import view.gui.PaintCanvas;

import java.util.ArrayList;
import java.util.List;

public class GlobalShapeList {
    private static GlobalShapeList instance;
    private List<Shape> shapeList;

    private GlobalShapeList(){
        shapeList = new ArrayList<>();
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

    public void Add(Shape shape){
        shapeList.add(shape);
    }

    public Shape Remove(){
        Shape recent = shapeList.get(shapeList.size() - 1);
        shapeList.remove(shapeList.size() - 1);
        return recent;
    }

    public void Clear(){
        shapeList.clear();
    }

    public Shape Recent() {
        return shapeList.get((shapeList.size() - 1));
    }
}
