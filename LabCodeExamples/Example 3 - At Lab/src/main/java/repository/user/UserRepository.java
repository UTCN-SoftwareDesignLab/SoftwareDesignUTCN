package repository.user;

import model.User;
import model.validation.Notification;

public interface UserRepository {
    Notification<User> findByUsernameAndPassword(String username, String encodePassword);

    Boolean save(User user);
}
