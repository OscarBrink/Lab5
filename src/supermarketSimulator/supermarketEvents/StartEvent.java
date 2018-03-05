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
		state.setCurrTime(time);
		new ArriveEvent(TimeState.arrivalTime(), state, eventQueue);

	}

	public int getCustomerNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}