package d.bad;

import i.Invoice;
import i.good.GoodInvoicePrinter;

public class BadPrintingService {

    private final GoodInvoicePrinter invoicePrinter;
    private final HtmlInvoicePrinter htmlInvoicePrinter;

    public BadPrintingService() {
        this.invoicePrinter = new GoodInvoicePrinter();
        this.htmlInvoicePrinter = new HtmlInvoicePrinter();
    }

    public void print(Invoice invoice, boolean printHtml) {
        if (printHtml) {
            htmlInvoicePrinter.print(invoice);
        } else {
            invoicePrinter.print(invoice);
        }
    }

}
