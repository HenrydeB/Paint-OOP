package model;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Shapes.*;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.util.List;

public class MouseActions implements IUndoable {
    ShapeActions instance;
    ApplicationState state;

    public MouseActions(ApplicationState appState){
        instance = ShapeActions.getInstance();
        state = appState;
    }
    public void selectShapes(Point start, Point end){
        CommandHistory.add(this);
        if(instance.getSelectedShapes().size() > 0){
            instance.clearSelected();
        }
        Rectangle boundBox = new Rectangle(state, start, end);
        List<IShape> createdShapes = GlobalShapeLists.getInstance().getList();
        for(IShape shape : createdShapes){
            if(doesCollide(boundBox, (Shape)shape)){
                instance.addSelected((Shape)shape);
            }
        }
        System.out.println("Selected shapes count = " + instance.getSelectedShapes().size());
    }

    private boolean doesCollide(Shape bound, Shape shape){
        if
        (
            (bound.minX < shape.minX + shape.width) &&
            (bound.minX + bound.width > shape.minX) &&
            (bound.minY < shape.minY + shape.height) &&
            (bound.minY + bound.height > shape.minY)
        )
        {
            return true;
        }
        return false;
    }


    public void moveShapes(Point start, Point end){
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
