package model.Shapes;

import model.Point;
import model.interfaces.IShape;
import model.persistence.ApplicationState;

import java.awt.*;

public class Shape implements IShape {

   public ShapeType type;
   public int width;
   public int height;
   public int minX; // will need to move
   public int minY; // will need to move

   public Point start;
   public Point end;
   public ShapeColor primary;
   ShapeColor secondary;
   public ShapeShadingType shadingType;

    public Shape(ApplicationState appState ,Point initial, Point last){
         type = appState.getActiveShapeType(); //might throw an error because we need to import java.awt.ShapeType
         primary = appState.getActivePrimaryColor();
         secondary = appState.getActiveSecondaryColor();
         shadingType = appState.getActiveShapeShadingType();
         start = initial;
         end = last;
    }
    public void create() {
        width = calcSide(this.start.x, this.end.x);
        height = calcSide(this.start.y, this.end.y);
        minX = Math.min(start.x, end.x);
        minY = Math.min(start.y, end.y);
    }

    private int calcSide(int cor1, int cor2){
        return Math.max(cor1, cor2) - Math.min(cor1, cor2);
    }

    @Override
    public void draw() {

    }

    @Override
    public void move() {

    }

    @Override
    public void paste() {

    }

    @Override
    public void delete() {

    }

    public Color getPrimary(){
        return this.primary.getColor();
    }

    public Color getSecondary(){
        return this.secondary.getColor();
    }

}
