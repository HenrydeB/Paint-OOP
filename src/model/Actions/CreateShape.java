package model.Actions;

import model.Actions.CommandHistory;
import model.Lists.GlobalShapeLists;
import model.Point;
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
                shape = ShapeFactory.createRectangle(appState, start, end, false);
            }
            case TRIANGLE -> {
                shape = ShapeFactory.createTriangle(appState, start, end, false);
            }
            case ELLIPSE -> {
                shape = ShapeFactory.createEllipse(appState, start, end, false);
            }
        }
        GlobalShapeLists instance = GlobalShapeLists.getInstance();
        instance.addToList(shape,instance.getMainList());
        CommandHistory.add(this);
    }

    public void undo(){
        removedShape = globalList.Remove(globalList.getMainList());
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
            instance.addToList(recent, instance.getMainList());
        }
        CommandHistory.add(this);
    }


}

