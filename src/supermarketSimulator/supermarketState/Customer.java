package supermarketSimulator.supermarketState;

/**
 * Class describes a customer visiting the supermarket in the simulation.
 *
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public class Customer {
	private int number;
	private double queueTime = 0.00d, startQueue = 0.00d;

	/**
	 * Constructor.
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

	/**
	 * Sets the Quetime.
	 * 
	 * @param time
	 *            The current time.
	 * @pre startQueue must have been set.
	 */
	public void setQueueTime(double time) {
		if (this.startQueue != 0) { // If startQueue is set.
			this.queueTime = time - this.startQueue;
		}

	}

	/**
	 * @param startQueue
	 *            Time queue started.
	 */
	public void setStartQueue(double startQueue) {
		this.startQueue = startQueue;
	}

}
