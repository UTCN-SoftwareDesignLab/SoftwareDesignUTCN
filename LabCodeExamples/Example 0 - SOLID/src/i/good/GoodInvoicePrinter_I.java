package i.good;

import i.ComplexInvoice;
import i.Invoice;

public interface GoodInvoicePrinter_I {

    void print(Invoice invoice);

    void printComplexInvoice(ComplexInvoice complexInvoice);

}
