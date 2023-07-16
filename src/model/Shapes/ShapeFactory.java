package model.Shapes;
import model.Point;
import model.persistence.ApplicationState;

public class ShapeFactory {
    public Shape createShape(ApplicationState appState, Point start, Point end) {
        switch(appState.getActiveShapeType()){
            case RECTANGLE -> {
               Rectangle rectangle = new Rectangle(appState, start, end);
                System.out.println(rectangle.width + ", " + rectangle.height);
               return rectangle;
            }
            case ELLIPSE -> {
                Ellipse ellipse = new Ellipse(appState, start, end);
                return ellipse;
            }
            case TRIANGLE -> {
                Triangle triangle = new Triangle(appState, start, end);
                return triangle;
            }
        }
        return null;
    }
}
