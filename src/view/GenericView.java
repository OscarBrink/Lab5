package view;

import java.util.Observable;
import java.util.Observer;

public class GenericView extends Observable implements Observer{
	
	public GenericView() {	
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Generic Ändrad");
		setChanged();
		notifyObservers();
		
		
	}

		
}
