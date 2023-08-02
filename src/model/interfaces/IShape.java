package model.interfaces;
import java.awt.Color;
import model.Shapes.Shape;

public interface IShape{
    void create();


    int calcSide(int cor1, int cor2);
    void cloneShape(Shape shape);

    void copyStyles(Shape shape);

     Color getPrimary();
     Color getSecondary();
}
