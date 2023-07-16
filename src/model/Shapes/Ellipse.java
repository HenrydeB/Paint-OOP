package model.Shapes;

import model.Point;
import model.persistence.ApplicationState;

public class Ellipse extends Shape{
    public Ellipse(ApplicationState appState, Point initial, Point last) {
        super(appState, initial, last);
        this.create();
    }
}
