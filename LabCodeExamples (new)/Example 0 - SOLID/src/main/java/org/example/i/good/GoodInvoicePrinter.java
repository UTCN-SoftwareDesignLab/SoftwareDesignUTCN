package org.example.i.good;

import org.example.i.ComplexInvoice;
import org.example.i.Invoice;

public class GoodInvoicePrinter implements GoodInvoicePrinter_I {

  @Override
  public void print(Invoice invoice) {
    System.out.println("Printing invoice " + invoice);
  }

  @Override
  public void printComplexInvoice(ComplexInvoice complexInvoice) {
    System.out.println("Printing complex invoice " + complexInvoice);
  }

}
