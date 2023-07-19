package view.Strategy;

import model.Shapes.Shape;
import model.Shapes.Triangle;
import view.interfaces.IShapeDesign;

import java.awt.*;

public class FillStrategy implements IShapeDesign {
    public FillStrategy(){}
    @Override
    public void design(Shape shape, Graphics2D graphics) {
        graphics.setColor(shape.getPrimary());
        switch(shape.type){
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
