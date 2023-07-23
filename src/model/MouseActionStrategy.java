package model;

import model.interfaces.IMouseAction;
import model.persistence.ApplicationState;

public class MouseActionStrategy {
    ApplicationState state;
    Point start;
    Point end;
    public MouseActionStrategy(ApplicationState applicationState, Point s, Point e){
        state = applicationState;
        start = s;
        end = e;
    }

    public void strategize() {
        //MouseActions actions = new MouseActions(state);
        IMouseAction action = null;
        switch(state.getActiveMouseMode()){
            case DRAW -> {
                action = new DrawAction(state, start, end, false, false);
            }
            case MOVE -> {
                //actions.moveShapes(start, end);
                action = new MoveAction(false, false, start, end);
            }
            case SELECT -> {
               // actions.selectShapes(start, end);
                action = new SelectAction(start, end, state);
            }
        }
        action.run();
    }
}
