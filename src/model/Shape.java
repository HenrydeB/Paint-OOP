package model;

import model.interfaces.IShape;

public class Shape implements IShape{
    Point onClick;
    Point offClick;
    String type;
    int width;
    int height;

    public Shape(Point first, Point end, String reqType){
        onClick = first;
        offClick = end;
        type = reqType; //this may later come from the ApplicationState

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
        width = calcSide(this.onClick.x, this.offClick.x);
        height = calcSide(this.onClick.y, this.offClick.y);

        //Might not need these lines anymore?
        int xCoord = Math.min(this.onClick.x, this.offClick.x);
        int yCoord = Math.min(this.onClick.y, this.offClick.y);

        //We need to create the list and add our shape to the list maybe here?
        //maybe we can add it right after we call the function

    }
}
