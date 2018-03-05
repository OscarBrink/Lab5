package supermarketSimulator.supermarketEvents;

import genericSimulator.events.*;
import supermarketSimulator.supermarketState.*;

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
		new ArriveEvent(state.getTimeState().arrivalTime(time), state, eventQueue);

	}

	@Override
	public String[] getPrintInfo() {
		return new String[]{
				String.format("%.2f", time),
				getEventName(),
				""
		};
	}

}