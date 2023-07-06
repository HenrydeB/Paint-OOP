package model;

import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.gui.PaintCanvas;

public class Shape implements IShape {

   public String type;
   public int width;
    public int height;

    public int minX;

    public int minY;

    public Point start;
    public Point end;

    public Shape(String reqType, Point initial, Point last){

        type = reqType; //this may later come from the ApplicationState
        start = initial;
        end = last;
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

        width = calcSide(this.start.x, this.end.x);
        height = calcSide(this.start.y, this.end.y);
        minX = Math.min(start.x, end.x);
        minY = Math.min(start.y, end.y);
        //We need to create the list and add our shape to the list maybe here?
        //maybe we can add it right after we call the function
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
