package i.good;


import i.Invoice;

public class SomeOtherGoodInvoicePrinter implements
        SomeOtherGoodInvoicePrinter_I {

    @Override
    public void someOtherPrintMethod(Invoice invoice) {
        System.out.println("Printing the invoice in a totally different way "
                + invoice);
    }
}