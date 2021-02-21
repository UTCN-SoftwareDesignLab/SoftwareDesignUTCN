package s.bad;

public class BadEmployee {

    private final String status;
    private final String name;
    private final int hours;

    public BadEmployee(String status, String name, int hours) {
        this.status = status;
        this.name = name;
        this.hours = hours;
    }

    public int calculatePay() {
        return switch (this.status) {
            case "A" -> 1;
            case "B" -> 2;
            default -> 0;
        };
    }

    public void save() {
        System.out.printf("%s saved to database.\n", this.name);
    }

    public String reportHours() {
        return String.format("%s worked %d hours.\n", this.name, this.hours);
    }

    @Override
    public String toString() {
        return "I am a employee";
    }

}
