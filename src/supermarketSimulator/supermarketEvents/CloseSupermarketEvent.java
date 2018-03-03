package supermarketSimulator.supermarketEvents;

import genericSimulator.events.Event;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketState.SupermarketState;

public class CloseSupermarketEvent extends Event {
	private SupermarketState state;
	private EventQueue que;

	public CloseSupermarketEvent(int time, SupermarketState state, EventQueue que) {
		super.time = time;
		this.state = state;
		this.que = que;
		que.addEvent(this); // Adds itself to the EventQueue
	}

	@Override
	public String getEventName() {
		return "CloseEvent";
	}

	/**
	 * Closes store.
	 */
	@Override
	public void effect() {
		state.closeStore();

	}

	public int getCustomerNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
