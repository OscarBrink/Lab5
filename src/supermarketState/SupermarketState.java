package supermarketState;

import events.EventQueue;
import state.State;
import supermarketEvents.PickEvent;
import supermarketEvents.ArriveEvent;
import supermarketEvents.CloseSupermarketEvent;
import supermarketEvents.PayEvent;
import supermarketEvents.StartEvent;
import supermarketEvents.StopSimEvent;

public class SupermarketState extends State {

	private int finishedCustomers, currentCustomers, customersMissed, maxCustomers;
	private double queueTime, idleCashierTime;
	private int nrOfFreeCashiers, openCashiers;
	private double currentTime;
	private EventQueue queueList;
	private boolean isOpen;

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
		info[2] = String.valueOf(((PickEvent) (queueList.get(size-1))).getCustomerNumber());
		info[3] = isOpen();
		info[4] = String.valueOf(nrOfFreeCashiers);
		info[5] = String.valueOf(idleCashierTime);
		info[6] = String.valueOf(currentCustomers);
		info[7] = String.valueOf(finishedCustomers);
		info[8] = String.valueOf(customersMissed);
		info[9] = String.valueOf(((PickEvent) (queueList.get(size-1))).getTotQueSize());
		info[10] = String.valueOf(queueTime);
		info[11] = String.valueOf(((PickEvent) (queueList.get(size-1))).getQueSize());
		info[12] = ((PickEvent) (queueList.get(size-1))).queToString();
		return info;
	}
	
	/**
	 * Puts the start parameters for the simulator into an array.
	 */
	public double[] supermarketParameters(){
		double[] parameters = new double[8];
		parameters[0] = openCashiers;
		parameters[1] = maxCustomers;
		parameters[2] = //Ankomsthatighet 
		parameters[3] = //plocktid minsta
		parameters[4] = //plocktid största
		parameters[5] = //betaltid minsta
		parameters[6] = //betaltid största
		parameters[7] = //fröet
		return parameters;
	}
	
	/**
	 * Puts the result of the simulation into an array.
	 */
	public double[] supermarketResult(){
		int size = queueListSize();
		double[] result = new double[8];
		result[0] = //Totala antalet kunder som kommit till affären, oavsett öppet eller stängt.
		result[1] = finishedCustomers;
		result[2] = customersMissed;
		result[3] = openCashiers;
		result[4] = idleCashierTime;
		result[5] = queueList.get(size-1).getTime();
		result[6] = maxCustomers;
		result[7] = //Total tid maxantalet på kunder har köat.
		return result;
	}

	public int queueListSize() {
		return queueList.size();
	}

	private String isOpen() {
		return (currentCustomers <= maxCustomers) ? "Ö":"S"; 
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

	/**
	 * Increases current nr of customers in the store.
	 */
	public void increaseCurrCustomers() {
		this.currentCustomers++;
	}

	/**
	 * Decreases current nr of customers in the store.
	 */
	public void decreaseCurrCustomers() {
		this.currentCustomers--;
	}

	public void finishedCustomer() {
		this.finishedCustomers++;
	}

	/**
	 * Returns true if the store is open.
	 */
	public boolean Open() {
		return isOpen;
	}

	/**
	 * sets isOpen to false, closes store.
	 */
	public void closeStore() {
		this.isOpen = false;
	}

	/**
	 * @return nrOfFreeCashiers The nr of cashiers currently free.
	 */
	public int getFreeCashiers() {
		return this.nrOfFreeCashiers;
	}

	/**
	 * @return currentTime The current time.
	 */
	public double getCurrTime() {
		return this.currentTime;
	}

	/**
	 * Increases the total quetime for the store.
	 * 
	 * @param time
	 *            The time a specific customer has been in que.
	 */
	public void increaseQueTime(double time) {
		this.queueTime += time;
	}

	/**
	 * Increases amount of free cashiers.
	 */
	public void increaseFreeCashiers() {
		this.nrOfFreeCashiers++;
	}

	/**
	 * Decreases nr of free cashiers.
	 */
	public void decreaseFreeCashiers() {
		this.nrOfFreeCashiers--;
	}
}
