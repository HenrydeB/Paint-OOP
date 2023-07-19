package model.Shapes;

import model.Point;
import model.interfaces.IShape;
import model.persistence.ApplicationState;

 class Ellipse extends Shape{
    public Ellipse(ApplicationState appState, Point initial, Point last) {
        super(appState, initial, last);
        this.create();
    }
}
