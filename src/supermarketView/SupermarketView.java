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
		if(state.getQueueList().get(size-1).getEventName() == "StartEvent"){
			printParameters();
			System.out.println("FÖRLOPP\n==========\n");
			System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "Tid", "Händelse", "Kund", "?", "Led","LedT", "I", "$", ":-(", "Köat", "KöT","Köar", "[Kassakö]");
			System.out.printf("%-10s %-10s\n", info[0], info[1]);
		}
		if(state.getQueueList().get(size-1).getEventName() == "StopEvent"){
			System.out.printf("%-10s %-10s\n", info[0], info[1]);
			printResult();
		}
		else{
			System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9], info[10],info[11],info[12]);
		}	
	}
	
	private void printParameters(){
		double[] par = state.supermarketParameters();
		System.out.println("PARAMETRAR\n==========\n");
		System.out.println("Antal kassor, N......: " + par[0]);
		System.out.println("Max som ryms,M......: " + par[1]);
		System.out.println("Ankomsthastighet, lambda......: " + par[2]);
		System.out.println("Plocktider, [p_min....p_max]: [" + par[3] + "...." + par[4] + "]");
		System.out.println("Betaltider, [k_min....k_max]: [" + par[5] + "...." + par[6] + "]");
		System.out.println("Fröet,f......: " + par[7] + "\n");
	}
	
	private void printResult(){
		double[] res = state.supermarketResult();
		System.out.println("RESULTAT\n==========\n");
		System.out.println("1) Av " + res[0] + " kunder handlade " + res[1] + " medan " + res[2] + " missades.");
		System.out.println("2) Total tid " + res[3] + " varit lediga: " + res[4] + " te.\nGenomsnittlig ledig kassatid: " + res[5]/res[4] + "te.");
		System.out.println("3) Total tid " + res[6] + " kunder tvingats köa: " + res[7] + " te.\nGenomsnittlig kötid: " + res[5]/res[7] + "te.");
	}
}
