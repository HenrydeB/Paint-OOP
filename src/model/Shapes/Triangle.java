package model.Shapes;

import model.Point;
import model.interfaces.IShape;
import model.persistence.ApplicationState;

 public class Triangle extends Shape {
//work on making this package private
   public int[] xAxis = new int[3];
   public int[] yAxis = new int[3];
    public Triangle(ApplicationState appState, Point initial, Point last, boolean isSelected) {
        super(appState, initial, last, isSelected);
        this.create();
    }
    @Override
    public void create(){ // need to adjust for ifSelected. Should be able to go based on the min value if start.x == minX, go from there
        super.create();

        this.xAxis[0] = this.start.get('x');
        this.xAxis[1] = this.end.get('x');
        this.xAxis[2] = this.start.get('x');

        this.yAxis[0] = this.start.get('y');
        this.yAxis[1] = this.end.get('y');
        this.yAxis[2] = this.end.get('y');

        if(isSelected)
            adjustSelectedEndpoints();

    }

    //can adjust numbers to make this work better
    private void adjustSelectedEndpoints(){
        if(this.minX == this.start.get('x')){
            if(this.minY == this.start.get('y')){
                this.yAxis[1] = this.yAxis[1] + 3;
                this.yAxis[2] = this.yAxis[2] + 3;
            } else if(this.minY == this.end.get('y')){
                this.xAxis[1] = this.xAxis[1] + 6;
                this.yAxis[0] = this.yAxis[0] + 6;
            }
        } else if(this.minX == this.end.get('x')){
            if(this.minY == this.start.get('y')){
                this.yAxis[1] = this.yAxis[1] +3;
                this.yAxis[2] = this.yAxis[2] +3;
                this.xAxis[0] = this.xAxis[0] +3;
                this.xAxis[2] = this.xAxis[2] +3;
            } else if(this.minY == this.end.get('y')){
                this.xAxis[0] = this.xAxis[0] +6;
                this.xAxis[2] = this.xAxis[2] + 6;
                this.xAxis[1] = this.xAxis[1] -3;
                this.yAxis[0] = this.yAxis[0] + 6;
            }
        }
    }

    @Override
     public void cloneShape(Shape shape){
        super.cloneShape(shape);
        int maxX = this.width + this.minX;
        int maxY = this.height + this.minY;
    }

}
