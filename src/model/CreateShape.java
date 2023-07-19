package model;

import model.Lists.GlobalShapeLists;
import model.Shapes.Shape;
import model.Shapes.ShapeFactory;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.util.*;

public class CreateShape implements IUndoable {

    static IShape removedShape;
    static IShape createdShape;
   private static GlobalShapeLists globalList;
   //ApplicationState appState

    public CreateShape () {
       globalList = GlobalShapeLists.getInstance();
    }
    public void addShape(ApplicationState appState, Point start, Point end){
        IShape shape = null;
        switch(appState.getActiveShapeType()){
            case RECTANGLE -> {
                shape = ShapeFactory.createRectangle(appState, start, end);
            }
            case TRIANGLE -> {
                shape = ShapeFactory.createTriangle(appState, start, end);
            }
            case ELLIPSE -> {
                shape = ShapeFactory.createEllipse(appState, start, end);
            }
        }
        GlobalShapeLists instance = GlobalShapeLists.getInstance();
        instance.AddToList(shape);
        CommandHistory.add(this);
    }

    public void removeShape(){
        removedShape = globalList.Remove();
        CommandHistory.undo();
    }

    public void redoShape(){
        CommandHistory.redo();
        GlobalShapeLists instance = GlobalShapeLists.getInstance();
        List<IShape> removed = instance.getRemovedShapes();
        int removedIndex = removed.size() - 1;
        if(removedIndex >= 0 ) {
            IShape recent = removed.get(removedIndex);
            removed.remove(removedIndex);
            instance.AddToList(recent);
        }
    }


}

