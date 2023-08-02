package model.Lists;

import model.Point;
import model.Shapes.Shape;
import model.Shapes.ShapeFactory;
import model.Shapes.ShapeType;
import model.interfaces.IShape;
import model.persistence.ApplicationState;

import java.util.ArrayList;
import java.util.List;

public class ShapeActions {
    private List<Shape> movedShapes;
    private List<Shape> selectedShapes;
    private List<Shape> undoMoves;
    private List<Shape> Clipboard;
    private static ShapeActions instance;


    public int deltX;
    public int deltY;

    private ShapeActions(){
        movedShapes = new ArrayList<>();
        selectedShapes = new ArrayList<>();
        undoMoves = new ArrayList<>();
        Clipboard = new ArrayList<>();
    }

    public static synchronized ShapeActions getInstance(){
        if(instance == null)
            instance = new ShapeActions();
        return instance;
    }

    public void addSelected(Shape shape){
        selectedShapes.add(shape);
    }

    public void CopyToClipboard(List<Shape> list){
        for(Shape shape : list){
            if(!shape.isSelected)
                Clipboard.add(shape);
        }
        System.out.println("Copied " + Clipboard.size() + " objects to clipboard");
    }
    public void ClearClipboard(){
        Clipboard.clear();
    }

    public List<Shape> getClipboard(){
        return Clipboard;
    }

    public Shape OutlineShapeFactory(ApplicationState appState, Shape shape){
        IShape outline = null;
        Point start = new Point();
        start.x = shape.start.get('x');
        start.y = shape.start.get('y');
        Point end = new Point();
        end.x = shape.end.get('x');
        end.y = shape.end.get('y');
        switch(shape.type){
            case RECTANGLE -> {
                outline = ShapeFactory.createRectangle(appState, start, end, true);
                outline.cloneShape(shape);
            }
            case TRIANGLE -> {
                outline = ShapeFactory.createTriangle(appState, start, end, true);
            }
            case ELLIPSE -> {
                outline = ShapeFactory.createEllipse(appState, start, end, true);
                outline.cloneShape(shape);
            }
        }
        return (Shape)outline;
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

    public void addListToSelected(List<Shape> outlines){
        for(Shape shape : outlines){
            instance.addSelected(shape);
        }
    }

    public List<Shape> getUndoMoves() { return undoMoves; }
    public void clearUndoMoves() { undoMoves.clear(); }
    public void addUndoMoves(Shape shape) { undoMoves.add(shape);}

}
