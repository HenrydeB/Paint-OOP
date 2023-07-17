package model;

import model.Lists.GlobalShapeLists;
import model.Shapes.Shape;
import model.Shapes.ShapeFactory;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.util.*;

public class CreateShape implements IUndoable {

    static Shape removedShape;
    static Shape createdShape;
   private static GlobalShapeLists globalList;
   //ApplicationState appState

    public CreateShape () {
       globalList = GlobalShapeLists.getInstance();
    }
    public void addShape(ApplicationState appState, Point start, Point end){
        ShapeFactory factory = new ShapeFactory();
        createdShape = factory.createShape(appState, start, end);
        GlobalShapeLists instance = GlobalShapeLists.getInstance();
        instance.AddToList(createdShape);
        CommandHistory.add(this);
    }

    public void removeShape(){
        removedShape = globalList.Remove();
        CommandHistory.undo();
    }

    public void redoShape(){
        CommandHistory.redo();
        GlobalShapeLists instance = GlobalShapeLists.getInstance();
        List<Shape> removed = instance.getRemovedShapes();
        int removedIndex = removed.size() - 1;
        if(removedIndex >= 0 ) {
            Shape recent = removed.get(removedIndex);
            removed.remove(removedIndex);
            instance.AddToList(recent);
        }
    }
}

