package org.example.database;

public class Constants {

  public static class SCHEMAS {

    public static final String TEST = "sd-basics-test";
    public static final String PRODUCTION = "sd-basics";

    public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
  }

  public static class TABLES {
    public static final String BOOK = "book";
    public static final String USER = "user";
    public static final String ROLE = "role";
    public static final String USER_ROLE = "user_role";

    public static final String[] ORDERED_TABLES_FOR_CREATION = {BOOK, USER, ROLE, USER_ROLE};
  }

  public static class Roles {
    public static final String ADMINISTRATOR = "administrator";
    public static final String EMPLOYEE = "employee";
    public static final String CUSTOMER = "customer";

    public static final String[] ROLES = new String[]{ADMINISTRATOR, EMPLOYEE, CUSTOMER};
  }

}
