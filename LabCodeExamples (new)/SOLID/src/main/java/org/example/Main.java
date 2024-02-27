package org.example;

import org.example.d.bad.HtmlInvoicePrinter;
import org.example.d.good.GoodPrintingService;
import org.example.i.Invoice;
import org.example.i.good.GoodInvoicePrinter;
import org.example.i.good.GoodInvoicePrinter_I;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    testD();
  }

  private static void testD() {
    GoodInvoicePrinter_I htmlInvoicePrinter = new HtmlInvoicePrinter();
    GoodInvoicePrinter_I goodInvoicePrinter = new GoodInvoicePrinter();

    GoodPrintingService goodPrintingService = new GoodPrintingService(htmlInvoicePrinter);

    Invoice invoice1 = new Invoice(68);
    goodPrintingService.print(invoice1);

    goodPrintingService = new GoodPrintingService(goodInvoicePrinter);

    Invoice invoice = new Invoice(5);
    goodPrintingService.print(invoice);
  }


}