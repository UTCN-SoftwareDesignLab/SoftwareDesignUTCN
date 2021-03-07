import database.Boostraper;

import java.sql.SQLException;

/**
 * Created by Alex on 18/03/2017.
 */
public class Launcher {

    public static boolean BOOTSTRAP = true;

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance(false);

        bootstrap();

        componentFactory.getLoginView().setVisible();
    }

    private static void bootstrap() {
        if (BOOTSTRAP) {
            try {
                new Boostraper().execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
