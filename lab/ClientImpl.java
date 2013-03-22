package src.lab;

import java.io.File;
import java.util.List;


public class ClientImpl extends AbstractClient {

	public ClientImpl(String name, Printer p, List<File> queue) {
		super(name, p, queue);;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected void tryToPrint() {
		// TODO Auto-generated method stub
		// definition of fairness
		// get fairness. If fair, we say requestToPrint. 
		// if not we say requestToPrintNext
		synchronized(printer){
			try{
				while(printer.isFair(this) != true || printer.isAvailable() != true){
					printer.requestToPrintNext(this);
					synchronized(this){
						wait();
					}
				}	
				printer.requestToPrint(this, queue.remove(0));
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}
}
