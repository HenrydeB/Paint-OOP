package model;

import java.util.ArrayList;
import java.util.List;

public class GlobalShapeList {
    private static GlobalShapeList instance;
    private List<Shape> shapeList;

    private GlobalShapeList(){
        shapeList = new ArrayList<>();
    }

    public static GlobalShapeList getInstance(){
        if(instance == null){
            instance = new GlobalShapeList();
        }
        return instance;
    }

    public void Add(Shape shape){
        System.out.println("new shape added");
        this.shapeList.add(shape);
    }

    public void Remove(Shape shape){
        int index = this.shapeList.indexOf(shape);
        this.shapeList.remove(index);
    }

    public void Clear(){
        this.shapeList.clear();
    }

    public Shape Recent() {
        return this.shapeList.get((this.shapeList.size() - 1));
    }
}
