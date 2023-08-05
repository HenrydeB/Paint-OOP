package view.Strategy;

import model.Shapes.Shape;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.IShapeDesign;

import java.awt.*;

public class ShapeDesigner {

    public void draw(IShape shape, Graphics2D graphics) {
        IShapeDesign designer = null;

        if(shape.isSelected() == false){
            switch(shape.getShadingType()){
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
        } else {
            designer = new SelectedStrategy();
        }
        designer.design(shape, graphics);
    }
}
