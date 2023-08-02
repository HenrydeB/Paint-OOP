package view.gui;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Shapes.Shape;
import model.interfaces.IShape;
import view.Strategy.ShapeDesigner;
import view.interfaces.IShapeObserver;

import javax.swing.JComponent;
import java.awt.*;
import java.util.List;

public class PaintCanvas extends JComponent implements IShapeObserver {
    GlobalShapeLists globalShapeList;
public PaintCanvas() {
    globalShapeList = GlobalShapeLists.getInstance();
}
    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D)g;
        ShapeDesigner designer = new ShapeDesigner();
        if(globalShapeList != null){
            List<IShape> shapeList = globalShapeList.getList();
            if (shapeList != null) {
                System.out.println("shapelist size " + shapeList.size());
                for(IShape shape : shapeList) {
                    designer.draw((Shape)shape, graphics2d);
                }
            }
            List<Shape> selected = ShapeActions.getInstance().getSelectedShapes();
            if(selected != null){
                for(Shape shape : selected){
                    designer.draw(shape, graphics2d);
                }
            }
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}


