package service.user;

import model.User;

/**
 * Created by Alex on 11/03/2017.
 */
public interface AuthenticationService {

    boolean register(String username, String password);

    User login(String username, String password);

    boolean logout(User user);

}
