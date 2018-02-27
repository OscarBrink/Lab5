package supermarketState;

/**
 * Class describes a customer visiting the supermarket in the simulation.
 */
public class Customer {

	private static int amount = 0; /* Amount of customer objects created
									  i.e. created during the simulation. */
	private int customerNumber,
		queueTime,
		startQueue;

	/**
	 * Constructor.
	 */
	public Customer() {
		customerNumber = ++amount;
	}

	/**
	 * @return int Gets the customer ID-number.
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @return int Gets time spent in queue for this customer.
	 */
	public int getQueueTime() {
		return queueTime;
	}

	/**
	 * @param startQueue Time queue started.
	 */
	public void setStartQueue(int startQueue) {
		this.startQueue = startQueue;
	}

}
