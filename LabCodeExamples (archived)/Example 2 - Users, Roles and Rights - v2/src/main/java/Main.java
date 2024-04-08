import java.time.LocalDate;
import java.time.ZonedDateTime;

public class Main {
  public static void main(String[] args) {
    String charSequence = ZonedDateTime.now().toLocalDate().toString();
    System.out.println(charSequence);
    LocalDate parse = LocalDate.parse(charSequence);
    System.out.println(parse);

  }
}
