package model.Actions;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;

import model.interfaces.IMouseAction;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PasteAction implements IMouseAction, IUndoable {
boolean isUndo;
boolean isRedo;
ApplicationState state;
    public PasteAction (ApplicationState appState, boolean undo, boolean redo){
        state = appState;
        isUndo = undo;
        isRedo = redo;
    }

    @Override
    public void run() {
        ShapeActions instance = ShapeActions.getInstance();
        GlobalShapeLists globalShapes = GlobalShapeLists.getInstance();
        if(this.isUndo){
            undo(globalShapes);
        } else if(this.isRedo){
            redo(globalShapes);
        } else{
            paste(instance, globalShapes);
        }
    }

    private void paste(ShapeActions instance, GlobalShapeLists globalShapes){
        CommandHistory.add(this);
        List<IShape> clipboard = instance.getClipboard();
        List<IShape> toPaste = new ArrayList<>();
        for(IShape selected : clipboard){
            selected.Paste(toPaste, state);
        }
        globalShapes.addSetToList(toPaste, globalShapes.getMainList());
    }

    private void undo(GlobalShapeLists globalShapes){
        List<IShape> globalList = globalShapes.getMainList();
        Iterator<IShape> iterator = globalList.iterator();
        while(iterator.hasNext()){
            IShape shape = iterator.next();
            if(shape.isCopy()){
                iterator.remove();
                globalShapes.addToList(shape, globalShapes.getDeletedShapes());
            }
        }
        CommandHistory.undo();
        globalShapes.notifyObservers();
    }
    private void redo(GlobalShapeLists globalShapes){
        List<IShape> deletedShapes = globalShapes.getDeletedShapes();
        Iterator<IShape> iterator = deletedShapes.iterator();
        while(iterator.hasNext()){
            IShape shape = iterator.next();
            globalShapes.addToList(shape, globalShapes.getMainList());
        }
        CommandHistory.redo();
    }
}
