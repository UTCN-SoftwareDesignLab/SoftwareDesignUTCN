package org.example.s.good;

public class DatabaseService {

  public void save(GoodEmployee employee) {
    System.out.printf("%s saved to the database\n", employee.getName());
  }
}
