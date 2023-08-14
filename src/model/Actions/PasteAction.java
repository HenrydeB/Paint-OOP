package model.Actions;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Point;
import model.Shapes.Shape;
import model.Shapes.Group;
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
            Shape shape = (Shape)selected; //need to go back and fix
            Point start = new Point();
            Point end = new Point();
            start.x = shape.start.get('x') + 100;
            start.y = shape.start.get('y') + 100;

            end.x = shape.end.get('x') + 100;
            end.y = shape.end.get('y') + 100;
            CopyShape copy = new CopyShape();
            IShape duplicate = copy.Copy(state, shape, start, end);
            toPaste.add(duplicate);

            if(shape instanceof Group){
                paste(shape, toPaste);
            }

        }
        globalShapes.addSetToList(toPaste, globalShapes.getMainList());
    }
    //TODO: maybe move this to the Group class
    private void paste(IShape container, List<IShape> toPaste){
        Group group = (Group)container;
        List<IShape> set = group.getGroupList();
        for(IShape shape : set){
            Shape obj = (Shape)shape; //need to go back and fix
            Point start = new Point();
            Point end = new Point();
            start.x = obj.start.get('x') + 100;
            start.y = obj.start.get('y') + 100;

            end.x = obj.end.get('x') + 100;
            end.y = obj.end.get('y') + 100;
            CopyShape copy = new CopyShape();
            IShape duplicate = copy.Copy(state, obj, start, end);
            toPaste.add(duplicate);

            if(shape instanceof Group)
                paste(shape, toPaste);
        }
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
