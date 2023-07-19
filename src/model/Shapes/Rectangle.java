package model.Shapes;

import model.Point;
import model.interfaces.IShape;
import model.persistence.ApplicationState;

 public class Rectangle extends Shape {
    public Rectangle(ApplicationState appState, Point initial, Point last) {
        super(appState, initial, last);
        this.create();
    }
}
