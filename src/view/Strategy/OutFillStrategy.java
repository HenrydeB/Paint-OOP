package view.Strategy;

import model.Shapes.Shape;
import model.Shapes.Triangle;
import model.interfaces.IShape;
import view.interfaces.IShapeDesign;

import java.awt.*;

public class OutFillStrategy implements IShapeDesign {
    public OutFillStrategy() {}
    @Override
    public void design(IShape obj, Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(5));
        Shape shape = (Shape)obj;
        switch (obj.getType()) {
            case RECTANGLE -> {
                graphics.setColor(shape.getSecondary());
                graphics.drawRect(shape.minX, shape.minY, shape.width, shape.height);
                graphics.setColor(shape.getPrimary());
                graphics.fillRect(shape.minX, shape.minY, shape.width, shape.height);
            }
            case TRIANGLE -> {
                Triangle triangle = (Triangle) shape;
                graphics.setColor(shape.getSecondary());
                graphics.drawPolygon(triangle.xAxis, triangle.yAxis, 3);
                graphics.setColor(shape.getPrimary());
                graphics.fillPolygon(triangle.xAxis, triangle.yAxis, 3);
            }
            case ELLIPSE -> {
                graphics.setColor(shape.getSecondary());
                graphics.drawOval(shape.minX, shape.minY, shape.width, shape.height);
                graphics.setColor(shape.getPrimary());
                graphics.fillOval(shape.minX, shape.minY, shape.width, shape.height);
            }
        }
    }
}
