package model;

import model.interfaces.IShape;

public class Shape implements IShape{

    String type;
    int width;
    int height;

    Point start;
    Point end;

    public Shape(String reqType, Point initial){

        type = reqType; //this may later come from the ApplicationState
        start = initial;
        switch(type.toLowerCase()){
            case "rectangle":
                createRectangle();
            /*case "circle":
                createCircle();
            case "triangle":
                createTriangle();
             */

        }
    }

    private int calcSide(int cor1, int cor2){
        //regardless of where the first click is, I imagine this will work
        return Math.max(cor1, cor2) - Math.min(cor1, cor2);
    }

    @Override
    public void createRectangle() {


        //We need to create the list and add our shape to the list maybe here?
        //maybe we can add it right after we call the function

        GlobalShapeList shapeList = GlobalShapeList.getInstance();
        shapeList.Add(this);

    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setEnd (Point end){
        this.end = end;
    }

    public void setStart(Point start){
        this.start = start;
    }
}
