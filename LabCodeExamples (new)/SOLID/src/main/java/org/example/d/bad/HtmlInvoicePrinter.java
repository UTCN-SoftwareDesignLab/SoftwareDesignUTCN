package org.example.d.bad;


import org.example.i.ComplexInvoice;
import org.example.i.Invoice;
import org.example.i.good.GoodInvoicePrinter_I;

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
