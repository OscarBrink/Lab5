package supermarketSimulator.supermarketEvents;

import genericSimulator.events.*;
import supermarketSimulator.supermarketState.*;

public class PayEvent extends Event {
	private SupermarketState state;
	private Customer c;
	private EventQueue que;

	public PayEvent(double time, SupermarketState state, EventQueue que, Customer c) {
		this.c = c;
		this.que = que;
		super.time = time;
		this.state = state;
		c.setQueueTime(state.getCurrTime());// Customer can pay, quetime is set.
		state.increaseQueTime(c.getQueueTime()); // This customers quetime is added to total.
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
		if (PickEvent.getQueSize2() > 0) {
			new PayEvent(TimeState.paymentTime(), state, que, PickEvent.getNextCustomer()); // Next customer pays.
		} else {
			state.increaseFreeCashiers(); // Noone in que, Free cashier.
		}

	}

}
