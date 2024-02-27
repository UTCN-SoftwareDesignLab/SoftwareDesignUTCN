import d.bad.HtmlInvoicePrinter;
import d.good.GoodPrintingService;
import i.Invoice;
import i.good.GoodInvoicePrinter;
import i.good.GoodInvoicePrinter_I;
import l.bad.BadGraphicsService;
import l.bad.BadRectangle;
import o.bad.BadMobileClient;
import o.bad.BadServer;
import o.bad.BadWebClient;
import o.good.Client;
import o.good.GoodMobileClient;
import o.good.GoodServer;
import o.good.GoodWebClient;
import s.bad.BadEmployee;
import s.good.EmployeePaymentService;
import s.good.EmployeeRepository;
import s.good.EmployeeTimetrackingService;
import s.good.GoodEmployee;

public class Main {

    public static void main(String[] args) {
        testS();
        testO();
        testL();
        testI();
        testD();
    }

    private static void testS() {
        testSBad();
        testSGood();
    }

    private static void testSBad() {
        BadEmployee employee = new BadEmployee("1", "BadEmployee", 5);
        employee.calculatePay();
        System.out.println(employee.reportHours());
        employee.save();
    }

    private static void testSGood() {
        GoodEmployee employee = new GoodEmployee("1", "GoodEmployee!", 42);

        EmployeePaymentService employeePaymentService = new EmployeePaymentService();
        employeePaymentService.calculatePay(employee);

        EmployeeTimetrackingService employeeTimetrackingService = new EmployeeTimetrackingService();
        employeeTimetrackingService.reportHours(employee);

        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.save(employee);
    }

    private static void testO() {
        testOBad();
        testOGood();
    }

    private static void testOBad() {
        BadWebClient client = new BadWebClient();
        BadServer server = new BadServer();
        server.reactToClient(client);

        BadMobileClient anotherBadClient = new BadMobileClient();
        server.reactToClient(anotherBadClient);
    }

    private static void testOGood() {

        Client web = new GoodWebClient();
        GoodServer server = new GoodServer();
        server.reactToClient(web);

        Client mobile = new GoodMobileClient();
        server.reactToClient(mobile);

    }

    private static void testL() {
        testLBad();
        testLGood();
    }

    private static void testLBad() {
        BadGraphicsService badGraphicsService = new BadGraphicsService();
        BadRectangle actuallySquare = badGraphicsService.getNewRectangle();

        badGraphicsService.verify(actuallySquare);
    }

    private static void testLGood() {
    }

    private static void testI() {
        testIBad();
        testIGood();
    }

    private static void testIBad() {

    }

    private static void testIGood() {

    }

    private static void testD() {
        testDBad();
        testDGood();
    }

    private static void testDBad() {

    }

    private static void testDGood() {
        GoodInvoicePrinter_I htmlInvoicePrinter = new HtmlInvoicePrinter();
        GoodInvoicePrinter_I goodInvoicePrinter = new GoodInvoicePrinter();

        GoodPrintingService goodPrintingService = new GoodPrintingService(htmlInvoicePrinter);

        Invoice invoice1 = new Invoice(68);
        goodPrintingService.print(invoice1);

        goodPrintingService = new GoodPrintingService(goodInvoicePrinter);

        Invoice invoice = new Invoice(5);
        goodPrintingService.print(invoice);

    }
}
