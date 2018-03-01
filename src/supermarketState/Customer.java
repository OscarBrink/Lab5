package supermarketState;

/**
 * Class describes a customer visiting the supermarket in the simulation.
 */
public class Customer {
	private int number,
		queueTime,
		startQueue;

	/*
	/**
	 * Constructor.
	 * /
	public Customer() {
		number = ++amount;
	}
	*/
	public Customer(int number) {
		this.number = number;
	}

	/**
	 * @return int Gets the customer ID-number.
	 */
	public int getCustomerNumber() {
		return number;
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
