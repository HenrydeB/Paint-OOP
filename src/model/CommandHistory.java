package model;

import model.interfaces.IUndoable;

import java.util.Stack;

class CommandHistory implements IUndoable{
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}

	public static boolean undo() {
		System.out.println("in Command History");
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
			redoStack.push(c);
			IUndoable.undo();
		}
		return result;
	}

	public static boolean redo() {
		System.out.println("In Command History");
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			IUndoable.redo();
		}
		return result;
	}
}
