package supermarketSimulator.supermarketState;

import java.text.DecimalFormat;
import java.util.ArrayList;

import genericSimulator.events.EventQueue;
import genericSimulator.state.State;

public class SupermarketState extends State {

	private int finishedCustomers, currentCustomers, customersMissed, maxCustomers, totalCustomers;
	private double queueTime = 0.0, idleCashierTime;
	private int openCashiers, queTot = 0;
	private int nrOfFreeCashiers = openCashiers;
	private double currentTime, lambda, pickMin, pickMax, payMin, payMax;
	private long seed;
	private EventQueue queueList;
	private CustomerFactory customerFactory = new CustomerFactory();
	private boolean isOpen = true;
	private ArrayList<Customer> cashierQueue = new ArrayList<Customer>();
	private TimeState timeState;

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
		result[5] = queueList.getFirst().getTime(); // Summan av tiden affären varit öppen.
		result[6] = queTot; // Max antal kunder som köat
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
	 * Adds a customer to the cashierqueue and increases total of customers that has
	 * had to queue.
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

	/**
	 * @return this.cashierQueue.size(); Size of the current cashierQue.
	 */
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

	/**
	 * Checks if the store is full.
	 *
	 * @return Ö if store isn't full, otherwise returns S.
	 */
	private String isOpen() {
		return (currentCustomers < maxCustomers) ? "Ö" : "S";
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
	 * @return nrOfFreeCashiers The nr of cashiers currently free.
	 */
	public int getFreeCashiers() {
		return this.nrOfFreeCashiers;
	}

	public void increaseIdleTime() {
		if (nrOfFreeCashiers > 0) {
			idleCashierTime += getCurrTime();
		}
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

	/**
	 * Increases the total amount of customers that arrives at the store.
	 */
	public void increaseTotCustomers() {
		totalCustomers++;
	}

	public void setMaxCustomers(int maxCustomers) {
		this.maxCustomers = maxCustomers;
	}

	public void setOpenCashiers(int openCashiers) {
		this.openCashiers = openCashiers;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public void setPickMin(double pickMin) {
		this.pickMin = pickMin;
	}

	public void setPickMax(double pickMax) {
		this.pickMax = pickMax;
	}

	public void setPayMin(double payMin) {
		this.payMin = payMin;
	}

	public void setPayMax(double payMax) {
		this.payMax = payMax;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public void initTimeState() {
		this.timeState = new TimeState(seed, lambda, pickMin, pickMax, payMin, payMax);
	}

	public TimeState getTimeState() {
		return timeState;
	}

}
