package view;

import model.Actions.MouseActionStrategy;
import model.Point;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseAdapter {

    public Point start = new Point();
    public Point end = new Point();
    PaintCanvas canvas;
    ApplicationState appState;

    public ClickHandler(PaintCanvas pc, ApplicationState as){
        canvas = pc;
        appState = as;
    }

    @Override
    public void mousePressed(MouseEvent e){
        start.x = e.getX();
        start.y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e){
        end.x = e.getX();
        end.y = e.getY();
        MouseActionStrategy action = new MouseActionStrategy(appState, start, end);
        appState.getActiveMouseMode();
        action.strategize();
    }
}
