package supermarketEvents;

import events.Event;
import supermarketState.SupermarketState;

public class CloseSupermarketEvent extends Event {
	private SupermarketState state;

	public CloseSupermarketEvent(int time, SupermarketState states) {
		super.time = time;
		this.state = state;
	}

	@Override
	public String getEventName() {
		return "CloseSupermarketEvent";
	}

	@Override
	public void effect() {
		// ändra Supermarketstate == closed m.m.

	}

}
