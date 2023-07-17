package main;

import controller.JPaintController;
import model.Lists.GlobalShapeLists;
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
        GlobalShapeLists instance = GlobalShapeLists.getInstance();
        instance.addObserver(paintCanvas);
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(uiModule);

        paintCanvas.addMouseListener(new ClickHandler(paintCanvas, appState));

        new JPaintController(uiModule, appState, paintCanvas);
    }
}
