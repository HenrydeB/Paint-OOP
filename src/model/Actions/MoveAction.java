package model.Actions;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Point;
import model.Shapes.Group;
import model.Shapes.Shape;
import model.Shapes.ShapeType;
import model.Shapes.Triangle;
import model.interfaces.IMouseAction;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.util.List;

public class MoveAction implements IMouseAction, IUndoable {

    ShapeActions instance;
    boolean isUndo;
    boolean isRedo;
    Point start;
    Point end;
    public MoveAction(boolean undo, boolean redo, Point s, Point e) {
        instance = ShapeActions.getInstance();
        isUndo = undo;
        isRedo = redo;
        start = s;
        end = e;
    }
    @Override
    public void run() {
        if(isUndo)
            undoMove();
        else if(isRedo)
            redoMove();
        else
            moveShapes();
    }

    public void moveShapes(){
        CommandHistory.add(this);

        if(instance.getMovedShapes().size() > 0)
            instance.clearList(instance.getMovedShapes());

        instance.setDeltas(start, end);
        List<IShape> selected = instance.getSelectedShapes();
        for(IShape obj : selected){
            obj.Move(instance.deltX, instance.deltY);
            instance.addToList(obj, instance.getMovedShapes());
        }

        GlobalShapeLists.getInstance().notifyObservers();
    }


    public void undoMove(){
        CommandHistory.undo();

        List<IShape> moved = instance.getMovedShapes();
        int dX = instance.deltX;
        int dY = instance.deltY;
        for(IShape selected : moved){
            selected.Move(-dX, -dY);
            instance.addToList(selected, instance.getUndoMoves());
        }
        instance.clearList(instance.getMovedShapes());
        GlobalShapeLists.getInstance().notifyObservers();
    }

    public void redoMove(){
        CommandHistory.redo();
        List<IShape> moved = instance.getUndoMoves();
        for(IShape obj : moved){
            obj.Move(instance.deltX, instance.deltY);
            instance.addToList(obj, instance.getMovedShapes());
        }
        instance.clearList(instance.getUndoMoves());
        GlobalShapeLists.getInstance().notifyObservers();
    }
}
