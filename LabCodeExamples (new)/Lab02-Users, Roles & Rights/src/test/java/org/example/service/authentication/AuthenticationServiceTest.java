package org.example.service.authentication;

import org.example.database.DatabaseConnectionFactory;
import org.example.database.DbConnection;
import org.example.repository.security.RoleRepository;
import org.example.repository.security.RoleRepositorySQL;
import org.example.repository.security.UserRepository;
import org.example.repository.security.UserRepositorySQL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;

import static org.example.database.SupportedDatabase.MYSQL;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthenticationServiceTest {

  private AuthenticationService authenticationService;
  private UserRepository userRepository;

  @BeforeEach
  public void cleanup() {
    userRepository.deleteAll();
  }

  @BeforeAll
  public void setup() {
    DbConnection connectionWrapper = DatabaseConnectionFactory.getConnectionWrapper(MYSQL, true);
    Connection connection = connectionWrapper.getConnection();

    RoleRepository roleRepositorySQL = new RoleRepositorySQL(connection);
    userRepository = new UserRepositorySQL(connection, roleRepositorySQL);
    authenticationService = new AuthenticationService(userRepository, roleRepositorySQL);
  }


  @Test
  void register() {
    assertTrue(authenticationService.register("blabla", "blablabla"));
    assertFalse(authenticationService.register("blabla", "blablabla"));
  }

  @Test
  void login() {
  }
}