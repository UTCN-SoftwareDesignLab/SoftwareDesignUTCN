package org.example.service.authentication;

import org.example.database.DatabaseConnectionFactory;
import org.example.database.DbConnection;
import org.example.repository.security.RoleRepository;
import org.example.repository.security.RoleRepositorySQL;
import org.example.repository.security.UserRepository;
import org.example.repository.security.UserRepositorySQL;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;

import static org.example.database.SupportedDatabase.MYSQL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthenticationServiceTest {

  private AuthenticationService authenticationService;

  @BeforeAll
  public void setup() {
    DbConnection connectionWrapper = DatabaseConnectionFactory.getConnectionWrapper(MYSQL, true);
    Connection connection = connectionWrapper.getConnection();

    UserRepository userRepositorySQL = new UserRepositorySQL(connection);
    RoleRepository roleRepositorySQL = new RoleRepositorySQL(connection);
    authenticationService = new AuthenticationService(userRepositorySQL, roleRepositorySQL);
  }


  @Test
  void register() {
  }

  @Test
  void login() {
  }
}