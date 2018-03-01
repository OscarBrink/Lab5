package events;

import supermarketState.SupermarketState;

public class PayEvent extends Event {
	private SupermarketState state;

	public PayEvent(int time, SupermarketState state) {
		super.time = time;
		this.state = state;
	}

	@Override
	public String getEventName() {
		return "PayEvent";
	}

	@Override
	public void effect() {
		// currentCustomers -- m.m.

	}

}
