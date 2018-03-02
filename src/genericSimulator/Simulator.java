package genericSimulator;

import genericSimulator.events.EventQueue;
import genericSimulator.state.State;
import genericSimulator.view.View;
import supermarketSimulator.supermarketEvents.StartEvent;

public class Simulator {

	private EventQueue eventQueue;
	private State state;
	private View view;


	public Simulator(EventQueue eventQueue, State state, View view) {
		this.eventQueue = eventQueue;
		this.state = state;
		this.view = view;
	}

	public void run() {
		while (!state.getEmergencyBreak()) {
			// TODO
		}
	}
}
