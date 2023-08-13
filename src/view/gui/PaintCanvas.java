package view.gui;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Shapes.Group;
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
            List<IShape> shapeList = globalShapeList.getMainList();
            if (shapeList != null) {
                for(IShape shape : shapeList) {
                    designer.draw(shape, graphics2d);
                }
            }
            List<IShape> selected = ShapeActions.getInstance().getSelectedShapes();
            if(selected != null){
                for(IShape shape : selected){
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


