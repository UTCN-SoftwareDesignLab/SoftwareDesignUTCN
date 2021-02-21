package controller;

import command.Command;

import java.util.Stack;

/**
 * Created by Alex on 29/03/2017.
 */
public class CommandStack {

    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    public CommandStack() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void push(Command command) {
        redoStack.clear();
        undoStack.push(command);
    }

    public Command undo() {
        if (undoStack.size() > 1) {
            redoStack.push(undoStack.pop());
        }
        return undoStack.peek();
    }

    public Command redo() {
        if (redoStack.size() > 0) {
            undoStack.push(redoStack.pop());
        }
        return undoStack.peek();
    }
}
