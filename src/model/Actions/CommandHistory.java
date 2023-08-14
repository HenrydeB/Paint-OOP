package model.Actions;

import model.interfaces.IUndoable;

import java.util.Stack;

class CommandHistory implements IUndoable{
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}

	public static void undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
			redoStack.push(c);
			IUndoable.undo();
		}
		//return result;
	}

	public static void redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			IUndoable.redo();
		}
		//return result;
	}

	public static IUndoable peekUndo(){
		return undoStack.peek();
	}

	public static IUndoable peekRedo(){
		return redoStack.peek();
	}

	public static boolean emptyRedo(){
		return redoStack.isEmpty();
	}
}
