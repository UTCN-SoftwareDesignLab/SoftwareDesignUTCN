package org.example.launcher;

import org.example.database.Bootstrap;
import org.example.database.SupportedDatabase;

import java.sql.SQLException;

public class Launcher {
  public static boolean BOOTSTRAP = false;

  public static void main(String[] args) {
    bootstrap();

    ComponentFactory componentFactory = new ComponentFactory(SupportedDatabase.MYSQL, false);
    componentFactory.getLoginView().setVisible();
  }

  private static void bootstrap() {
    if (BOOTSTRAP) {
      try {
        new Bootstrap().bootstrap();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
  }
}
