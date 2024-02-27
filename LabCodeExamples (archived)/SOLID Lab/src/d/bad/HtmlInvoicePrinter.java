package d.bad;

import i.ComplexInvoice;
import i.Invoice;
import i.good.GoodInvoicePrinter_I;

public class HtmlInvoicePrinter implements GoodInvoicePrinter_I {

    @Override
    public void print(Invoice invoice) {
        System.out.println();
    }

    @Override
    public void printComplexInvoice(ComplexInvoice complexInvoice) {
        System.out.println();
    }
}
