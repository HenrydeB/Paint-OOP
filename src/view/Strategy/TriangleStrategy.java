package view.Strategy;

import model.Shapes.Shape;
import model.Shapes.Triangle;
import view.interfaces.IShapeStrategy;

import java.awt.*;

public class TriangleStrategy implements IShapeStrategy {
    Graphics2D graphics2D;
    public TriangleStrategy(Graphics2D graphics){
        graphics2D = graphics;
    }
    @Override
    public void drawShape(Shape shape) {
        Triangle triangle = (Triangle)shape;
        graphics2D.drawPolygon(triangle.xAxis, triangle.yAxis, 3);
    }

    @Override
    public void fillShape(Shape shape) {
        Triangle triangle = (Triangle)shape;
        graphics2D.fillPolygon(triangle.xAxis, triangle.yAxis, 3);
    }
}
