package model;

import model.interfaces.IShape;
import view.gui.PaintCanvas;

import java.util.List;

public class CreateShape {

    static Shape createdShape;
   private static GlobalShapeList globalList;

    public CreateShape (){
       globalList = GlobalShapeList.getInstance();
    }
    public static IShape run(String type, Point start, Point end,PaintCanvas canvas, String action){

            switch(action){
                case "add":
                    addShape(type, start, end, canvas);
                    break;
                case "undo":
                    removeShape(canvas);
                    break;
                case "redo":

            }
            canvas.repaint();
            return createdShape;
        }
    private static void addShape(String type, Point start, Point end, PaintCanvas canvas){
        createdShape = new Shape(type, start, end);
        System.out.println("createdShape type = " + createdShape.type);
        GlobalShapeList instance = GlobalShapeList.getInstance();
        List<Shape> shapes = instance.getList();
        System.out.println("before size = " + (shapes.size() -1));
        shapes.add(createdShape);
        CommandHistory.add(createdShape); //this adds an IUndoable, so i guess this needs to be similar to a shape?
        canvas.repaint();
    }

    private static void removeShape(PaintCanvas canvas){
        globalList.Remove();
        CommandHistory.undo();
        canvas.repaint();
    }

    private static void redoShape(PaintCanvas canvas){
        CommandHistory.redo();
    }
}

