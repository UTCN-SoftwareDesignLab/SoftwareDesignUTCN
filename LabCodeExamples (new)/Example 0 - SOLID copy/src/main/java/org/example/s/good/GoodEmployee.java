package org.example.s.good;

public class GoodEmployee {
  private final String status;
  private final String name;
  private final int hours;

  public GoodEmployee(String status, String name, int hours) {
    this.status = status;
    this.name = name;
    this.hours = hours;
  }

  public String getStatus() {
    return status;
  }

  public String getName() {
    return name;
  }

  public int getHours() {
    return hours;
  }

  @Override
  public String toString() {
    return String.format("Employee: %s, Status: %s, Hours: %d", name, status, hours);
  }
}
