package repository.user;

import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.security.RightsRolesRepository;

import java.sql.*;

import static database.Constants.Tables.USER;

public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;

    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }


    @Override
    public Notification<User> findByUsernameAndPassword(String username, String encodePassword) {
        Notification<User> notification = new Notification<>();

        try {

            Statement statement = connection.createStatement();
            String sql = "Select * from `" + USER + "` where `username` = \'" + username + "\' and `password`=\'" + encodePassword + "\'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(username)
                        .setPassword(encodePassword)
                        .setRoles(rightsRolesRepository.findRolesForUser(resultSet.getLong("id")))
                        .build();
                notification.setResult(user);
                return notification;
            } else {
                notification.addError("Invalid username and/or password!");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return notification;
    }

    @Override
    public Boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
