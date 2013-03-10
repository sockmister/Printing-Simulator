package lab;

import java.io.File;

public class PrinterImpl extends AbstractPrinter {

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void requestToPrint(Client c, File f)
			throws IllegalPrintStateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestToPrintNext(Client c) throws IllegalPrintStateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFair(Client c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void print() throws IllegalPrintStateException {
		// TODO Auto-generated method stub
		
	}



}
