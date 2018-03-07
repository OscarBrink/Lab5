package supermarketSimulator.supermarketState;

import java.text.DecimalFormat;
import java.util.ArrayList;

import genericSimulator.events.EventQueue;
import genericSimulator.state.State;

/**
 * 
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 * 
 *         This class represents the state of the supermarket, meaning it keeps
 *         track of all the variables that updates during the simulation and
 *         also creates customers (and cashierqueues if needed).
 */
public class SupermarketState extends State {

	private int finishedCustomers, currentCustomers, customersMissed,
	maxCustomers, totalCustomers;
	private double queueTime = 0.0, idleCashierTime, previousTime, paymentTime;
	private int openCashiers, queTot = 0;
	private int nrOfFreeCashiers;
	private double currentTime, lambda, pickMin, pickMax, payMin, payMax;
	private long seed;
	private EventQueue queueList;
	private CustomerFactory customerFactory = new CustomerFactory();
	private boolean isOpen = true;
	private ArrayList<Customer> cashierQueue = new ArrayList<Customer>();
	private TimeState timeState;

	/**
	 * Constructor for SupermarketState, takes an EventQueue object to be able
	 * to accesses the simulations events.
	 * 
	 * @param queueList
	 *            the current simulations EventQueue object.
	 */
	public SupermarketState(EventQueue queueList) {
		this.queueList = queueList;
	}

	/**
	 * Puts all the variables for each event into an array.
	 *
	 * @return info Array with all the variables.
	 */
	public String[] supermarketInfo() {
		DecimalFormat df = new DecimalFormat("#0.00");
		String[] info = new String[13];

		String[] eventInfo = queueList.getFirst().getPrintInfo();
		info[0] = eventInfo[0]; // Händelsetidpunkt
		info[1] = eventInfo[1]; // Händelsenamn
		info[2] = eventInfo[2]; // Kundnr

		info[3] = isOpen(); // Affären öppen eller stängd
		info[4] = String.valueOf(nrOfFreeCashiers); // Lediga kassor
		info[5] = String.valueOf(df.format(idleCashierTime)); // Tid som kassor varit lediga
		info[6] = String.valueOf(currentCustomers); // Nuvarande kunder i affären
		info[7] = String.valueOf(finishedCustomers); // Kunder som betalat
		info[8] = String.valueOf(customersMissed); // Kunder som inte kom in
		info[9] = String.valueOf(queTot); // Totala antalet kunder som köat
		info[10] = String.valueOf(df.format(queueTime)); // Summan tid kunder har köat
		info[11] = String.valueOf(getCashierQueSize()); // Hur lång kassakön är
		info[12] = toStringCashQue(); // Skriver ut kassakön
		return info;
	}

	/**
	 * Puts the start parameters for the simulator into an array.
	 *
	 * @return parameters Array of the simulators parameters.
	 */
	public double[] supermarketParameters() {
		double[] parameters = new double[8];
		parameters[0] = openCashiers;
		parameters[1] = maxCustomers;
		parameters[2] = lambda; // Ankomsthatighet
		parameters[3] = pickMin; // plocktid minsta
		parameters[4] = pickMax; // plocktid största
		parameters[5] = payMin; // betaltid minsta
		parameters[6] = payMax; // betaltid största
		parameters[7] = seed; // fröet
		return parameters;
	}

	/**
	 * Puts the result of the simulation into an array.
	 *
	 * @return result Array of the result from the simulation.
	 */
	public double[] supermarketResult() {
		double[] result = new double[8];
		result[0] = totalCustomers;// Totala antalet kunder som kommit till affären, oavsett öppet eller stängt.
		result[1] = finishedCustomers; // Kunder som betalat
		result[2] = customersMissed; // Kunder som inte kom in
		result[3] = openCashiers; // Antal tillgängliga kassor
		result[4] = idleCashierTime; // Summan av tid kassor varit lediga
		result[5] = paymentTime; // Tidpunkt sista kund betalat.
		result[6] = queTot; // Max antal kunder som köat
		result[7] = (this.queueTime); // Summan av tid kunder köat.
		return result;
	}

