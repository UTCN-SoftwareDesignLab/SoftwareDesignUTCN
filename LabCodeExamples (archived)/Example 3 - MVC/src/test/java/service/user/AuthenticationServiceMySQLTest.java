package service.user;

import launcher.ComponentFactory;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.user.UserRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationServiceMySQLTest {

  public static final String TEST_USERNAME = "test@username.com";
  public static final String TEST_PASSWORD = "TestPassword1@";
  private static AuthenticationService authenticationService;
  private static UserRepository userRepository;

  @BeforeAll
  public static void setUp() {
    ComponentFactory componentFactory = ComponentFactory.instance(true);
    userRepository = componentFactory.getUserRepository();
    authenticationService = componentFactory.getAuthenticationService();
  }

  @BeforeEach
  public void cleanUp() {
    userRepository.removeAll();
  }


  @Test
  public void register() {
    assertTrue(
        authenticationService.register(TEST_USERNAME, TEST_PASSWORD).getResult()
    );
  }

  @Test
  public void login() throws Exception {
    authenticationService.register(TEST_USERNAME, TEST_PASSWORD);
    User user = authenticationService.login(TEST_USERNAME, TEST_PASSWORD).getResult();
    assertNotNull(user);
  }

  @Test
  public void logout() {

  }

}
