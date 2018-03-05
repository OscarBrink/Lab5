package supermarketSimulator.supermarketEvents;

import genericSimulator.events.*;
import supermarketSimulator.supermarketState.*;

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

	/**
	 * Customer is done choosing their stuff. If there are free cashiers the
	 * customer goes to pay. If there are no free cashiers the customer is placed in
	 * a que. Customers queTime is started.
	 */
	@Override
	public void effect() {
		state.setCurrTime(time);
		state.increaseIdleTime();
		state.increaseQueTime();
		if (state.getFreeCashiers() > 0) {
			// If there are free cashiers. Pay.
			new PayEvent(state.getTimeState().paymentTime(time), state, que, c);
			state.decreaseFreeCashiers(); // One less free cashier.
		} else {
			state.addCustomer(c); // Adding customer to que as no cashiers are available.
//			c.setStartQueue(state.getCurrTime());

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

	public double getQueueTime() {
		return c.getQueueTime();
	}
	
	
	public int getCustomerNumber() {
		return c.getCustomerNumber();
	}

	@Override
	public String getEventName() {
		return "PickEvent";
	}
}