	/**
	 * Updates the queuetime and the idleCahsierTime before the event gets
	 * printed.
	 */
	public void updateState() {
		setCurrTime(queueList.getFirst().getTime());
		if (isOpen || queueList.getFirst().getEventName() == "PayEvent") {
			increaseIdleTime();
			increaseQueTime();
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Creates a customer and returns it.
	 */
	public Customer newCustomer() {
		return customerFactory.newCustomer();
	}

	/**
	 * Returns the next customer in line and removes it from the queue.
	 */
	public Customer getNextCustomer() {
		Customer next = this.cashierQueue.get(0);
		this.cashierQueue.remove(0);
		return next;
	}

	/**
	 * Adds a customer to the cashierqueue and increases total of customers that
	 * has had to queue.
	 *
	 * @param c
	 *            The customer to be added.
	 */
	public void addCustomer(Customer c) {
		this.cashierQueue.add(c);
		queTot++;
	}

	/**
	 * Increases the total queuetime for the store.
	 * 
	 */
	public void increaseQueTime() {
		if (getCashierQueSize() > 0) {
			this.queueTime += (this.currentTime - this.previousTime)
					* getCashierQueSize();
		}
	}

	/**
	 * Increases the total time cashiers has been idle during the simulation.
	 */
	public void increaseIdleTime() {
		if (nrOfFreeCashiers > 0) {
			idleCashierTime += (this.currentTime - this.previousTime)
					* this.getFreeCashiers();
		}
	}

	/**
	 * @return this.cashierQueue.size(); Size of the current cashierQue.
	 */
	public int getCashierQueSize() {
		return this.cashierQueue.size();
	}

	/**
	 * Returns a string containing the customernumbers off all customers in the
	 * casherQueue.
	 */
	public String toStringCashQue() {
		String queString = "[";
		for (Customer c : this.cashierQueue)
			queString += c.getCustomerNumber() + ", ";
		return queString + "]";
	}

	/**
	 * Returns the eventQueue that is used in the simulation.
	 * 
	 * @return queueList the eventQueue
	 */
	public EventQueue getQueueList() {
		return queueList;
	}

	/**
	 * 
	 * @return the size of the eventQueue
	 */
	public int queueListSize() {
		return queueList.size();
	}

	/**
	 * Creates an TimeState object.
	 */
	public void initTimeState() {
		this.timeState = new TimeState(seed, lambda, pickMin, pickMax, payMin,
				payMax);
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

	/**
	 * Increases the total amount of customers that arrives at the store.
	 */
	public void increaseTotCustomers() {
		totalCustomers++;
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

	/**
	 * Checks if the store is open or closed.
	 *
	 * @return Ö if store is open, otherwise returns S.
	 */
	private String isOpen() {
		return (isOpen) ? "Ö" : "S";
	}

	/**
	 * returns true/false depending on if the max amount of customers are
	 * already in the store. Will be called when a new arrival comes.
	 */
	public boolean canEnter() {
		return currentCustomers < maxCustomers;
	}

	/**
	 * Increases nr of customers missed. Called from arrive if the customer
	 * cannot enter.
	 */
	public void missedCustomer() {
		this.customersMissed++;
	}

	/**
	 * Increases finished customers.
	 */
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
	 * Updates the currentTime and saves the last currentTime in a previousTime
	 * variable.
	 * 
	 * @param time
	 *            the time when the method is called.
	 */
	public void setCurrTime(double time) {
		this.previousTime = this.currentTime;
		this.currentTime = time;
	}

	/**
	 * Saves the time of the latest payEvent.
	 * 
	 * @param time
	 *            the time the payEvent happens.
	 */
	public void setPaymentTime(double time) {
		this.paymentTime = time;
	}

	/**
	 * Sets the max number of customers that can be in the store at once.
	 * 
	 * @param maxCustomers
	 *            number of customers
	 */
	public void setMaxCustomers(int maxCustomers) {
		this.maxCustomers = maxCustomers;
	}

	/**
	 * Sets the start value of how many available cashiers the store has during
	 * the simulation.
	 * 
	 * @param openCashiers
	 *            Amount of cashiers.
	 */
	public void setOpenCashiers(int openCashiers) {
		this.openCashiers = openCashiers;
		this.nrOfFreeCashiers = openCashiers;
	}

	/**
	 * Sets the speed at which the customers arrives to the store.
	 * 
	 * @param lambda
	 *            Customers arrivalspeed.
	 */
	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	/**
	 * @param pickMin
	 *            Setter for minimum pick-time.
	 */
	public void setPickMin(double pickMin) {
		this.pickMin = pickMin;
	}

	/**
	 * @param pickMax
	 *            Setter for maximum pick-time.
	 */
	public void setPickMax(double pickMax) {
		this.pickMax = pickMax;
	}

	/**
	 * @param payMin
	 *            Setter for minimum pay-time.
	 */
	public void setPayMin(double payMin) {
		this.payMin = payMin;
	}

	/**
	 * @param payMax
	 *            Setter for maximum pay-time.
	 */
	public void setPayMax(double payMax) {
		this.payMax = payMax;
	}

	/**
	 * @param seed
	 *            Setter for seed for random values.
	 */
	public void setSeed(long seed) {
		this.seed = seed;
	}

	/**
	 * Returns the total amount of customers visiting the store.
	 * 
	 * @return totalCustomers All the people arriving at the store.
	 */
	public int getTotalCustomers() {
		return totalCustomers;
	}

	/**
	 * Returns total of customers that has not been able to get into the store.
	 * 
	 * @return customersMissed customers that couldn't enter the store.
	 */
	public int getNumberOfMissedCustomers() {
		return customersMissed;
	}

	/**
	 * Returns the number of cashiers available during the simulation
	 * 
	 * @return openCashiers number of cashiers.
	 */
	public int getMaxOpenCashiers() {
		return openCashiers;
	}

	/**
	 * @return currentTime The current time of the simulation.
	 */
	public double getCurrTime() {
		return this.currentTime;
	}

	/**
	 * @return nrOfFreeCashiers The nr of cashiers currently free.
	 */
	public int getFreeCashiers() {
		return this.nrOfFreeCashiers;
	}

	/**
	 * Returns the timeState that has been created with initTimeState method.
	 * 
	 * @return timeState A TimeState object.
	 */
	public TimeState getTimeState() {
		return timeState;
	}
}
