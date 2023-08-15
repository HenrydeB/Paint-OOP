package model.Actions;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Shapes.Group;
import model.interfaces.IMouseAction;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;

import java.util.ArrayList;
import java.util.List;

public class UngroupAction implements IMouseAction, IUndoable {

    ApplicationState state;
    boolean isUndo;
    boolean isRedo;
    boolean isSecondaryAction;

    public UngroupAction(ApplicationState appState, boolean undo, boolean redo, boolean secondaryAction){
        state = appState;
        isUndo = undo;
        isRedo = redo;
        isSecondaryAction = secondaryAction;
    }
    @Override
    public void run() {
        ShapeActions instance = ShapeActions.getInstance();
        GlobalShapeLists globalInstance = GlobalShapeLists.getInstance();

        if(isUndo)
            undo();
        else if(isRedo)
            redo(instance, globalInstance);
        else
            ungroup(instance, globalInstance);
    }

    private void ungroup(ShapeActions selected, GlobalShapeLists globalList){

        if(!isSecondaryAction)
            CommandHistory.add(this);

        List<IShape> selectedShapes = selected.getSelectedShapes();
        List<IShape> children = new ArrayList<>();
        IShape group = null;
        for(IShape shape : selectedShapes){
            children = shape.UnGroup();
            if(children != null){
                group = shape;
                break;
                //we break because we assume the ungroup action goes on
                //the one group we just made
                //IE there cannot be more than one "root" node to the group tree
            }

        }
        selected.clearList(selectedShapes);
        selected.addSetToList(children, selectedShapes);
        List<IShape> outlinedShapes = new ArrayList<>();
        for(IShape shape : selectedShapes){
            outlinedShapes.add(shape.Outline(state));
        }
        selected.addSetToList(outlinedShapes, selectedShapes);
        globalList.RemoveObject(group, globalList.getMainList());
        globalList.addSetToList(children, globalList.getMainList());
    }

    private void undo(){
        CommandHistory.undo();
        IMouseAction group = new GroupAction(state, false, false, true);
        group.run();
    }

    private void redo(ShapeActions selected, GlobalShapeLists globalLists){
        CommandHistory.redo();
        ungroup(selected, globalLists);
    }
}
