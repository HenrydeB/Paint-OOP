package model.Actions;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Point;
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
            Shape shape = (Shape)obj; //need to fix
            if(shape.type.equals(ShapeType.TRIANGLE)){
                moveTriangle((Triangle)shape, instance.deltX, instance.deltY);
                /*Triangle triangle = (Triangle) shape;
                triangle.moveTriangle(instance.deltX, instance.deltY);*/
            } else {
                shape.minX = shape.minX + instance.deltX;
                shape.minY = shape.minY + instance.deltY;
            }
            instance.addToList(shape, instance.getMovedShapes());
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

        shape.start.put('x', shape.xAxis[0]);
        shape.start.put('y', shape.yAxis[0]);
        shape.end.put('x', shape.xAxis[1]);
        shape.end.put('y', shape.yAxis[1]);
    }

    public void undoMove(){
        CommandHistory.undo();

        List<IShape> moved = instance.getMovedShapes();
        int dX = instance.deltX;
        int dY = instance.deltY;
        for(IShape selected : moved){
            Shape shape = (Shape)selected; //need to fix
            if(shape.type.equals(ShapeType.TRIANGLE)){
                moveTriangle((Triangle) shape, -dX, -dY);
            } else {
                shape.minX = shape.minX + (-dX);
                shape.minY = shape.minY + (-dY);
            }
           // instance.addUndoMoves(shape);
            instance.addToList(shape, instance.getUndoMoves());
        }
        instance.clearList(instance.getMovedShapes());
        GlobalShapeLists.getInstance().notifyObservers();
        CommandHistory.add(this);
    }

    public void redoMove(){
        CommandHistory.redo();
        List<IShape> moved = instance.getUndoMoves();
        System.out.println("moved size" + moved.size());
        for(IShape obj : moved){
            Shape shape = (Shape)obj;
            if(shape.type.equals(ShapeType.TRIANGLE)){
                moveTriangle((Triangle)shape, instance.deltX, instance.deltY);
            } else {
                shape.minX = shape.minX + instance.deltX;
                shape.minY = shape.minY + instance.deltY;
            }
            instance.addToList(shape, instance.getMovedShapes());
        }
        instance.clearList(instance.getUndoMoves());
        GlobalShapeLists.getInstance().notifyObservers();
    }
}
