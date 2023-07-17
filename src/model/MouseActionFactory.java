package model;

import model.interfaces.IFactoryPattern;
import model.persistence.ApplicationState;

public class MouseActionFactory implements IFactoryPattern {
    ApplicationState state;
    Point start;
    Point end;
    public MouseActionFactory(ApplicationState applicationState, Point s, Point e){
        state = applicationState;
        start = s;
        end = e;
    }
    @Override
    public void strategize() {
        MouseActions actions = new MouseActions(state);
        switch(state.getActiveMouseMode()){
            case DRAW -> {
                CreateShape cs = new CreateShape();
                cs.addShape(state, start, end);
            }
            case MOVE -> {
                actions.moveShapes(start, end);
            }
            case SELECT -> {
                actions.selectShapes(start, end);
            }
        }
    }
}
