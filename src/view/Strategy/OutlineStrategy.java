package view.Strategy;

import model.Shapes.Shape;
import model.Shapes.Triangle;
import model.interfaces.IShape;
import view.interfaces.IShapeDesign;

import java.awt.*;

public class OutlineStrategy implements IShapeDesign {
    public OutlineStrategy() {}
    @Override
    public void design(IShape obj, Graphics2D graphics) {
        graphics.setColor(obj.getPrimary());
        graphics.setStroke(new BasicStroke(5));
        Shape shape = (Shape) obj;
        switch (obj.getType()) {
            case RECTANGLE -> {
                graphics.drawRect(shape.minX, shape.minY, shape.width, shape.height);
            }
            case TRIANGLE -> {
                Triangle triangle = (Triangle) shape;
                graphics.drawPolygon(triangle.xAxis, triangle.yAxis, 3);
            }
            case ELLIPSE -> {
                graphics.drawOval(shape.minX, shape.minY, shape.width, shape.height);
            }

        }
    }
}
