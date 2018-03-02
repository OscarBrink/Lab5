package supermarketSimulator.supermarketEvents;

import genericSimulator.events.Event;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketState.SupermarketState;
import supermarketSimulator.supermarketState.TimeState;

public class StartEvent extends Event {
	private SupermarketState state;
	private EventQueue eventQueue;

	public StartEvent(int time, SupermarketState state, EventQueue eventQueue) {
		super.time = time;
		this.state = state;
		this.eventQueue = eventQueue;

	}

	@Override
	public String getEventName() {
		return "StartEvent";
	}

	@Override
	public void effect() {
		new ArriveEvent(TimeState.arrivalTime(), state, eventQueue);

	}

}
