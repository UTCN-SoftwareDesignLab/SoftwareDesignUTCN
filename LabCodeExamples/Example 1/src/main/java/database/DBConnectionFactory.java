package database;

import utility.JDBConnectionWrapper;

/**
 * Created by Alex on 08/03/2017.
 */
public class DBConnectionFactory {

    private static final String TEST_SCHEMA = "test_library";
    private static final String SCHEMA = "library";

    public static JDBConnectionWrapper getConnectionWrapper(boolean test) {
        if (test) {
            return new JDBConnectionWrapper(TEST_SCHEMA);
        }
        return new JDBConnectionWrapper(SCHEMA);
    }

}
