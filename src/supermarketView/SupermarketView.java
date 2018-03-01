package supermarketView;

import java.util.Observable;
import supermarketState.SupermarketState;
import view.View;

public class SupermarketView extends View {
	
	private SupermarketState state;
	
	public SupermarketView(SupermarketState state){
		this.state = state;
	}
	
	/**
	 * Updates what is printed each time the state changes.
	 */
	public void update(Observable arg0, Object arg1){
		printEvent();
	}
	
	/**
	 * Prints out all the data from supermarketState.
	 */
	public void printEvent(){
		int size = state.queueListSize();
		String[] info = state.supermarketInfo();
		if(state.getQueueList().get(size-1).getEventName() == "Start"){
			System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "Tid", "Händelse", "Kund", "?", "Led","LedT", "I", "$", ":-(", "Köat", "KöT","Köar", "[Kassakö]");
			System.out.printf("%-10s %-10s\n", info[0], info[1]);
		}
		if(state.getQueueList().get(size-1).getEventName() == "Stop"){
			System.out.printf("%-10s %-10s\n", info[0], info[1]);
		}
		else{
			System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9], info[10],info[11],info[12]);
		}
		
	}

	
}
