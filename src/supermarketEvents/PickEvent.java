package supermarketEvents;

import events.*;
import supermarketState.*;

public class PickEvent extends Event {
	private SupermarketState state;
	private EventQueue que;
	private Customer c;

	public PickEvent(int time, SupermarketState state, EventQueue que, Customer c) {
		this.c = c;
		super.time = time;
		this.state = state;
		this.que = que;
		que.addEvent(this);// Adds itself to the EventQueue
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
