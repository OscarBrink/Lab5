package supermarketSimulator.supermarketEvents;

import genericSimulator.events.*;
import supermarketSimulator.supermarketState.*;

/**
 * This class is the arrive event of a customer. Contains the effect() which is
 * the action taken when this event occurs.
 * 
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public class ArriveEvent extends Event {
	private SupermarketState state;
	private EventQueue eventQueue;
	private Customer c;

	public ArriveEvent(double time, SupermarketState state, EventQueue eventQueue) {
		c = state.newCustomer(); // Creates customer.
		super.time = time;
		this.state = state;
		this.eventQueue = eventQueue;
		eventQueue.addEvent(this); // Adds itself to eventQueue.

	}

	@Override
	public String getEventName() {
		return "ArriveEvent";
	}

	/**
	 * Lets the customer in if store is open and not full, if it is full, the
	 * customer leaves and is added to statistics. If customer can get in, creates a
	 * pickEvent and another arrival. Increases nr of customers in store.
	 */
	@Override
	public void effect() {
		if (state.Open()) {
			state.increaseTotCustomers();
			new ArriveEvent(state.getTimeState().arrivalTime(time), state, eventQueue); // Creates the next arrival.
			if (state.canEnter()) {

				state.increaseCurrCustomers();
				new PickEvent(state.getTimeState().pickTime(time), state, eventQueue, c);// Creates a pickevent for the
																							// customer.
			} else {
				state.missedCustomer(); // Missed a customer
			}
		}

	}

	@Override
	public String[] getPrintInfo() {
		return new String[] { String.format("%.2f", time), getEventName(), String.valueOf(c.getCustomerNumber()) };
	}

	/**
	 * @return The customer number of the customer in this event.
	 */
	public int getCustomerNumber() {
		return c.getCustomerNumber();
	}
}
