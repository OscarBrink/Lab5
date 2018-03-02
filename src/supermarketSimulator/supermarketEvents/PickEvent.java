package supermarketSimulator.supermarketEvents;

import java.util.ArrayList;
import genericSimulator.events.*;
import supermarketSimulator.supermarketState.*;

public class PickEvent extends Event {
	private SupermarketState state;
	private EventQueue que;
	private Customer c;
	private static ArrayList<Customer> cashQue = new ArrayList<Customer>();
	private static int queTot = 0;

	public PickEvent(double time, SupermarketState state, EventQueue que, Customer c) {
		this.c = c;
		super.time = time;
		this.state = state;
		this.que = que;
		que.addEvent(this);// Adds itself to the EventQueue
	}

	/**
	 * Customer is done choosing their stuff. If there are free cashiers the
	 * customer goes to pay. If there are no free cashiers the customer is placed in
	 * a que. Customers queTime is started.
	 */
	@Override
	public void effect() {
		if (state.getFreeCashiers() > 0) {
			// If there are free cashiers. Pay.
			new PayEvent(TimeState.paymentTime(), state, que, c);
			state.decreaseFreeCashiers(); // One less free cashier.
		} else {
			state.addCustomer(c); // Adding customer to que as no cashiers are available.
			c.setStartQueue(state.getCurrTime());

		}
	}

	public double getQueueTime() {
		return c.getQueueTime();
	}

	@Override
	public String getEventName() {
		return "PickEvent";
	}
}
