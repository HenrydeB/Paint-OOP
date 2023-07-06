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
    PaintCanvas canvas;

    public ClickHandler(PaintCanvas pc){
        canvas = pc;
    }

    @Override
    public void mousePressed(MouseEvent e){
        start.x = e.getX();
        start.y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e){
        end.x = e.getX();
        end.y = e.getY();
        CreateShape cs = new CreateShape();
        cs.run("rectangle", start, end,canvas, "add");
    }
}
