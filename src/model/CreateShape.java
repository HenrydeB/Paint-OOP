package model;

import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.gui.PaintCanvas;

import java.util.*;

public class CreateShape implements IUndoable {

    static String command;
    static Shape removedShape;
    static Shape createdShape;
   private static GlobalShapeList globalList;

    public CreateShape () {
       globalList = GlobalShapeList.getInstance();
    }
    public IShape run(String type, Point start, Point end, PaintCanvas canvas, String action){
            command = action;
            switch(command){
                case "add":
                    addShape(type, start, end, canvas);
                    break;
                case "undo":
                    removeShape(canvas);

                    break;
                case "redo":
                    redoShape(canvas);
            }
            canvas.repaint();
            return createdShape;
        }
    private void addShape(String type, Point start, Point end, PaintCanvas canvas){
        createdShape = new Shape(type, start, end);
        GlobalShapeList instance = GlobalShapeList.getInstance();
        List<Shape> shapes = instance.getList();
        shapes.add(createdShape);
        CommandHistory.add(this);
        canvas.repaint();
    }

    private void removeShape(PaintCanvas canvas){
        removedShape = globalList.Remove();
        CommandHistory.undo();
        canvas.repaint();
    }

    private static void redoShape(PaintCanvas canvas){
        CommandHistory.redo();
        GlobalShapeList instance = GlobalShapeList.getInstance();
        List<Shape> shapes = instance.getList();
        List<Shape> removed = instance.getRemovedShapes();

        int removedIndex = removed.size() - 1;

        Shape recent = removed.get(removedIndex);
        removed.remove(removedIndex);
        shapes.add(recent);
        canvas.repaint();
    }
}

