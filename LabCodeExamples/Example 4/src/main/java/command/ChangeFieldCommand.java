package command;

import model.SampleModel;

/**
 * Created by Alex on 29/03/2017.
 */
public class ChangeFieldCommand implements Command {

    private final String text;
    private final SampleModel model;

    public ChangeFieldCommand(String text, SampleModel model) {
        this.text = text;
        this.model = model;
    }

    public void execute() {
        model.setField(text);
    }
}
