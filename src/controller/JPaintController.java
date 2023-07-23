package controller;

import model.*;
import model.interfaces.IApplicationState;
import model.interfaces.IMouseAction;
import model.persistence.ApplicationState;
import view.EventName;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;

    private PaintCanvas canvas;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, PaintCanvas cnv) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        setupEvents();
        canvas = cnv;
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, applicationState::setActiveShape);
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, applicationState::setActivePrimaryColor);
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, applicationState::setActiveSecondaryColor);
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, applicationState::setActiveShadingType);
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, applicationState::setActiveStartAndEndPointMode);
        uiModule.addEvent(EventName.UNDO, this::undo);
        uiModule.addEvent(EventName.REDO, this::redo);
        uiModule.addEvent(EventName.COPY, this::copy);
        uiModule.addEvent(EventName.PASTE, this::paste);
        uiModule.addEvent(EventName.DELETE, this::delete);
        uiModule.addEvent(EventName.GROUP, this::group);
        uiModule.addEvent(EventName.UNGROUP, this::ungroup);
    }

    private void undo() {
        IMouseAction action = null;
        switch (applicationState.getActiveMouseMode()){
            case DRAW -> {
                action = new DrawAction((ApplicationState)applicationState, null, null, true, false);
            }
            case MOVE -> {
                action = new MoveAction(true, false, null, null);
            }
        }
        action.run();
    }

    private void redo() {
        IMouseAction action = null;
        switch (applicationState.getActiveMouseMode()){
            case DRAW -> {
                action = new DrawAction((ApplicationState)applicationState, null, null, false, true);
            }
            case MOVE -> {
                action = new MoveAction(false, true, null, null);
            }
        }
        action.run();
    }

    private void copy() {
    }

    private void paste() {
    }

    private void delete() {
    }

    private void group() {
    }

    private void ungroup() {
    }
}
