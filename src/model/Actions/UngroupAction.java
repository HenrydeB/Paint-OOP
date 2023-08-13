package model.Actions;

import model.Lists.GlobalShapeLists;
import model.Lists.ShapeActions;
import model.Shapes.Group;
import model.Shapes.Shape;
import model.interfaces.IMouseAction;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.util.List;

public class UngroupAction implements IMouseAction, IUndoable {
    @Override
    public void run() {
        ShapeActions instance = ShapeActions.getInstance();
        GlobalShapeLists globalInstance = GlobalShapeLists.getInstance();
        List<IShape> selected = instance.getSelectedShapes();

        /*TODO:create a function to recursively go through the group tree
        * */
        for(IShape shape : selected){
            if(shape instanceof Group){

            }
        }

    }
}
