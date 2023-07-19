package view.interfaces;

import java.awt.Graphics2D;
import model.Shapes.Shape;
import model.persistence.ApplicationState;


public interface IShapeDesign {
    public void design(Shape shape, Graphics2D graphics);
}
