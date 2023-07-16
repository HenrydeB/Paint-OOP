package view.Strategy;

import view.interfaces.IShapeStrategy;
import javax.swing.JComponent;
import model.Shapes.Shape;
import java.awt.*;

public class RectangleStrategy implements IShapeStrategy {
    Graphics2D graphics2D;
    public RectangleStrategy(Graphics2D g){
        graphics2D = g;
    }
    @Override
    public void drawShape(Shape shape) {
        graphics2D.drawRect(shape.minX, shape.minY, shape.width, shape.height);
    }

    @Override
    public void fillShape(Shape shape) {
        graphics2D.fillRect(shape.minX, shape.minY, shape.width, shape.height);
    }
}
