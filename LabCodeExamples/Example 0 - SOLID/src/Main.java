import d.bad.BadPrintingService;
import d.good.GoodPrintingService;
import d.good.HtmlInvoicePrinter;
import i.ComplexInvoice;
import i.Invoice;
import i.bad.BadInvoicePrinter;
import i.bad.BadInvoicePrinter_I;
import i.bad.SomeOtherBadInvoicePrinter;
import i.good.GoodInvoicePrinter;
import i.good.GoodInvoicePrinter_I;
import i.good.SomeOtherGoodInvoicePrinter;
import i.good.SomeOtherGoodInvoicePrinter_I;
import l.bad.BadGraphicsService;
import l.bad.BadRectangle;
import l.bad.BadSquare;
import l.good.GoodGraphicsService;
import l.good.GoodRectangle;
import l.good.GoodSquare;
import l.good.Shape;
import o.bad.AnotherBadClient;
import o.bad.BadClient;
import o.bad.BadServer;
import o.good.AnotherGoodClient;
import o.good.Client_I;
import o.good.GoodClient;
import o.good.GoodServer;
import s.bad.BadEmployee;
import s.good.*;

import java.util.Date;

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
        employee.reportHours();
        employee.save();
    }

    private static void testSGood() {
        GoodEmployee employee = new GoodEmployee("1", "GoodEmployee!", 42);

        EmployeeAdditionalPaymentService additionalPaymentService = new EmployeeAdditionalPaymentService();
        EmployeePaymentService employeePaymentService = new EmployeePaymentService(additionalPaymentService);
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
        BadClient client = new BadClient();
        BadServer server = new BadServer();
        server.reactToClient(client);

        AnotherBadClient anotherBadClient = new AnotherBadClient();
//        server.reactToClient(anotherBadClient);

    }

    private static void testOGood() {
        Client_I client = new GoodClient();
        GoodServer server = new GoodServer();
        server.reactToClient(client);

        AnotherGoodClient anotherGoodClient = new AnotherGoodClient();
        server.reactToClient(anotherGoodClient);
    }


    private static void testL() {
        testLBad();
        testLGood();
    }

    private static void testLBad() {
        BadRectangle actuallySquare = new BadSquare(20, 30);
        BadGraphicsService badGraphicsService = new BadGraphicsService();

        badGraphicsService.checkForArea(actuallySquare);
    }

    private static void testLGood() {
        Shape rectangle = new GoodRectangle(29, 40);
        Shape square = new GoodSquare(20);
        GoodGraphicsService goodGraphicsService = new GoodGraphicsService();

        goodGraphicsService.checkForArea(rectangle);
        goodGraphicsService.checkForArea(square);
    }


    private static void testI() {
        testIBad();
        testIGood();
    }

    private static void testIBad() {
        Invoice invoice = new Invoice(-132523);
        ComplexInvoice complexInvoice = new ComplexInvoice(21439, new Date());

        BadInvoicePrinter_I badInvoicePrinter = new BadInvoicePrinter();
        badInvoicePrinter.print(invoice);
        badInvoicePrinter.printComplexInvoice(complexInvoice);
        badInvoicePrinter.someOtherPrintMethod(invoice); //why should I be able to do this?

        BadInvoicePrinter_I someOtherBadInvoicePrinter = new SomeOtherBadInvoicePrinter();
        someOtherBadInvoicePrinter.print(invoice); //why should I be able to do this?
        someOtherBadInvoicePrinter.printComplexInvoice(complexInvoice); //why should I be able to do this?
        someOtherBadInvoicePrinter.someOtherPrintMethod(invoice);
    }

    private static void testIGood() {
        Invoice invoice = new Invoice(423);
        ComplexInvoice complexInvoice = new ComplexInvoice(242, new Date());

        GoodInvoicePrinter_I goodInvoicePrinter = new GoodInvoicePrinter();
        goodInvoicePrinter.print(invoice);
        goodInvoicePrinter.printComplexInvoice(complexInvoice);
        // goodInvoicePrinter.someOtherPrintMethod(invoice); // unavailable! great.


        SomeOtherGoodInvoicePrinter_I someOtherGoodInvoicePrinter = new SomeOtherGoodInvoicePrinter();
        // other 2 methods are unavailable
        someOtherGoodInvoicePrinter.someOtherPrintMethod(invoice);
    }


    private static void testD() {
        testDBad();
        testDGood();
    }

    private static void testDBad() {
        Invoice invoice = new Invoice(665);

        BadPrintingService badPrintingService = new BadPrintingService();
        badPrintingService.print(invoice);

        // now what?
        // badPrintingService = new BadPrintingService(new HtmlInvoicePrinter()); // but why!?
    }

    private static void testDGood() {
        Invoice invoice = new Invoice(665);

        GoodPrintingService goodPrintingService = new GoodPrintingService(
                new GoodInvoicePrinter());
        goodPrintingService.print(invoice);

        goodPrintingService = new GoodPrintingService(new HtmlInvoicePrinter());
        goodPrintingService.print(invoice);
    }
}
