package model;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Shapes.Shape;
import model.interfaces.IMouseAction;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.util.ArrayList;
import java.util.List;

public class PasteAction implements IMouseAction, IUndoable {

ApplicationState state;
    public PasteAction (ApplicationState appState){
        state = appState;
    }

    @Override
    public void run() {
        ShapeActions instance = ShapeActions.getInstance();
        List<Shape> clipboard = instance.getClipboard();
        List<IShape> toPaste = new ArrayList<>();
        for(Shape shape : clipboard){
            Point start = new Point();
            Point end = new Point();
            start.x = shape.start.get('x') + 10;
            start.y = shape.start.get('y') + 10;

            end.x = shape.end.get('x') + 10;
            end.y = shape.end.get('y') + 10;
            CopyShape copy = new CopyShape();
            IShape duplicate = copy.Copy(state, shape, start, end);
            toPaste.add(duplicate);
        }
        GlobalShapeLists.getInstance().AddSetToList(toPaste);
    }
}
