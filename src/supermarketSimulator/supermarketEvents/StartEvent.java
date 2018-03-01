package supermarketEvents;

import genericSimulator.events.Event;
import supermarketState.SupermarketState;

public class StartEvent extends Event {
	private SupermarketState state;

	public StartEvent(int time, SupermarketState state) {
		super.time = time;
		this.state = state;

	}

	@Override
	public String getEventName() {
		return "StartEvent";
	}

	@Override
	public void effect() {
		// Öppna butik m.m.

	}

}
