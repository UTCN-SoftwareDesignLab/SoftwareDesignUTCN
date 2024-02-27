package org.example.i.good;


import org.example.i.Invoice;

public class SomeOtherGoodInvoicePrinter implements SomeOtherGoodInvoicePrinter_I {

  @Override
  public void someOtherPrintMethod(Invoice invoice) {
    System.out.println("...");
  }

}
