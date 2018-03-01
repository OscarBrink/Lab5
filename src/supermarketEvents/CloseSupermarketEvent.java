package supermarketEvents;

import events.*;
import supermarketState.SupermarketState;

public class CloseSupermarketEvent extends Event {
	private SupermarketState state;
	private EventQueue que;

	public CloseSupermarketEvent(int time, SupermarketState states, EventQueue que) {
		super.time = time;
		this.state = state;
		this.que = que;
		que.addEvent(this); // Adds itself to the EventQueue
	}

	@Override
	public String getEventName() {
		return "CloseSupermarketEvent";
	}

	/**
	 * Closes store.
	 */
	@Override
	public void effect() {
		state.closeStore();

	}

}
