package model.Shapes;

import model.Point;
import model.persistence.ApplicationState;

 class Ellipse extends Shape{
    public Ellipse(ApplicationState appState, Point initial, Point last, boolean isSelected) {
        super(appState, initial, last, isSelected);
        this.create();
    }
}
