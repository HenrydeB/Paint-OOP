package view.Strategy;

import model.Shapes.Shape;
import model.Shapes.Triangle;
import model.interfaces.IShape;
import view.interfaces.IShapeDesign;

import java.awt.*;

public class FillStrategy implements IShapeDesign {
    public FillStrategy(){}
    @Override
    public void design(IShape obj, Graphics2D graphics) {
        graphics.setColor(obj.getPrimary());
        Shape shape = (Shape) obj;
        switch(obj.getType()){
            case RECTANGLE -> {
                graphics.fillRect(shape.minX, shape.minY, shape.width, shape.height);
            }
            case TRIANGLE -> {
                Triangle triangle = (Triangle)shape;
                graphics.fillPolygon(triangle.xAxis, triangle.yAxis, 3);
            }
            case ELLIPSE -> {
                graphics.fillOval(shape.minX, shape.minY, shape.width, shape.height);
            }
        }

    }
}
