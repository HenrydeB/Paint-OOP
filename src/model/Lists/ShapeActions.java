package model.Lists;

import model.Point;
import model.Shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeActions { //might need to implement the IShapeSubject, don't know for sure yet
    private List<Shape> movedShapes;
    private List<Shape> selectedShapes;
    private List<Shape> undoMoves;
    private static ShapeActions instance;

    public int deltX;
    public int deltY;

    public ShapeActions(){
        movedShapes = new ArrayList<>();
        selectedShapes = new ArrayList<>();
        undoMoves = new ArrayList<>();
    }

    public static synchronized ShapeActions getInstance(){
        if(instance == null)
            instance = new ShapeActions();
        return instance;
    }

    public void addSelected(Shape shape){
        selectedShapes.add(shape);
    }

    public void setDeltas(Point start, Point end){
        deltX = end.x - start.x;
        deltY = end.y - start.y;
    }

    public void addMoved(Shape shape){
        movedShapes.add(shape);
    }

    public List<Shape> getMovedShapes() {return movedShapes;}

    public void clearMoved() {movedShapes.clear();}

    public void clearSelected(){
        selectedShapes.clear();
    }

    public List<Shape> getSelectedShapes(){
        return selectedShapes;
    }

    public List<Shape> getUndoMoves() { return undoMoves; }
    public void clearUndoMoves() { undoMoves.clear(); }
    public void addUndoMoves(Shape shape) { undoMoves.add(shape);}
}
