package supermarketEvents;

import events.*;
import supermarketState.*;

public class ArriveEvent extends Event {
	private SupermarketState state;
	private EventQueue que;
	private Customer c;

	public ArriveEvent(int time, SupermarketState state, EventQueue que) {
		c = CustomerFactory.newCustomer(); // Creates customer.
		super.time = time;
		this.state = state;
		this.que = que;
		que.addEvent(this); // Adds itself to eventQue.

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
			if (state.canEnter()) {
				state.IncreaseCurrCustomers();
				new PickEvent(0, state, que, c);// Creates a pickevent for the customer.
				new ArriveEvent(0, state, que); // Creates the next arrival.
			} else {
				state.missedCustomer(); // Missed a customer
			}
		} else {
			new ArriveEvent(0, state, que); // Keeps generating customers even if store is closed??
		}

	}
}
