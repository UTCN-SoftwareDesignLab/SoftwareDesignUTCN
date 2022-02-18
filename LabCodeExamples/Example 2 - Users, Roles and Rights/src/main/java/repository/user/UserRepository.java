package repository.user;

import model.User;

import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 */
public interface UserRepository {

  List<User> findAll();

  User findByUsernameAndPassword(String username, String password);

  boolean save(User user);

  void removeAll();

}
