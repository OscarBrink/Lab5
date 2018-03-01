package supermarketSimulator.supermarketEvents;

import java.util.ArrayList;
import events.*;
import genericSimulator.events.Event;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketState.Customer;
import supermarketSimulator.supermarketState.SupermarketState;
import supermarketSimulator.supermarketState.TimeState;
import supermarketState.*;

public class PickEvent extends Event {
	private SupermarketState state;
	private EventQueue que;
	private Customer c;
	private static ArrayList<Customer> cashQue = new ArrayList<Customer>();
	private static int queTot;

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
			cashQue.add(c); // Adding customer to que as no cashiers are available.
			c.setStartQueue(state.getCurrTime());
			queTot++;
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

	/**
	 * returns the amount of customers in cashierQue.
	 */
	public int getQueSize() {
		return cashQue.size();
	}
	
	public int getCustomerNumber(){
		return c.getCustomerNumber();
	}
	
	/**
	 * returns the total amount of customers that has been in cashierQue.
	 */
	public int getTotQueSize() {
		return queTot;
	}
	
	/**
	 * @return queString a string representation of the cashQue.
	 */
	public String queToString(){
		String queString = "[";
		for(Customer c: cashQue)
			queString+= c.getCustomerNumber() + ", ";
		return queString + "]";
	}
	
	public double getQueueTime(){
		return c.getQueueTime();
	}

	@Override
	public String getEventName() {
		return "PickEvent";
	}
}
