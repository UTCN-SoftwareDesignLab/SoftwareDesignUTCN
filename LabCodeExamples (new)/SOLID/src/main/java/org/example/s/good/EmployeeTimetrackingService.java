package org.example.s.good;

public class EmployeeTimetrackingService {

    public String reportHours(GoodEmployee employee) {
        return String.format("%s worked %d hours.\n", employee.getName(),
                employee.getHours());
    }

}
