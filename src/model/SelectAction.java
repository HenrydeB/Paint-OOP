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

import java.util.ArrayList;
import java.util.List;

public class SelectAction implements IMouseAction, IUndoable {
    Point initial;
    Point second;
    ApplicationState state;
    ShapeActions instance;
    GlobalShapeLists globalInstance;
    public SelectAction(Point s, Point e, ApplicationState appState) {
        initial = s;
        second = e;
        state = appState;
        instance = ShapeActions.getInstance();
        globalInstance = GlobalShapeLists.getInstance();
    }


    @Override
    public void run() {
        CommandHistory.add(this);
        if(instance.getSelectedShapes().size() > 0){
            instance.clearSelected();
            globalInstance.notifyObservers();
        }
        IShape boundBox = ShapeFactory.createRectangle(this.state, this.initial, this.second, false);
        List<IShape> createdShapes = globalInstance.getList();
        for(IShape shape : createdShapes){
            if(doesCollide((Shape) boundBox, (Shape)shape)){
                instance.addSelected((Shape)shape);
            }
        }

        List<Shape> selectedShapes = instance.getSelectedShapes();
        List<Shape> outlinedShapes = new ArrayList<>();
        for(Shape shape : selectedShapes){
            outlinedShapes.add(instance.OutlineShapeFactory(state, shape));
        }
        instance.addListToSelected(outlinedShapes);

        globalInstance.notifyObservers();
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
