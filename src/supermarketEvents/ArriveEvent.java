package supermarketEvents;

import events.Event;
import supermarketState.SupermarketState;

public class ArriveEvent extends Event {
	private SupermarketState state;

	public ArriveEvent(int time, SupermarketState state) {
		// Customer c = createCustomer(); Skapa kund i kundfabriken.
		super.time = time;
		this.state = state;

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
