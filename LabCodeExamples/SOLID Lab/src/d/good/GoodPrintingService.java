package d.good;

import i.Invoice;
import i.good.GoodInvoicePrinter_I;

public class GoodPrintingService {

    private final GoodInvoicePrinter_I printer;

    public GoodPrintingService(GoodInvoicePrinter_I printer) {
        this.printer = printer;
    }

    public void print(Invoice invoice) {
        printer.print(invoice);
    }
}
