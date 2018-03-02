package supermarketSimulator.supermarketEvents;

import genericSimulator.events.*;
import supermarketSimulator.supermarketState.*;

public class StartEvent extends Event {
	private SupermarketState state;
	private EventQueue que;

	public StartEvent(int time, SupermarketState state) {
		this.que = que;
		super.time = time;
		this.state = state;

	}

	@Override
	public String getEventName() {
		return "StartEvent";
	}

	@Override
	public void effect() {
		new ArriveEvent(TimeState.arrivalTime(), state, que);
		// sätt state.open till true?
	}

}
