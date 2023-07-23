package model;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Point;
import model.Shapes.Rectangle;
import model.Shapes.Shape;
import model.Shapes.ShapeFactory;
import model.interfaces.IMouseAction;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.util.List;

public class SelectAction implements IMouseAction, IUndoable {
    Point start;
    Point end;
    ApplicationState state;
    ShapeActions instance;
    public SelectAction(Point s, Point e, ApplicationState appState) {
        start = s;
        end = e;
        state = appState;
        instance = ShapeActions.getInstance();
    }


    @Override
    public void run() {
        CommandHistory.add(this);
        if(instance.getSelectedShapes().size() > 0){
            instance.clearSelected();
        }
        IShape boundBox = ShapeFactory.createRectangle(state, start, end);
        List<IShape> createdShapes = GlobalShapeLists.getInstance().getList();
        for(IShape shape : createdShapes){
            if(doesCollide((Shape) boundBox, (Shape)shape)){
                instance.addSelected((Shape)shape);
            }
        }
        System.out.println("Selected shapes count = " + instance.getSelectedShapes().size());
    }

    private boolean doesCollide(Shape bound, Shape shape){
        if
        (
                (bound.minX < shape.minX + shape.width) &&
                        (bound.minX + bound.width > shape.minX) &&
                        (bound.minY < shape.minY + shape.height) &&
                        (bound.minY + bound.height > shape.minY)
        )
        {
            return true;
        }
        return false;
    }
}
