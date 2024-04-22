package org.example.s.good;

public class PaymentService {
  public int calculatePay(GoodEmployee employee) {
    return switch (employee.getStatus()) {
      case "A" -> 1;
      case "B" -> 2;
      default -> 0;
    };
  }
}
