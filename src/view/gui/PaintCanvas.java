package view.gui;

import model.Shape;
import model.GlobalShapeList;
import java.util.*;
import model.Point;
import view.ClickHandler;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class PaintCanvas extends JComponent {
    List<Shape> shapes;
    GlobalShapeList globalShapeList;
public PaintCanvas(List<Shape> shapeList) {
    shapes = shapeList;
    globalShapeList = GlobalShapeList.getInstance();
}
    @Override
    public void paint(Graphics g) {

        Graphics2D graphics2d = (Graphics2D)g;
        GlobalShapeList instance = GlobalShapeList.getInstance();
        List<Shape> shapeList = instance.getList();
        if (shapeList != null) {
            for(Shape shape : shapeList) {
                graphics2d.setColor(Color.GREEN);
                switch (shape.type.toLowerCase()) {
                    case "rectangle":
                        graphics2d.fillRect(shape.minX, shape.minY, shape.width, shape.height);

                }
            }
        }
    }
}

