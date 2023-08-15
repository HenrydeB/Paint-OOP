package model.Actions;

import java.util.*;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Point;
import model.Shapes.Group;
import model.Shapes.ShapeFactory;
import model.interfaces.IMouseAction;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;


public class GroupAction implements IMouseAction, IUndoable {

    boolean isUndo;
    boolean isRedo;
    ApplicationState state;
    boolean isSecondary;
    public GroupAction(ApplicationState appState, boolean undo, boolean redo, boolean secondaryAction){
        isUndo = undo;
        isRedo = redo;
        state = appState;
        isSecondary = secondaryAction;
    }
    @Override
    public void run() {
        if(isUndo)
            undo();
        else if(isRedo)
            redo();
        else
            group();

    }

    private void group() {
        if(!isSecondary)
            CommandHistory.add(this);

        ShapeActions selectedShapes = ShapeActions.getInstance();
        List<IShape> selected = selectedShapes.getSelectedShapes();
        int minX = 0, minY = 0, maxX = 0, maxY = 0;

        for(IShape shape : selected){
            minX = ((shape.getMinX() < minX) || (minX == 0)) ? shape.getMinX() : minX;
            minY = ((shape.getMinY() < minY) || (minY == 0))  ? shape.getMinY() : minY;
            maxX = ((shape.getMaxX() > maxX) || (maxX == 0)) ? shape.getMaxX() : maxX;
            maxY = ((shape.getMaxY() > maxY) || (maxY == 0)) ? shape.getMaxY() : maxY;
        }
        Point start = new Point();
        start.setValues(minX, minY);
        Point end = new Point();
        end.setValues(maxX, maxY);

        IShape group = ShapeFactory.createGroup(state, start, end, false, selected);
        IMouseAction action = new SelectAction(start, end, state, true);
        GlobalShapeLists instance = GlobalShapeLists.getInstance();
        List<IShape> globalShapes = instance.getMainList();
        instance.removeSetFromList(selected, globalShapes);
        instance.addToList(group, globalShapes);
        action.run();
    }

    private void undo(){
        CommandHistory.undo();
        IMouseAction undoGroup = new UngroupAction(state, false, false, true);
        undoGroup.run();
    }

    private void redo(){
        CommandHistory.redo();
        group();
    }
}
