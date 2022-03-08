package service.user;

import database.DBConnectionFactory;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationServiceMySQLTest {

  private static AuthenticationService authenticationService;
  private static UserRepository userRepository;

  @BeforeAll
  public static void setUp() {
    Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
    RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
    userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

    authenticationService = new AuthenticationServiceMySQL(
        userRepository,
        rightsRolesRepository
    );
  }

  @BeforeEach
  public void cleanUp() {
    userRepository.removeAll();
  }


  @Test
  public void register() throws Exception {
    assertTrue(
        authenticationService.register("Test Username", "Test Password")
    );
  }

  @Test
  public void login() throws Exception {
    String username = "TEST";
    String password = "123456";
    authenticationService.register(username, password);

    User user = authenticationService.login(username, password);

    assertNotNull(user);
  }

  @Test
  public void logout() throws Exception {

  }

}
