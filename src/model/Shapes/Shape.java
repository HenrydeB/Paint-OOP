package model.Shapes;

import model.Actions.CopyShape;
import model.Point;
import model.interfaces.IShape;
import model.persistence.ApplicationState;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

public class Shape implements IShape {

   public ShapeType type;
   public int width;
   public int height;

   public boolean isSelected;
   public boolean isCopy;
   public int minX;
   public int minY;

   public HashMap<Character, Integer> start = new HashMap<>();

   public HashMap<Character, Integer> end = new HashMap<>();
   public ShapeColor primary;
   ShapeColor secondary;
   public ShapeShadingType shadingType;

    public Shape(ApplicationState appState ,Point initial, Point last, boolean selected){
     this.type = appState.getActiveShapeType(); //might throw an error because we need to import java.awt.ShapeType
     this.primary = appState.getActivePrimaryColor();
     this.secondary = appState.getActiveSecondaryColor();
     this.shadingType = appState.getActiveShapeShadingType();
     this.start.put('x', initial.x);
     this.start.put('y', initial.y);
     this.end.put('x', last.x);
     this.end.put('y', last.y);
     this.isSelected = selected;
    }
    @Override
    public void create() {
        this.width = calcSide(this.start.get('x'), this.end.get('x'));
        this.height = calcSide(this.start.get('y'), this.end.get('y'));
        this.minX = Math.min(this.start.get('x'), this.end.get('x'));
        this.minY = Math.min(this.start.get('y'), this.end.get('y'));
    }

    @Override
    public void cloneShape(Shape shape){
        this.minX = shape.minX;
        this.minY = shape.minY;
        this.width = shape.width;
        this.height = shape.height;
        this.type = shape.type;
        this.primary = shape.primary;
        this.secondary = shape.secondary;
        this.shadingType = shape.shadingType;
    }

    @Override
    public void copyStyles(Shape shape){
        this.isCopy = true;
        this.type = shape.type;
        this.primary = shape.primary;
        this.secondary = shape.secondary;
        this.shadingType = shape.shadingType;
    }

    @Override
    public int calcSide(int cor1, int cor2){
         return Math.max(cor1, cor2) - Math.min(cor1, cor2);
     }

    @Override
    public boolean isCopy(){
        if(this.isCopy){
            return true;
        }
        return false;
    }
    @Override
    public boolean isSelected(){
        if(this.isSelected){
            return true;
        }
        return false;
    }

    @Override
    public void setSelected(){
        if(this.isSelected)
            this.isSelected = false;
    }
    @Override
    public ShapeShadingType getShadingType(){
        return this.shadingType;
    }

     @Override
     public Color getPrimary(){
        return this.primary.getColor();
    }

    @Override
    public Color getSecondary(){
        return this.secondary.getColor();
    }

    @Override
    public ShapeType getType(){
        return this.type;
    }

    @Override
    public int getMinX(){
        return this.minX;
    }
    @Override
    public int getMinY(){
        return this.minY;
    }

    @Override
    public int getMaxX(){
        return (start.get('x') > end.get('x')) ? start.get('x') : end.get('x');
    }

    @Override
    public int getMaxY(){
        return (start.get('y') > end.get('y')) ? start.get('y') : end.get('y');
    }

    @Override
    public void Move(int deltX, int deltY){
        this.minX = this.minX + deltX;
        this.minY = this.minY + deltY;
    }

    @Override
    public void Paste(List<IShape> toPaste, ApplicationState state){
        Point nStart = new Point();
        Point nEnd = new Point();

        nStart.x = this.start.get('x') + 100;
        nStart.y = this.start.get('y') + 100;

        nEnd.x = this.end.get('x') + 100;
        nEnd.y = this.end.get('y') + 100;

        CopyShape copy = new CopyShape();
        IShape duplicate = copy.Copy(state, this, nStart, nEnd);
        toPaste.add(duplicate);
    }

    @Override
    public IShape Outline(ApplicationState state){
        IShape outline = null;
        Point oStart = new Point();
        oStart.x = this.start.get('x');
        oStart.y = this.start.get('y');
        Point oEnd = new Point();
        oEnd.x = this.end.get('x');
        oEnd.y = this.end.get('y');
        switch(this.type){
            case RECTANGLE -> {
                outline = ShapeFactory.createRectangle(state, oStart, oEnd, true);
                outline.cloneShape(this);
            }
            case TRIANGLE -> {
                outline = ShapeFactory.createTriangle(state, oStart, oEnd, true);
                outline.cloneShape(this);
            }
            case ELLIPSE -> {
                outline = ShapeFactory.createEllipse(state, oStart, oEnd, true);
                outline.cloneShape(this);
            }
        }
        return outline;
    }

}
