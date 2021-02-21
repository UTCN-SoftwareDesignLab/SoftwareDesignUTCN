import controller.SampleController;
import model.SampleModel;
import view.SampleView;

/**
 * Created by Alex on 27/03/2017.
 */
public class Launcher {
    public static void main(String[] args) {
        new SampleController(new SampleView(), new SampleModel());
    }
}
