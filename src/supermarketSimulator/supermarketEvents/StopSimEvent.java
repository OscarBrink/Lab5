package supermarketSimulator.supermarketEvents;

import genericSimulator.events.Event;
import supermarketSimulator.supermarketState.SupermarketState;

public class StopSimEvent extends Event {
	private SupermarketState state;

	public StopSimEvent(int time, SupermarketState state) {
		super.time = time;
		this.state = state;
	}

	@Override
	public String getEventName() {
		return "StopSimEvent";
	}

	@Override
	public void effect() {
		// Stoppa simulationen.

	}

}
