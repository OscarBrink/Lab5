package supermarketEvents;

import events.*;
import supermarketState.*;

public class PayEvent extends Event {
	private SupermarketState state;
	private Customer c;
	private EventQueue que;

	public PayEvent(double time, SupermarketState state, EventQueue que, Customer c) {
		this.c = c;
		this.que = que;
		super.time = time;
		this.state = state;
		que.addEvent(this); // Adds itself to the EventQueue

	}

	@Override
	public String getEventName() {
		return "PayEvent";
	}

	/**
	 * Customer is done paying and leaves. Current nr of customers decreased. If
	 * there is a que to the cashiers next customer comes.
	 */
	@Override
	public void effect() {
		state.decreaseCurrCustomers();
		state.finishedCustomer();
		// If state has a que to the cashiers, que.getnextCustomer?

	}

}
