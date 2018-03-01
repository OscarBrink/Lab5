package supermarketState;

import events.EventQueue;
import simulation.*;
import state.State;

public class SupermarketState extends State {

	private int finishedCustomers, currentCustomers, customersMissed, maxCustomers;
	private int queueTime, idleCashierTime;
	private int nrOfFreeCashiers, openCashiers;
	private EventQueue queueList;

	public SupermarketState(EventQueue queueList) {
		this.queueList = queueList;
	}

	public EventQueue getQueueList() {
		return queueList;
	}

	/**
	 * Puts all the variables for each event into an array.
	 * @return info Array with all the variables.
	 */
	public String[] supermarketInfo(){
		int size = queueListSize();
		String[] info = new String[13];
		info[0] = String.valueOf(queueList.get(size-1).getTime());
		info[1] = queueList.get(size-1).getEventName();
		info[2] = String.valueOf(queueList.get(size-1).getCustomerNumber());
		info[3] = isOpen();
		info[4] = String.valueOf(nrOfFreeCashiers);
		info[5] = String.valueOf(idleCashierTime);
		info[6] = String.valueOf(currentCustomers);
		info[7] = String.valueOf(finishedCustomers);
		info[8] = String.valueOf(customersMissed);
		info[9] = //Antal som måste köa
		info[10] = String.valueOf(queueTime);
		info[11] = //Antal kunder i kön
		info[12] = //Skriva ut kön
		return info;
	}

	public int queueListSize() {
		return queueList.size();
	}

	private String isOpen() {
		if (currentCustomers <= maxCustomers) {
			return "Ö";
		}
		return "S";
	}

	/**
	 * returns true/false depending on if the max amount of customers are already in
	 * the store. Will be called when a new arrival comes.
	 */
	public boolean canEnter() {
		return currentCustomers < maxCustomers;
	}

	/**
	 * Increases nr of customers missed. Called from arrive if the customer cannot
	 * enter.
	 */
	public void missedCustomer() {
		this.customersMissed++;
	}
}
