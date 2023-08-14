package model.Lists;

import model.Point;
import model.Shapes.Shape;
import model.Shapes.ShapeFactory;
import model.Shapes.ShapeType;
import model.interfaces.IShape;
import model.interfaces.ISingletonLists;
import model.persistence.ApplicationState;

import java.util.ArrayList;
import java.util.List;

public class ShapeActions implements ISingletonLists {
    private List<IShape> movedShapes;
    private List<IShape> selectedShapes;
    private List<IShape> undoMoves;
    private List<IShape> Clipboard;
    private List<IShape> deletedShapes;
    private static ShapeActions instance;


    public int deltX;
    public int deltY;

    private ShapeActions(){
        movedShapes = new ArrayList<>();
        selectedShapes = new ArrayList<>();
        undoMoves = new ArrayList<>();
        Clipboard = new ArrayList<>();
        deletedShapes = new ArrayList<>();
    }

    public static synchronized ShapeActions getInstance(){
        if(instance == null)
            instance = new ShapeActions();
        return instance;
    }
    @Override
    public void addToList(IShape shape, List<IShape> target){
        target.add(shape);
    }
    @Override
    public void addSetToList(List<IShape> set, List<IShape> target){
        for(IShape shape : set){
            target.add(shape);
        }
    }
    @Override
    public void clearList(List<IShape> list) {

        if(list == selectedShapes) {
            for (IShape shape : list) {
                shape.setSelected();
            }
        }
        list.clear();
    }

    public List<IShape> getDeletedShapes(){return deletedShapes;}

    public List<IShape> getClipboard(){
        return Clipboard;
    }

    public List<IShape> getMovedShapes() {return movedShapes;}


    public List<IShape> getSelectedShapes(){
        return selectedShapes;
    }

    public List<IShape> getUndoMoves() { return undoMoves; }

    public void CopyToClipboard(List<IShape> list){
        for(IShape shape : list){
            if(!shape.isSelected())
                Clipboard.add(shape);
        }
        System.out.println("Copied " + Clipboard.size() + " objects to clipboard");
    }


    public void setDeltas(Point start, Point end){
        deltX = end.x - start.x;
        deltY = end.y - start.y;
    }


}
