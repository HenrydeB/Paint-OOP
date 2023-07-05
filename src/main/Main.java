package main;

import controller.JPaintController;
import model.GlobalShapeList;
import model.persistence.ApplicationState;
import view.ClickHandler;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args){
        PaintCanvas paintCanvas = new PaintCanvas();

        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);

        GlobalShapeList shapeList = GlobalShapeList.getInstance();

        paintCanvas.addMouseListener(new ClickHandler(paintCanvas));

        new JPaintController(uiModule, appState);
    }
}
