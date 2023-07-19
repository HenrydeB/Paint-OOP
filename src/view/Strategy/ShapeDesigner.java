package view.Strategy;

import model.Shapes.Shape;
import model.persistence.ApplicationState;
import view.interfaces.IShapeDesign;

import java.awt.*;

public class ShapeDesigner {

    public void draw(Shape shape, Graphics2D graphics) {
        IShapeDesign designer = null;
        switch(shape.shadingType){
            case OUTLINE -> {
                designer = new OutlineStrategy();
            }
            case FILLED_IN -> {
                designer = new FillStrategy();
            }
            case OUTLINE_AND_FILLED_IN -> {
                designer = new OutFillStrategy();
            }
        }
        designer.design(shape, graphics);
    }
}
