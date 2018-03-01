package supermarketEvents;

import events.*;
import supermarketState.*;

public class PickEvent extends Event {
	private SupermarketState state;
	private EventQueue que;
	private Customer c;

	public PickEvent(double time, SupermarketState state, EventQueue que, Customer c) {
		this.c = c;
		super.time = time;
		this.state = state;
		this.que = que;
		que.addEvent(this);// Adds itself to the EventQueue
	}

	@Override
	public void effect() {
		if (state.getFreeCashiers() > 0) {
			// If there are free cashiers. Pay.
			new PayEvent(TimeState.paymentTime(), state, que, c);
		} else {
			// add customer to Que?
		}
	}

	@Override
	public String getEventName() {
		return "PickEvent";
	}
}
