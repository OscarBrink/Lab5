package supermarketSimulator.supermarketState;

import java.util.ArrayList;

import genericSimulator.events.EventQueue;
import genericSimulator.state.State;
import supermarketSimulator.supermarketEvents.PickEvent;
import supermarketSimulator.supermarketEvents.StartEvent;

public class SupermarketState extends State {

	private int finishedCustomers, currentCustomers, customersMissed, maxCustomers;
	private double queueTime = 0.0, idleCashierTime;
	private int openCashiers, queTot = 0;
	private int nrOfFreeCashiers = openCashiers;
	private double currentTime;
	private EventQueue queueList;
	private CustomerFactory customerFactory = new CustomerFactory();
	private boolean isOpen = true;
	private ArrayList<Customer> cashierQueue = new ArrayList<Customer>();

	public SupermarketState(EventQueue queueList) {
		this.queueList = queueList;
	}

	public EventQueue getQueueList() {
		return queueList;
	}

	/**
	 * Puts all the variables for each event into an array.
	 * 
	 * @return info Array with all the variables.
	 */
	public String[] supermarketInfo() {
		int size = queueListSize();
		String[] info = new String[13];
		info[0] = String.valueOf(queueList.getFirst().getTime()); // Händelsetidpunkt
		info[1] = queueList.getFirst().getEventName(); // Händelsenamn
		info[2] = "";// String.valueOf(((PickEvent) (queueList.get(size-1))).getCustomerNumber());
						// //Kundnr
		info[3] = isOpen(); // Affären öppen eller stängd
		info[4] = String.valueOf(nrOfFreeCashiers); // Lediga kassor
		info[5] = String.valueOf(idleCashierTime); // Tid som kassor varit lediga
		info[6] = String.valueOf(currentCustomers); // Nuvarande kunder i affären
		info[7] = String.valueOf(finishedCustomers); // Kunder som betalat
		info[8] = String.valueOf(customersMissed); // Kunder som inte kom in
		info[9] = String.valueOf(queueTime); // Totala antalet kunder som köat
		info[10] = String.valueOf(queueTime); // Summan tid kunder har köat
		info[11] = "queueueuTot";// String.valueOf(((PickEvent) (queueList.get(size-1))).getQueSize()); //Hur
									// lång kassakön är
		info[12] = "queueu";// ((PickEvent) (queueList.get(size-1))).queToString(); //Skriver ut kassakön
		return info;
	}

	/**
	 * Puts the start parameters for the simulator into an array.
	 */
	public double[] supermarketParameters() {
		double[] parameters = new double[8];
		parameters[0] = openCashiers;
		parameters[1] = maxCustomers;
		parameters[2] = // Ankomsthatighet
				parameters[3] = // plocktid minsta
						parameters[4] = // plocktid största
								parameters[5] = // betaltid minsta
										parameters[6] = // betaltid största
												parameters[7] = 0;// fröet
		return parameters;
	}

	/**
	 * Puts the result of the simulation into an array.
	 */
	public double[] supermarketResult() {
		int size = queueListSize();
		double[] result = new double[8];
		result[0] = // Totala antalet kunder som kommit till affären, oavsett öppet eller stängt.
				result[1] = finishedCustomers; // Kunder som betalat
		result[2] = customersMissed; // Kunder som inte kom in
		result[3] = openCashiers; // Antal tillgängliga kassor
		result[4] = idleCashierTime; // Summan av tid kassor varit lediga
		result[5] = queueList.get(size - 1).getTime(); // Summan av tiden affären varit öppen.
		result[6] = (queTot); // Max antal kunder som köat
		result[7] = (this.queueTime); // Summan av tid kunder köat.
		return result;
	}

	/**
	 * Creates a customer and returns it.
	 */
	public Customer newCustomer() {
		return customerFactory.newCustomer();
	}

	public int queueListSize() {
		return queueList.size();
	}

	/**
	 * Adds a customer to the cashierqueue.
	 * 
	 * @param c
	 *            The customer to be added.
	 */
	public void addCustomer(Customer c) {
		this.cashierQueue.add(c);
		queTot++;
	}

	/**
	 * Returns the next customer in line and removes it from the queue.
	 */
	public Customer getNextCustomer() {
		Customer next = this.cashierQueue.get(0);
		this.cashierQueue.remove(0);
		return next;
	}

	public int getCashierQueSize() {
		return this.cashierQueue.size();
	}

	/**
	 * Returns a string containing the customernumbers off all customers in the
	 * casherQueee.
	 */
	public String toStringCashQue() {
		String queString = "[";
		for (Customer c : this.cashierQueue)
			queString += c.getCustomerNumber() + ", ";
		return queString + "]";
	}

	private String isOpen() {
		return (currentCustomers <= maxCustomers) ? "Ö" : "S";
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

	public void setMaxCustomers(int maxCustomers) {
		this.maxCustomers = maxCustomers;
	}

	public void setOpenCashiers(int openCashiers) {
		this.openCashiers = openCashiers;
	}
}
