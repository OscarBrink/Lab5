package supermarketState;

/**
 * Class describes a customer visiting the supermarket in the simulation.
 */
public class Customer {
	private int number;
	private double queueTime, startQueue;

	/*
	 * /** Constructor. / public Customer() { number = ++amount; }
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
	public double getQueueTime() {
		return queueTime;
	}

	public void setQueueTime(double time) {
		this.queueTime = time - this.startQueue;
	}

	/**
	 * @param startQueue
	 *            Time queue started.
	 */
	public void setStartQueue(double startQueue) {
		this.startQueue = startQueue;
	}

}
