package supermarketSimulator.supermarketEvents;

import genericSimulator.events.*;
import supermarketSimulator.supermarketState.*;

public class ArriveEvent extends Event {
	private SupermarketState state;
	private EventQueue que;
	private Customer c;

	public ArriveEvent(double time, SupermarketState state, EventQueue que) {
		c = state.newCustomer(); // Creates customer.
		super.time = time;
		this.state = state;
		this.que = que;
		que.addEvent(this); // Adds itself to eventQueue.

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
		state.increaseTotCustomers();
		state.increaseIdleTime();
		if (state.Open()) {
			if (state.canEnter()) {
				state.increaseCurrCustomers();
				new PickEvent(state.getTimeState().pickTime(time), state, que, c);// Creates a pickevent for the customer.
				new ArriveEvent(state.getTimeState().arrivalTime(time), state, que); // Creates the next arrival.
			} else {
				state.missedCustomer(); // Missed a customer
			}
		} else {
			new ArriveEvent(state.getTimeState().arrivalTime(time), state, que); // Keeps generating customers even if store is
																	// closed??
		}

	}

	@Override
	public String[] getPrintInfo() {
		return new String[]{
				String.format("%.2f", time),
				getEventName(),
				String.valueOf(c.getCustomerNumber())
		};
	}



	public int getCustomerNumber() {
		return c.getCustomerNumber();
	}
}
