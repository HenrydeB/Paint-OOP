package model;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Shapes.Shape;
import model.Shapes.ShapeType;
import model.Shapes.Triangle;
import model.interfaces.IMouseAction;
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
            instance.getMovedShapes().clear();

        instance.setDeltas(start, end);
        List<Shape> selected = instance.getSelectedShapes();
        for(Shape shape : selected){
            if(shape.type.equals(ShapeType.TRIANGLE)){
                moveTriangle((Triangle)shape, instance.deltX, instance.deltY);
            } else {
                shape.minX = shape.minX + instance.deltX;
                shape.minY = shape.minY + instance.deltY;
            }
            instance.addMoved(shape);
        }
        GlobalShapeLists.getInstance().notifyObservers();
    }

    private void moveTriangle(Triangle shape, int dX, int dY){
        for(int i = 0; i<= shape.xAxis.length - 1; i++){
            shape.xAxis[i] = shape.xAxis[i] + dX;
            shape.yAxis[i] = shape.yAxis[i] + dY;
        }
        shape.minX = shape.minX + dX;
        shape.minY = shape.minY + dY;
    }

    public void undoMove(){
        CommandHistory.undo();

        List<Shape> moved = instance.getMovedShapes();
        int dX = instance.deltX;
        int dY = instance.deltY;
        for(Shape shape : moved){
            if(shape.type.equals(ShapeType.TRIANGLE)){
                moveTriangle((Triangle) shape, -dX, -dY);
            } else {
                shape.minX = shape.minX + (-dX);
                shape.minY = shape.minY + (-dY);
            }
            instance.addUndoMoves(shape);
        }
        instance.clearMoved();
        GlobalShapeLists.getInstance().notifyObservers();
    }

    public void redoMove(){
        CommandHistory.redo();
        List<Shape> moved = instance.getUndoMoves();
        System.out.println("moved size" + moved.size());
        for(Shape shape : moved){
            if(shape.type.equals(ShapeType.TRIANGLE)){
                moveTriangle((Triangle)shape, instance.deltX, instance.deltY);
            } else {
                shape.minX = shape.minX + instance.deltX;
                shape.minY = shape.minY + instance.deltY;
            }
            instance.addMoved(shape);
        }
        instance.clearUndoMoves();
        GlobalShapeLists.getInstance().notifyObservers();
    }
}
