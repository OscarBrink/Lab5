package supermarketSimulator.supermarketEvents;

import genericSimulator.events.*;
import supermarketSimulator.supermarketState.*;

public class StartSupermarketEvent extends StartEvent {
	private SupermarketState state;
	private EventQueue eventQueue;

	public StartSupermarketEvent(int time, SupermarketState state, EventQueue eventQueue) {
		super(time);
		this.state = state;
		this.eventQueue = eventQueue;
	}

	@Override
	public String getEventName() {
		return "StartSupermarketEvent";
	}

	@Override
	public void effect() {
		state.setCurrTime(time);
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
