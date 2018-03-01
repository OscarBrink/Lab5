package supermarketEvents;

import events.Event;
import supermarketState.SupermarketState;

public class PickEvent extends Event {
	private SupermarketState state;

	public PickEvent(int time, SupermarketState state) {
		super.time = time;
		this.state = state;
	}

	@Override
	public void effect() {
		// skapa nytt payEvent m.m.
	}

	@Override
	public String getEventName() {
		return "PickEvent";
	}
}
