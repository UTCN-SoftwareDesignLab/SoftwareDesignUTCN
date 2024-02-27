package org.example.s.good;

public class EmployeePaymentService {

    public int calculatePay(GoodEmployee g) {
        return switch (g.getStatus()) {
            case "A" -> 1;
            case "B" -> 2;
            default -> 0;
        };
    }
}
