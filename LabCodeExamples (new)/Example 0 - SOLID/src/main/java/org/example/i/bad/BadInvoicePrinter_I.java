package org.example.i.bad;


import org.example.i.ComplexInvoice;
import org.example.i.Invoice;

public interface BadInvoicePrinter_I {

    void print(Invoice invoice);

    void printComplexInvoice(ComplexInvoice complexInvoice);

    void someOtherPrintMethod(Invoice invoice);

}
