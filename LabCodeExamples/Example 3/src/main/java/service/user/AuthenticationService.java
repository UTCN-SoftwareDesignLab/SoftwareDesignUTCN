package service.user;

import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;

/**
 * Created by Alex on 11/03/2017.
 */
public interface AuthenticationService {

    Notification<Boolean> register(String username, String password);

    Notification<User> login(String username, String password) throws AuthenticationException;

    boolean logout(User user);

}
