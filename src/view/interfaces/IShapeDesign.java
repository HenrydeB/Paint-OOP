package view.interfaces;

import java.awt.Graphics2D;
import model.Shapes.Shape;
import model.interfaces.IShape;
import model.persistence.ApplicationState;


public interface IShapeDesign {
    public void design(IShape shape, Graphics2D graphics);
}
