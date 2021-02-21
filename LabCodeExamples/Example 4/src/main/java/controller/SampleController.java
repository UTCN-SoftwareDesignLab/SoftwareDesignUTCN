package controller;

import command.ChangeFieldCommand;
import model.SampleModel;
import view.SampleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Alex on 29/03/2017.
 */
public class SampleController implements Observer {

    private final CommandStack commandStack;
    private final SampleView view;
    private final SampleModel model;

    public SampleController(SampleView view, SampleModel model) {
        this.commandStack = new CommandStack();
        this.view = view;
        this.model = model;
        view.addUpdateButtonListener(new UpdateButtonListener());
        view.addUndoButtonListener(new UndoButtonListener());
        view.addRedoButtonListener(new RedoButtonListener());
        model.addObserver(this);
        commandStack.push(new ChangeFieldCommand("", model));
    }

    public void update(Observable o, Object arg) {
        view.updateFieldA(model.getField());
        view.updateFieldB(model.getField());
    }

    private class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ChangeFieldCommand changeFieldCommand = new ChangeFieldCommand(view.getFieldAText(), model);
            commandStack.push(changeFieldCommand);
            changeFieldCommand.execute();
        }
    }

    private class UndoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            commandStack.undo().execute();
        }
    }

    private class RedoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            commandStack.redo().execute();
        }
    }
}
