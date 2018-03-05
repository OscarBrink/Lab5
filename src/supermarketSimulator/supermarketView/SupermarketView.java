package supermarketSimulator.supermarketView;

import java.text.DecimalFormat;
import java.util.Observable;
import supermarketSimulator.supermarketState.SupermarketState;
import genericSimulator.view.View;

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
		String[] info = state.supermarketInfo();
		if(state.getQueueList().getFirst().getEventName() == "StartEvent"){
			printParameters();
			System.out.println("FÖRLOPP\n==========\n");
			System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "Tid", "Händelse", "Kund", "?", "Led","LedT", "I", "$", ":-(", "Köat", "KöT","Köar", "[Kassakö]");
			System.out.printf("%-10s %-10s\n", info[0], info[1]);
		}
		else if(state.getQueueList().getFirst().getEventName() == "CloseEvent"){
			System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", info[0], info[1], "---", info[3], info[4], info[5], info[6], info[7], info[8], info[9], info[10],info[11],info[12]);
		}
		else if(state.getQueueList().getFirst().getEventName() == "StopSimEvent"){
			System.out.printf("%-10s %-10s\n", info[0], info[1]);
			printResult();
		}
		else{
			System.out.printf("%-10s %-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9], info[10],info[11],info[12]);
		}	
	}
	
	private void printParameters(){
		double[] par = state.supermarketParameters();
		System.out.println("PARAMETRAR\n==========\n");
		System.out.println("Antal kassor, N......: " + (int)par[0]);
		System.out.println("Max som ryms, M......: " + (int)par[1]);
		System.out.println("Ankomsthastighet, lambda......: " + par[2]);
		System.out.println("Plocktider, [p_min....p_max]: [" + par[3] + "...." + par[4] + "]");
		System.out.println("Betaltider, [k_min....k_max]: [" + par[5] + "...." + par[6] + "]");
		System.out.println("Fröet,f......: " + (long)par[7] + "\n");
	}
	
	private void printResult(){
		DecimalFormat df = new DecimalFormat("#0.00");
		double[] res = state.supermarketResult();
		System.out.println("\nRESULTAT\n==========\n");
		System.out.println("1) Av " + (int)res[0] + " kunder handlade " + (int)res[1] + " medan " + (int)res[2] + " missades.\n");
		System.out.println("2) Total tid " + (int)res[3] + " kassor varit lediga: " + df.format(res[4]) + " te.\n   Genomsnittlig ledig kassatid: " + df.format(res[4]/res[3]) + " te.\n");
		System.out.println("3) Total tid " + (int)res[6] + " kunder tvingats köa: " + df.format(res[7]) + " te.\n   Genomsnittlig kötid: " + df.format(res[7]/res[6]) + " te.\n");
	}
}
