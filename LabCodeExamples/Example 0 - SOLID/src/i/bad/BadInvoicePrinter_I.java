package i.bad;


import i.ComplexInvoice;
import i.Invoice;

public interface BadInvoicePrinter_I {

	void print(Invoice invoice);
	
	void printComplexInvoice(ComplexInvoice complexInvoice);
	
	void someOtherPrintMethod(Invoice invoice);
	
}
