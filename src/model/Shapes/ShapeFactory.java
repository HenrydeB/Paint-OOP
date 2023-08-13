package model.Shapes;
import model.Point;
import model.interfaces.IShape;
import model.persistence.ApplicationState;

import java.util.HashMap;
import java.util.List;

public class ShapeFactory {
    public static IShape createRectangle(ApplicationState appState, Point start, Point end, boolean isSelected){
        return new Rectangle(appState, start, end, isSelected);
    }

    public static IShape createEllipse(ApplicationState appState, Point start, Point end, boolean isSelected){
        return new Ellipse(appState, start, end, isSelected);
    }

    public static IShape createTriangle(ApplicationState appState, Point start, Point end, boolean isSelected){
        return new Triangle(appState, start, end, isSelected);
    }

    public static IShape createGroup(ApplicationState state, Point start, Point end, boolean isSelected, List<IShape> set){
        return new Group(state, start, end, isSelected, set);
    }
}
