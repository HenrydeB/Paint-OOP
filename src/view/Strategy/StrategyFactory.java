package view.Strategy;

import model.Shapes.*;
import model.Shapes.Shape;
import model.Shapes.ShapeShadingType;
import model.interfaces.IFactoryPattern;
import model.persistence.ApplicationState;
import view.interfaces.IShapeStrategy;

import java.awt.*;

public class StrategyFactory implements IFactoryPattern{
    Graphics2D graphics; //need to update for the observer pattern maybe?
    Shape strategyShape;
    public StrategyFactory (Graphics2D g, Shape shape){
        graphics = g;
        strategyShape = shape;
    }
    public void strategize(){
        switch(strategyShape.type){
            case RECTANGLE -> {
                RectangleStrategy strategy = new RectangleStrategy(graphics);
                design(strategy, strategyShape);
            }
            case ELLIPSE -> {
                EllipseStrategy strategy = new EllipseStrategy(graphics);
                design(strategy, strategyShape);

            }
            case TRIANGLE -> {
                TriangleStrategy strategy = new TriangleStrategy(graphics);
                design(strategy, strategyShape);
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
