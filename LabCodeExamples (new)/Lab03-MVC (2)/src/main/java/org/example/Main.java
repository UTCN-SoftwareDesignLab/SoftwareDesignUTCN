package org.example;

import org.example.database.Bootstrap;
import org.example.launcher.ComponentFactory;
import org.example.service.security.SecurityService;

import java.sql.SQLException;

import static org.example.database.SupportedDatabase.MYSQL;

public class Main {
  public static void main(String[] args) throws SQLException {
    new Bootstrap().bootstrap();

    ComponentFactory componentFactory = new ComponentFactory(MYSQL, true);

    SecurityService securityService = componentFactory.getSecurityService();
    securityService.register("user", "parola");

    componentFactory.getLoginView().setVisible();
  }
}