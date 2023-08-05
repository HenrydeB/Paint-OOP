package model.Actions;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Shapes.Shape;
import model.interfaces.IMouseAction;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.util.Iterator;
import java.util.List;

public class DeleteAction implements IMouseAction, IUndoable {
    boolean isUndo;
    boolean isRedo;
    public DeleteAction(boolean undo, boolean redo){
        isUndo = undo;
        isRedo = redo;
    }
    @Override
    public void run() {
        GlobalShapeLists instance = GlobalShapeLists.getInstance();
        ShapeActions listActions = ShapeActions.getInstance();

        if(isUndo){
            undo(instance, listActions);
        } else if(isRedo){
            redo(instance, listActions);
        } else{
            delete(instance, listActions);
        }
        CommandHistory.add(this);
    }

    private static void delete(GlobalShapeLists instance, ShapeActions listActions){
        listActions.clearList(listActions.getDeletedShapes());
        List<IShape> selected = listActions.getSelectedShapes();
        List<IShape> allShapes = instance.getMainList();
        for(IShape shape : selected){
            allShapes.remove(shape);
            listActions.addToList(shape, listActions.getDeletedShapes());
        }
       // listActions.clearSelected();
        listActions.clearList(listActions.getSelectedShapes());
        instance.notifyObservers();
    }

    private static void undo(GlobalShapeLists instance, ShapeActions listActions){
        List<IShape> deleted = listActions.getDeletedShapes();
        for(IShape shape : deleted){
            instance.addToList(shape, instance.getMainList());
            listActions.addToList(shape, listActions.getSelectedShapes());
        }
    }

    private static void redo(GlobalShapeLists instance, ShapeActions listActions){
        List<IShape> selected = listActions.getSelectedShapes();
        for(IShape shape : selected){
            instance.getMainList().remove(shape);
            listActions.addToList(shape, listActions.getDeletedShapes());
        }
        listActions.clearList(listActions.getSelectedShapes());
        instance.notifyObservers();
    }
}
