package supermarketEvents;

import events.*;
import supermarketState.*;

public class ArriveEvent extends Event {
	private SupermarketState state;
	private EventQueue que;

	public ArriveEvent(int time, SupermarketState state, EventQueue que) {
		Customer c = CustomerFactory.newCustomer(); // Creates customer.
		super.time = time;
		this.state = state;
		this.que = que;
		que.addEvent(this); // Adds itself to eventQue.

	}

	@Override
	public String getEventName() {
		return "ArriveEvent";
	}

	@Override
	public void effect() {
		/*
		 * if(butiken är öppen){ if(supermarketstate.getCurrentCustomers() <
		 * supermarketState.getMaxCustomers()) {
		 * 
		 * } else { supermarketState.missedCustomers ++; } }
		 */
	}
}
