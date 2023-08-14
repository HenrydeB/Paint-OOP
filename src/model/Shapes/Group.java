package model.Shapes;

import model.Point;
import model.interfaces.IShape;
import model.persistence.ApplicationState;

import java.awt.Color;
import java.util.*;

//This here is the "composite" of the composite pattern
public class Group extends Shape {

     List<IShape> group;
     boolean isGrouped;

    public Group(ApplicationState appState ,Point initial, Point last, boolean selected, List<IShape> set){
        super(appState, initial, last, selected);
        group = new ArrayList<>();
        primary = ShapeColor.BLACK;
        shadingType = ShapeShadingType.OUTLINE;
        type = ShapeType.RECTANGLE;
        addToGroup(set);
        create();
    }

    @Override
    public void create() {
        //TODO: this will be the outline that we create, and then we should just be able to reference the shape list
        // so when we are iterating during the shape creation part of Paint, if we have an instanceOf Group, then we can
        // try to set this up to recursively draw these shapes? This may not be possible because of how paint and repaint work
        super.create();

    }

    public List<IShape> getGroupList(){
        return group;
    }

    public void addToGroup(List<IShape> shapes){
        for(IShape shape : shapes){
            if(shape.isSelected())
                continue;

            if(shape instanceof Group)
                ((Group) shape).isGrouped = true;

            group.add(shape);
        }
    }
    public void clearGroup(){
        group.clear();
    }

    public boolean isGrouped(){
        return isGrouped;
    }

    public void setGrouped(){
        if(isGrouped)
            isGrouped = false;
        else
            isGrouped = true;
    }

    @Override
    public ShapeShadingType getShadingType() {
        return super.getShadingType();
    }

    @Override
    public ShapeType getType() {
        return super.getType();
    }

    @Override
    public int calcSide(int cor1, int cor2) {
        return super.calcSide(cor1, cor2);
    }

    @Override
    public void cloneShape(Shape shape) {

    }

    @Override
    public void copyStyles(Shape shape) {

    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public boolean isCopy() {
        return isCopy;
    }

    @Override
    public Color getPrimary() {
        return primary.getColor();
    }

    @Override
    public Color getSecondary() {
        return secondary.getColor();
    }

    @Override
    public int getMinX() {
        return super.getMinX();
    }

    @Override
    public int getMinY() {
        return super.getMinY();
    }

    @Override
    public int getMaxX() {
        return super.getMaxX();
    }

    @Override
    public int getMaxY() {
        return super.getMaxY();
    }
}
