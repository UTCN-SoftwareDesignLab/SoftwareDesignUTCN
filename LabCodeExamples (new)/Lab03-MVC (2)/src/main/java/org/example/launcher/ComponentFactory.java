package org.example.launcher;

import org.example.controller.LoginController;
import org.example.database.DatabaseConnectionFactory;
import org.example.database.SupportedDatabase;
import org.example.repository.book.BookRepository;
import org.example.repository.book.BookRepositorySQL;
import org.example.repository.security.RoleRepository;
import org.example.repository.security.RoleRepositorySQL;
import org.example.repository.security.UserRepository;
import org.example.repository.security.UserRepositorySQL;
import org.example.service.security.SecurityService;
import org.example.view.LoginView;

import java.sql.Connection;

public class ComponentFactory {

  private final LoginView loginView;
  private final LoginController loginController;

  private final SecurityService securityService;

  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final BookRepository bookRepository;


  public ComponentFactory(SupportedDatabase supportedDatabase, Boolean componentsForTest) {
    final Connection connection = DatabaseConnectionFactory.getConnectionWrapper(supportedDatabase, componentsForTest).getConnection();
    this.roleRepository = new RoleRepositorySQL(connection);
    this.userRepository = new UserRepositorySQL(connection, roleRepository);
    this.securityService = new SecurityService(userRepository, roleRepository);
    this.loginView = new LoginView();
    this.loginController = new LoginController(loginView, securityService);
    this.bookRepository = new BookRepositorySQL(connection);
  }

  public LoginView getLoginView() {
    return loginView;
  }

  public LoginController getLoginController() {
    return loginController;
  }

  public SecurityService getSecurityService() {
    return securityService;
  }

  public RoleRepository getRoleRepository() {
    return roleRepository;
  }

  public UserRepository getUserRepository() {
    return userRepository;
  }

  public BookRepository getBookRepository() {
    return bookRepository;
  }
}
