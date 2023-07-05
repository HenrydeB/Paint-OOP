package view;

import model.CreateShape;
import model.GlobalShapeList;
import model.Shape;
import model.Point;
import view.gui.PaintCanvas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {

    public Point start = new Point();
    public Point end = new Point();
    int width;
    int height;
    PaintCanvas canvas;

    public ClickHandler(PaintCanvas pc){
        canvas = pc;
    }

    @Override
    public void mousePressed(MouseEvent e){
        start.x = e.getX();
        start.y = e.getY();

        //CreateShape cmd = (CreateShape)CreateShape.run("rectangle", start);
        CreateShape cs = new CreateShape();
        cs.run("rectangle", start);
        canvas.repaint();
        System.out.println("canvas repainted");
    }

    @Override
    public void mouseReleased(MouseEvent e){
        end.x = e.getX();
        end.y = e.getY();

        int xCoord = Math.min(this.start.x, this.end.x);
        int yCoord = Math.min(this.start.y, this.end.y);

        width = calcSide(this.start.x, this.end.x);
        height = calcSide(this.start.y, this.end.y);

        finishRecentShape();

        canvas.getGraphics().fillRect(xCoord, yCoord, width, height);

    }

    //Might want to change where this is for the sake of proper OOP, but this is an effect that happens
    //over the click action
    private int calcSide(int cor1, int cor2){
        //regardless of where the first click is, I imagine this will work
        return Math.max(cor1, cor2) - Math.min(cor1, cor2);
    }

    private void finishRecentShape(){
        Shape recent = GlobalShapeList.getInstance().Recent();
        recent.setEnd(end);
        recent.setWidth(width);
        recent.setHeight(height);
    }

}
