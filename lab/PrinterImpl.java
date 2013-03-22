package src.lab;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;

public class PrinterImpl extends AbstractPrinter {
	HashMap<String,Integer> lossCount = new HashMap<String,Integer>();
	Client next;
	
	@Override
	public boolean isAvailable() {
		return available;
	}

	@Override
	public synchronized void requestToPrint(Client c, File f)
			throws IllegalPrintStateException {
		// TODO Auto-generated method stub
		// if not fair, and if not available, throw IllegalPrintStateException
		if(!isFair(c) || !isAvailable()){
			throw new IllegalPrintStateException();
		}
		else{
			available = false;
			lossCount.put(c.getName(), 0);
			client = c;
			file = f;
		}
	}

	@Override
	public synchronized void requestToPrintNext(Client c) throws IllegalPrintStateException {
		int value = lossCount.get(c.getName()) + 1;
		lossCount.put(c.getName(), value);
		if(next == null){
			next = c;
			return;
		}
		
		boolean first = Collections.max(lossCount.values()) == value;
		boolean second = lossCount.get(next.getName()) != value;
		if(first && second){
			next = c;
		}
	}

	@Override
	public boolean isFair(Client c) {
		// if never competed, create entry of loss count 0
		if(!lossCount.containsKey(c.getName())){
			lossCount.put(c.getName(), 0);
		}
		
		//if loss count of client is highest among all, it's fair
		Integer max = Collections.max(lossCount.values());
		if(lossCount.get(c.getName()) == max){
			return true;
		}
		
		return false;
	}

	@Override
	protected void print() throws IllegalPrintStateException {
		printInternal();
		available = true;
		
		//get the next guy and notify
		synchronized(next){
			next.notify();
		}
	}
}
