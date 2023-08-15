package model.interfaces;
import java.awt.Color;
import java.util.List;

import model.Point;
import model.Shapes.Shape;
import model.Shapes.ShapeShadingType;
import model.Shapes.ShapeType;
import model.persistence.ApplicationState;

public interface IShape{
    void create();

    ShapeShadingType getShadingType();
    ShapeType getType();
    int calcSide(int cor1, int cor2);
    void cloneShape(Shape shape);

    void copyStyles(Shape shape);

    boolean isSelected();
    void setSelected();
    boolean isCopy();
     Color getPrimary();
     Color getSecondary();

     int getMinX();
     int getMinY();

     int getMaxX();
     int getMaxY();

     void Move(int deltX, int deltY);

     void Paste(List<IShape> toPaste, ApplicationState state);

     IShape Outline(ApplicationState state);

     List<IShape> UnGroup();
}
