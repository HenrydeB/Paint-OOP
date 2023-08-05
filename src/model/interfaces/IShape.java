package model.interfaces;
import java.awt.Color;
import model.Shapes.Shape;
import model.Shapes.ShapeShadingType;
import model.Shapes.ShapeType;

public interface IShape{
    void create();

    ShapeShadingType getShadingType();
    ShapeType getType();
    int calcSide(int cor1, int cor2);
    void cloneShape(Shape shape);

    void copyStyles(Shape shape);

    boolean isSelected();
    boolean isCopy();
     Color getPrimary();
     Color getSecondary();
}
