package database;

/**
 * Created by Alex on 08/03/2017.
 */
public class DBConnectionFactory {

    public JDBConnectionWrapper getConnectionWrapper(boolean test) {
        if (test) {
            return new JDBConnectionWrapper(Constants.Schemas.TEST);
        }
        return new JDBConnectionWrapper(Constants.Schemas.PRODUCTION);
    }

}
