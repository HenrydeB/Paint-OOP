package model.Actions;

import model.interfaces.IMouseAction;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.util.EmptyStackException;

public class RedoAction implements IMouseAction {
    ApplicationState appState;
    public RedoAction(ApplicationState state){
        appState = state;
    }
    @Override
    public void run() {
        try{
            IMouseAction action = null;
            IUndoable recent = CommandHistory.peekRedo();
            //TODO: now that we are peeking from Redo, we will need to double check other undoes and redoes
            if(recent instanceof DrawAction || recent instanceof CreateShape){
                action = new DrawAction(appState, null, null, false, true);
            } else if(recent instanceof MoveAction){
                action = new MoveAction(false, true, null, null);
            } else if(recent instanceof PasteAction){
                action = new PasteAction(appState, false, true);
            } else if(recent instanceof DeleteAction){
                action = new DeleteAction(false, true);
            } else if (recent instanceof GroupAction){
                action = new GroupAction(appState, false, true, false);
            } else if(recent instanceof UngroupAction){
                action = new UngroupAction(appState, false, true, false);
            }

            action.run();
        } catch (EmptyStackException ex){
            System.out.println("Redo Stack is Empty");
            ex.printStackTrace();
        }

    }
}
