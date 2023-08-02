package model;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Shapes.Shape;
import model.interfaces.IMouseAction;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.util.List;

public class DeleteAction implements IMouseAction, IUndoable {
    @Override
    public void run() {
        GlobalShapeLists instance = GlobalShapeLists.getInstance();
        List<Shape> selected = ShapeActions.getInstance().getSelectedShapes();
        List<IShape> allShapes = instance.getList();
        for(Shape shape : selected){
            allShapes.remove(shape);
        }
        ShapeActions.getInstance().clearSelected();
        instance.notifyObservers();
    }
}
