package model.Actions;

import model.Actions.CommandHistory;
import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Point;
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
    boolean secondaryAction;

    public SelectAction(Point s, Point e, ApplicationState appState, boolean secondary) {
        initial = s;
        second = e;
        state = appState;
        instance = ShapeActions.getInstance();
        globalInstance = GlobalShapeLists.getInstance();
        secondaryAction = secondary;
    }


    @Override
    public void run() {
        if(!secondaryAction)
            CommandHistory.add(this);

        if(instance.getSelectedShapes().size() > 0){
            instance.clearList(instance.getSelectedShapes());
            globalInstance.notifyObservers();
        }
        IShape boundBox = ShapeFactory.createRectangle(this.state, this.initial, this.second, false);
        List<IShape> createdShapes = globalInstance.getMainList();
        for(IShape shape : createdShapes){
            if(doesCollide((Shape) boundBox, (Shape)shape)){
                instance.addToList(shape, instance.getSelectedShapes());
            }
        }

        List<IShape> selectedShapes = instance.getSelectedShapes();
        List<IShape> outlinedShapes = new ArrayList<>();
        for(IShape shape : selectedShapes){
            outlinedShapes.add(shape.Outline(state));
        }
        instance.addSetToList(outlinedShapes, instance.getSelectedShapes());

        globalInstance.notifyObservers();
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
