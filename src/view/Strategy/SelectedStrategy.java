package view.Strategy;

import model.Shapes.Shape;
import model.Shapes.Triangle;
import model.interfaces.IShape;
import view.interfaces.IShapeDesign;

import java.awt.*;

public class SelectedStrategy implements IShapeDesign {
    @Override
    public void design(IShape obj, Graphics2D graphics) {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics.setStroke(stroke);
        graphics.setColor(Color.BLACK);
        Shape shape = (Shape)obj;
        switch(shape.getType()){
            case ELLIPSE -> {
                graphics.drawOval(shape.minX-5, shape.minY-5, shape.width+10, shape.height+10);
            }
            case RECTANGLE -> {
                graphics.drawRect(shape.minX-5, shape.minY-5, shape.width+10, shape.height+10);
            }
            case TRIANGLE -> {
                Triangle triangle = (Triangle) shape;
                graphics.drawPolygon(triangle.xAxis, triangle.yAxis, 3);
            }
        }
    }
}
