package org.example;

import org.example.s.good.GoodEmployee;
import org.example.s.good.ReportingService;

public class Main {
  public static void main(String[] args) {

    GoodEmployee goodEmployee = new GoodEmployee("A", "John", 40);

    System.out.println(goodEmployee.toString());

    ReportingService reportingService = new ReportingService();
    String result = reportingService.reportHours(goodEmployee);
    System.out.println(result);
  }


}