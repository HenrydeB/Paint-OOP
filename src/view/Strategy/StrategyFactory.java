package view.Strategy;

import model.Shapes.*;
import model.Shapes.Shape;
import model.Shapes.ShapeShadingType;
import model.persistence.ApplicationState;
import view.interfaces.IShapeStrategy;

import java.awt.*;

public class StrategyFactory {
    Graphics2D graphics;
//double check implementation
    public StrategyFactory(Graphics2D g){
        graphics = g;
    }
    public void strategize(Shape shape){
        switch(shape.type){
            case RECTANGLE -> {
                RectangleStrategy strategy = new RectangleStrategy(graphics);
                design(strategy, shape);
            }
            case ELLIPSE -> {
                EllipseStrategy strategy = new EllipseStrategy(graphics);
                design(strategy, shape);

            }
            case TRIANGLE -> {
                TriangleStrategy strategy = new TriangleStrategy(graphics);
                design(strategy, shape);
            }
        }
    }

    private void design(IShapeStrategy strategy, Shape shape){
        graphics.setColor(shape.getPrimary());
        if(shape.shadingType.equals(ShapeShadingType.FILLED_IN))
            strategy.fillShape(shape);
         else if (shape.shadingType.equals(ShapeShadingType.OUTLINE))
            strategy.drawShape(shape);
        else{
            strategy.fillShape(shape);
            graphics.setColor(shape.getSecondary());
            strategy.drawShape(shape);
        }
    }
}
