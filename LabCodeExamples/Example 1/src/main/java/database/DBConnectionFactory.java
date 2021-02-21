package database;

import utility.JDBConnectionWrapper;

/**
 * Created by Alex on 08/03/2017.
 */
public class DBConnectionFactory {

    public JDBConnectionWrapper getConnectionWrapper(boolean test) {
        if (test) {
            return new JDBConnectionWrapper("test_library");
        }
        return new JDBConnectionWrapper("library");
    }

}
