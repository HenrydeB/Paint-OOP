package model.Actions;

import model.Point;
import model.Shapes.*;
import model.Shapes.ShapeFactory;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

public class CopyShape {

    public IShape Copy(ApplicationState state, Shape shape, Point start, Point end){
        IShape duplicate = null;

        if(shape instanceof Group){
            duplicate = ShapeFactory.createGroup(state, start, end, false, ((Group) shape).getGroupList());
            duplicate.copyStyles((Group)shape);
        } else {
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
        }
        return duplicate;
    }
}
