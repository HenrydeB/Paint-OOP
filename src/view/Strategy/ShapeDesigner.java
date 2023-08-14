package view.Strategy;

import model.Shapes.Group;
import model.Shapes.Shape;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.IShapeDesign;

import java.awt.Graphics2D;
import java.util.List;

public class ShapeDesigner {

    public void draw(IShape shape, Graphics2D graphics) {
        IShapeDesign designer = null;

        if(shape instanceof Group){
            designer = handleGroup(shape, graphics);
        } else if(shape.isSelected() == false){
            switch(shape.getShadingType()){
                case OUTLINE -> {
                    designer = new OutlineStrategy();
                }
                case FILLED_IN -> {
                    designer = new FillStrategy();
                }
                case OUTLINE_AND_FILLED_IN -> {
                    designer = new OutFillStrategy();
                }
            }
        } else {
            designer = new SelectedStrategy();
        }

        if(designer != null)
            designer.design(shape, graphics);
    }

    private IShapeDesign handleGroup(IShape shape, Graphics2D graphics) {
        Group group = (Group)shape;
        List<IShape> set = group.getGroupList();

        for(IShape groupedShape : set){
            draw(groupedShape, graphics);
        }

        if(!group.isGrouped())
            return new OutlineStrategy();

        return null;
    }
}
