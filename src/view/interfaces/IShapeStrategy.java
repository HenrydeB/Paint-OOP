package view.interfaces;

import model.Shapes.Shape;

public interface IShapeStrategy {
    void drawShape(Shape shape);
    void fillShape(Shape shape);
}
