package model.Shapes;

import model.Point;
import model.interfaces.IShape;
import model.persistence.ApplicationState;

 public class Triangle extends Shape {
//work on making this package private
   public int[] xAxis = new int[3];
   public int[] yAxis = new int[3];
    public Triangle(ApplicationState appState, Point initial, Point last) {
        super(appState, initial, last);
        this.create();
    }
    @Override
    public void create(){
        this.xAxis[0] = this.start.x;
        this.xAxis[1] = this.end.x;
        this.xAxis[2] = this.start.x;

        this.yAxis[0] = this.start.y;
        this.yAxis[1] = this.end.y;
        this.yAxis[2] = this.end.y;

        //added for collision detection alg
        super.create();
    }
}
