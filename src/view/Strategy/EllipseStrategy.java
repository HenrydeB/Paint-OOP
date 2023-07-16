package view.Strategy;

import model.Shapes.Shape;
import view.interfaces.IShapeStrategy;

import java.awt.*;

public class EllipseStrategy implements IShapeStrategy {
    Graphics2D graphics2D;
    public EllipseStrategy(Graphics2D g) { graphics2D = g;}
    @Override
    public void drawShape(Shape shape) {
        graphics2D.drawOval(shape.minX, shape.minY, shape.width, shape.height);
    }

    @Override
    public void fillShape(Shape shape) {
        graphics2D.fillOval(shape.minX, shape.minY, shape.width, shape.height);
    }
}
