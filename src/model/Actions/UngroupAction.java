package model.Actions;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Shapes.Group;
import model.Shapes.Shape;
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

    public UngroupAction(ApplicationState appState, boolean undo, boolean redo){
        state = appState;
        isUndo = undo;
        isRedo = redo;
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
        if(!(CommandHistory.emptyRedo()) && !(CommandHistory.peekRedo() instanceof GroupAction))
            CommandHistory.add(this);

        List<IShape> selectedShapes = selected.getSelectedShapes();
        List<IShape> children = new ArrayList<>();
        Group group = null;
        for(IShape shape : selectedShapes){
            if(shape instanceof Group){
                 group = (Group)shape;
                if(!group.isGrouped()){
                    children = group.getGroupList();
                    break;
                }
            }
        }
        selected.clearList(selectedShapes);
        selected.addSetToList(children, selectedShapes);
        List<IShape> outlinedShapes = new ArrayList<>();
        for(IShape shape : selectedShapes){
            outlinedShapes.add(selected.OutlineShapeFactory(state, (Shape)shape)); //fix
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
