package view;

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
    PaintCanvas canvas = new PaintCanvas();

    public ClickHandler(PaintCanvas pc){
        canvas = pc;
    }


    @Override
    public void mousePressed(MouseEvent e){
        start.x = e.getX();
        start.y = e.getY();
        System.out.println("start x = " + start.x);
        System.out.println("start y = " + start.y);
    }

    @Override
    public void mouseReleased(MouseEvent e){
        end.x = e.getX();
        end.y = e.getY();
        System.out.println("end x = " + end.x);
        System.out.println("end y = " + end.y);

        width = calcSide(start.x, end.x);
        height = calcSide(start.y, end.y);

        int xCoord = Math.min(start.x, end.x);
        int yCoord = Math.min(start.y, end.y);


        canvas.getGraphics().fillRect(xCoord, yCoord, width, height);
    }

    //Might want to change where this is for the sake of proper OOP, but this is an effect that happens
    //over the click action
    private int calcSide(int cor1, int cor2){
        //regardless of where the first click is, I imagine this will work
        return Math.max(cor1, cor2) - Math.min(cor1, cor2);
    }

}
