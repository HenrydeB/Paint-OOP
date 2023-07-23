package view.Strategy;

import model.Shapes.Shape;
import model.Shapes.Triangle;
import view.interfaces.IShapeDesign;

import java.awt.*;

public class OutlineStrategy implements IShapeDesign {
    public OutlineStrategy() {}
    @Override
    public void design(Shape shape, Graphics2D graphics) {
        graphics.setColor(shape.getPrimary());
        switch (shape.type) {
            case RECTANGLE -> {
                graphics.setStroke(new BasicStroke(5));
                graphics.drawRect(shape.minX, shape.minY, shape.width, shape.height);
            }
            case TRIANGLE -> {
                Triangle triangle = (Triangle) shape;
                graphics.setStroke(new BasicStroke(5));
                graphics.drawPolygon(triangle.xAxis, triangle.yAxis, 3);
            }
            case ELLIPSE -> {
                graphics.setStroke(new BasicStroke(5));
                graphics.drawOval(shape.minX, shape.minY, shape.width, shape.height);
            }

        }
    }
}
