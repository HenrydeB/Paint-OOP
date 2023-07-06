package main;

import controller.JPaintController;
import model.GlobalShapeList;
import model.Shape;
import model.persistence.ApplicationState;
import view.ClickHandler;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        GlobalShapeList instance = GlobalShapeList.getInstance();
        List<Shape> shapeList = instance.getList();
        PaintCanvas paintCanvas = new PaintCanvas(shapeList);
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);


        paintCanvas.addMouseListener(new ClickHandler(paintCanvas));

        new JPaintController(uiModule, appState, paintCanvas);
    }
}
