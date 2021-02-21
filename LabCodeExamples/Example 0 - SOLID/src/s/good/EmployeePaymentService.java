package s.good;

public class EmployeePaymentService {

    private final EmployeeAdditionalPaymentService eAPS;

    public EmployeePaymentService(EmployeeAdditionalPaymentService employeeAdditionalPaymentService) {
        this.eAPS = employeeAdditionalPaymentService;
    }

    public int calculatePay(GoodEmployee employee) {
        return switch (employee.status) {
            case "A" -> 1;
            case "B" -> 2;
            default -> 0;
        };
    }
}
