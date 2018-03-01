package view;

import java.util.Observable;
import java.util.Observer;
import supermarketState.GenericState;

public class SupermarketView extends GenericView implements Observer {
	
		public SupermarketView(GenericView g) {
			GenericView genView = g;
			genView.addObserver(this);
		}
		
	
	
	public void update(Observable arg0, Object arg1) {
		System.out.println("Supermarket Ändrad");
		
	}

}
