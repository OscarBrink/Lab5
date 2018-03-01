package supermarketEvents;

import java.util.ArrayList;
import events.*;
import supermarketState.*;

public class PickEvent extends Event {
	private SupermarketState state;
	private EventQueue que;
	private Customer c;
	private static ArrayList<Customer> cashQue = new ArrayList<Customer>();

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
			state.decreaseFreeCashiers(); // One less free cashier.
		} else {
			cashQue.add(c); // Adding customer to que as no cashiers are available.
			c.setStartQueue(state.getCurrTime());
		}
	}

	/**
	 * Gets the customer who is first in the cashierQue. Customer leaves the que.
	 */
	public static Customer getNextCustomer() {
		Customer next = cashQue.get(0);
		cashQue.remove(0);
		return next;

	}

	public static int getQueSize() {
		return cashQue.size();
	}

	@Override
	public String getEventName() {
		return "PickEvent";
	}
}
