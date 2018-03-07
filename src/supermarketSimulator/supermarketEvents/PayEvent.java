package supermarketSimulator.supermarketEvents;

import genericSimulator.events.*;
import supermarketSimulator.supermarketState.*;

/**
 * This class is the pay event of a customer. Contains the effect() which is the
 * action taken when this event occurs.
 * 
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */

public class PayEvent extends Event {
	private SupermarketState state;
	private Customer c;
	private EventQueue que;

	public PayEvent(double time, SupermarketState state, EventQueue que, Customer c) {
		this.c = c;
		this.que = que;
		super.time = time;
		this.state = state;
		state.setPaymentTime(time);
		que.addEvent(this); // Adds itself to the EventQueue

	}

	@Override
	public String getEventName() {
		return "PayEvent";
	}

	/**
	 * @return The customer number of the customer in this event.
	 */
	public int getCustomerNumber() {
		return c.getCustomerNumber();
	}

	/**
	 * Customer is done paying and leaves. Current nr of customers decreased. If
	 * there is a que to the cashiers next customer comes.
	 */
	@Override
	public void effect() {
		state.decreaseCurrCustomers();
		state.finishedCustomer();
		if (state.getCashierQueSize() > 0) {
			new PayEvent(state.getTimeState().paymentTime(time), state, que, state.getNextCustomer()); // Next customer
																										// pays.
		} else {
			state.increaseFreeCashiers(); // Noone in que, Free cashier.
		}

	}

	@Override
	public String[] getPrintInfo() {
		return new String[] { String.format("%.2f", time), getEventName(), String.valueOf(c.getCustomerNumber()) };
	}

}
