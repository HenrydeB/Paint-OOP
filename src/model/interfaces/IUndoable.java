package model.interfaces;

public interface IUndoable {
    static boolean undo(){ return false;};

    static boolean redo() {return false;};
}
