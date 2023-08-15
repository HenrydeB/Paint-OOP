package model.Actions;

import model.Shapes.Group;
import model.interfaces.IMouseAction;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.util.EmptyStackException;

public class UndoAction implements IMouseAction {
    //what if we implemented this as a proxy??? maybe not

    ApplicationState state;
    public UndoAction(ApplicationState applicationState){
        state = applicationState;
    }
    @Override
    public void run() {
        try{
            IMouseAction action = null;
            IUndoable recent = CommandHistory.peekUndo();

            if(recent instanceof MoveAction){
                action = new MoveAction(true, false, null, null);
            } else if(recent instanceof CreateShape){
                action = new DrawAction(state, null, null, true, false);
            } else if(recent instanceof PasteAction){
                action = new PasteAction(state, true, false);
            } else if(recent instanceof DeleteAction){
                action = new DeleteAction(true, false);
            } else if( recent instanceof GroupAction){
                action = new GroupAction(state, true, false, false);
            } else if( recent instanceof UngroupAction){
                action = new UngroupAction(state, true, false, false);
            }
            action.run();
        } catch(EmptyStackException ex){
            ex.printStackTrace();
        }

    }
}
