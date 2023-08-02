package model.Shapes;

import model.Point;
import model.persistence.ApplicationState;

import java.util.HashMap;

public class Rectangle extends Shape {
    public Rectangle(ApplicationState appState, Point initial, Point last, boolean isSelected) {
        super(appState, initial, last, isSelected);
        this.create();
    }
}
