package org.example.d.good;


import org.example.i.Invoice;
import org.example.i.good.GoodInvoicePrinter_I;

public class GoodPrintingService {

  private final GoodInvoicePrinter_I printer;

  public GoodPrintingService(GoodInvoicePrinter_I printer) {
    this.printer = printer;
  }

  public void print(Invoice invoice) {
    printer.print(invoice);
  }
}
