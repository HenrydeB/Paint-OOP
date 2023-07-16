package view.gui;

import model.Lists.GlobalShapeLists;
import model.Shapes.Shape;
import view.Strategy.StrategyFactory;
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
        if(globalShapeList != null){
            List<Shape> shapeList = globalShapeList.getList();
            if (shapeList != null) {
                for(Shape shape : shapeList) {
                    StrategyFactory strategy = new StrategyFactory(graphics2d);
                    strategy.strategize(shape);
                }
            }
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}


