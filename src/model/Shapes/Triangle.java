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

        if(isSelected){
           this.xAxis = adjustSelectedEndpoints(this.xAxis, minX);
           this.yAxis = adjustSelectedEndpoints(this.yAxis, minY);
        }
    }

    private int[] adjustSelectedEndpoints(int[] point, int min){
        int[] adjusted = new int[3];
        int startY = this.start.get('y');
        int endX = this.end.get('x');
        for(int i = 0; i<3; i++){
            if(point[i] == min) {
                adjusted[i] = (point[i] == startY || point[i] == endX) ? point[i] - 7 : point[i] - 5;
            }
            else {
                adjusted[i] = (point[i] == startY || point[i] == endX) ? point[i] + 10 : point[i] + 5;
            }
        }
        return adjusted;
    }

    @Override
     public void cloneShape(Shape shape){
        super.cloneShape(shape);
        int maxX = this.width + this.minX;
        int maxY = this.height + this.minY;
    }

    @Override
     public void Move(int dX, int dY){
         for(int i = 0; i<= this.xAxis.length - 1; i++){
             this.xAxis[i] = this.xAxis[i] + dX;
             this.yAxis[i] = this.yAxis[i] + dY;
         }

         this.minX = this.minX + dX;
         this.minY = this.minY + dY;

        this.start.put('x', this.xAxis[0]);
        this.start.put('y', this.yAxis[0]);
        this.end.put('x', this.xAxis[1]);
        this.end.put('y', this.yAxis[1]);

     }

}
