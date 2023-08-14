package controller;

import model.Actions.*;
import model.Lists.ShapeActions;
import model.Shapes.Shape;
import model.interfaces.IApplicationState;
import model.interfaces.IMouseAction;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.EventName;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

import java.util.List;

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
        UndoAction action = new UndoAction((ApplicationState) applicationState);
        action.run();
    }

    private void redo() {
        RedoAction action = new RedoAction((ApplicationState) applicationState);
        action.run();
    }

    private void copy() {
        ShapeActions instance = ShapeActions.getInstance();
        List<IShape> selectedShapes = instance.getSelectedShapes();
        instance.clearList(instance.getClipboard());
        instance.CopyToClipboard(selectedShapes);
    }

    private void paste() {
        IMouseAction action = new PasteAction((ApplicationState) applicationState, false, false);
        action.run();
    }

    private void delete() {
        IMouseAction action = new DeleteAction(false, false);
        action.run();
    }

    private void group() {
        IMouseAction action = new GroupAction((ApplicationState) applicationState,false, false, false);
        action.run();
    }

    private void ungroup() {
        IMouseAction action = new UngroupAction((ApplicationState) applicationState, false, false, false);
        action.run();
    }
}
