package org.example.i.good;


import org.example.i.ComplexInvoice;
import org.example.i.Invoice;

public interface GoodInvoicePrinter_I {
  void print(Invoice invoice);

  void printComplexInvoice(ComplexInvoice complexInvoice);
}
