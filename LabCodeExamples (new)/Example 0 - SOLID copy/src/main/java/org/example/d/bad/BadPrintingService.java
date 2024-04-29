package org.example.d.bad;


import org.example.i.Invoice;

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
