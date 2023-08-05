package model.Actions;

import model.interfaces.IMouseAction;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

public class RedoAction implements IMouseAction {
    ApplicationState appState;
    public RedoAction(ApplicationState state){
        appState = state;
    }
    @Override
    public void run() {
        IMouseAction action = null;
        IUndoable recent = CommandHistory.peek();

        if(recent instanceof DrawAction || recent instanceof CreateShape){
            action = new DrawAction(appState, null, null, false, true);
        } else if(recent instanceof MoveAction){
            action = new MoveAction(false, true, null, null);
        } else if(recent instanceof PasteAction){
            action = new PasteAction(appState, false, true);
        } else if(recent instanceof DeleteAction){
            action = new DeleteAction(false, true);
        }
        action.run();
    }
}
