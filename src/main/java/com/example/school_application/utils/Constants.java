package com.example.school_application.utils;

public class Constants {
  public static enum Roles {
    USER, ADMIN
  };

  public static enum Permissions {
    USER_READ, USER_WRITE, USER_DELETE, ADMIN_READ, ADMIN_WRITE, ADMIN_DELETE
  }

  public static enum Status {
    CLOSE, OPEN
  }

  public static enum Type {
    FESTIVAL, FEDERAL
  }

  public static final String user = "anonomyus";
}
