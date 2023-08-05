package model.Actions;

import model.Point;
import model.interfaces.IMouseAction;
import model.persistence.ApplicationState;

public class DrawAction implements IMouseAction {
    ApplicationState state;
    Point start;
    Point end;
    boolean isUndo;
    boolean isRedo;
    public DrawAction(ApplicationState appState, Point s, Point e, boolean undo, boolean redo){
        state = appState;
        start = s;
        end = e;
        isUndo = undo;
        isRedo = redo;
    }

    @Override
    public void run() {
        CreateShape cs = new CreateShape();
        if(isUndo)
            cs.undo();
        else if(isRedo)
            cs.redoShape();
        else
            cs.addShape(state, start, end);
    }
}
