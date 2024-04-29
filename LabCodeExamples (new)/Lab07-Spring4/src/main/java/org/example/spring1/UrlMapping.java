package org.example.spring1;

public class UrlMapping {
  public static final String API = "/api";
  public static final String ITEMS = API + "/items";

  public static final String AUTH = API + "/auth";

  public static final String SIGN_IN = "/sign-in";
  public static final String SIGN_UP = "/sign-up";

  public static final String ID_PART = "/{id}";
  public static final String FILTERED_ITEMS_PART = "/filtered";

  public static final String CHANGE_NAME_PART = "/change-name";
}
