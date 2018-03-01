package supermarketState;

import java.util.Observable;

import events.EventQueue;
import view.GenericView;

public class GenericState extends Observable{
	boolean emergencyBreak = false;
	int currentTime;
	EventQueue queueList;
	
	private GenericView genericView;
	
	public GenericState(GenericView g) {
		genericView = g;
		this.addObserver(g);
		
	}
	
	
	
	public void test() {
		setChanged();
		notifyObservers();
	}
	
	

}
