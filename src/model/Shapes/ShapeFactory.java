package model.Shapes;
import model.Point;
import model.interfaces.IShape;
import model.persistence.ApplicationState;

public class ShapeFactory {
    public static IShape createRectangle(ApplicationState appState, Point start, Point end){
        return new Rectangle(appState, start, end);
    }

    public static IShape createEllipse(ApplicationState appState, Point start, Point end){
        return new Ellipse(appState, start, end);
    }

    public static IShape createTriangle(ApplicationState appState, Point start, Point end){
        return new Triangle(appState, start, end);
    }
}
