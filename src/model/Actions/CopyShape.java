package model.Actions;

import model.Point;
import model.Shapes.Shape;
import model.Shapes.ShapeFactory;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

public class CopyShape {

    public IShape Copy(ApplicationState state, Shape shape, Point start, Point end){
        IShape duplicate = null;
        switch(shape.type){
            case RECTANGLE -> {
                duplicate = ShapeFactory.createRectangle(state, start, end, false);
                duplicate.copyStyles(shape);
            } case ELLIPSE -> {
                duplicate = ShapeFactory.createEllipse(state, start, end, false);
                duplicate.copyStyles(shape);

            } case TRIANGLE -> {
                duplicate = ShapeFactory.createTriangle(state, start, end, false);
                duplicate.copyStyles(shape);
            }

        }
        return duplicate;
    }
}
