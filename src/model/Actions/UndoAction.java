package model.Actions;

import model.interfaces.IMouseAction;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

public class UndoAction implements IMouseAction {
    //what if we implemented this as a proxy??? maybe not

    ApplicationState state;
    public UndoAction(ApplicationState applicationState){
        state = applicationState;
    }
    @Override
    public void run() {
        IMouseAction action = null;
        IUndoable recent = CommandHistory.peek();

        if(recent instanceof MoveAction){
            action = new MoveAction(true, false, null, null);
        } else if(recent instanceof CreateShape){
            action = new DrawAction(state, null, null, true, false);
        } else if(recent instanceof PasteAction){
            action = new PasteAction(state, true, false);
        } else if(recent instanceof DeleteAction){
            action = new DeleteAction(true, false);
        }
        action.run();
    }
}
