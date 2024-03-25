package org.example.database;

import static org.example.database.Constants.TABLES.*;

public class SQLTableCreationFactory {

  public String getCreateSQLForTable(String table) {
    return switch (table) {
      case BOOK -> "CREATE TABLE IF NOT EXISTS book (" +
          "  id int(11) NOT NULL AUTO_INCREMENT," +
          "  author varchar(500) NOT NULL," +
          "  title varchar(500) NOT NULL," +
          "  publishedDate datetime DEFAULT NULL," +
          "  PRIMARY KEY (id)," +
          "  UNIQUE KEY id_UNIQUE (id)" +
          ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
      case USER -> "CREATE TABLE IF NOT EXISTS user (" +
          "  id INT NOT NULL AUTO_INCREMENT," +
          "  username VARCHAR(200) NOT NULL," +
          "  password VARCHAR(64) NOT NULL," +
          "  PRIMARY KEY (id)," +
          "  UNIQUE INDEX id_UNIQUE (id ASC)," +
          "  UNIQUE INDEX username_UNIQUE (username ASC));";
      case ROLE -> "  CREATE TABLE IF NOT EXISTS role (" +
          "  id INT NOT NULL AUTO_INCREMENT," +
          "  role VARCHAR(100) NOT NULL," +
          "  PRIMARY KEY (id)," +
          "  UNIQUE INDEX id_UNIQUE (id ASC)," +
          "  UNIQUE INDEX role_UNIQUE (role ASC));";
      case USER_ROLE -> "\tCREATE TABLE IF NOT EXISTS user_role (" +
          "  id INT NOT NULL AUTO_INCREMENT," +
          "  user_id INT NOT NULL," +
          "  role_id INT NOT NULL," +
          "  PRIMARY KEY (id)," +
          "  UNIQUE INDEX id_UNIQUE (id ASC)," +
          "  INDEX user_id_idx (user_id ASC)," +
          "  INDEX role_id_idx (role_id ASC)," +
          "  CONSTRAINT user_fkid" +
          "    FOREIGN KEY (user_id)" +
          "    REFERENCES user (id)" +
          "    ON DELETE CASCADE" +
          "    ON UPDATE CASCADE," +
          "  CONSTRAINT role_fkid" +
          "    FOREIGN KEY (role_id)" +
          "    REFERENCES role (id)" +
          "    ON DELETE CASCADE" +
          "    ON UPDATE CASCADE);";
      default -> "";
    };
  }

}
