package genericSimulator;

import genericSimulator.events.EventQueue;
import genericSimulator.state.State;
import genericSimulator.view.View;

/**
 * Class implements a DEDS-Simulator that can be run with the use of
 * specific States and Views.
 *
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public class Simulator {

	private EventQueue eventQueue;
	private State state;
	private View view;

	/**
	 * Constructor.
	 *
	 * @param eventQueue	EventQueue that holds the Events.
	 * @param state			State that tracks the current state.
	 * @param view			View that provides output to user.
	 */
	public Simulator(EventQueue eventQueue, State state, View view) {
		this.eventQueue = eventQueue;
		this.state = state;
		this.view = view;

		state.addObserver(view);
	}

	/**
	 * Runs the simulation.
	 */
	public void run() {
		while (!state.getEmergencyBreak()) {
			state.updateState();
			/*
			state.setChanged();
			state.notifyObservers();
			*/
			eventQueue.getFirst().effect();
			eventQueue.removeFirst();
		}
	}

	/**
	 * Runs the simulation without updating the View.
	 */
	public void runWithNoPrint() {
		while (!state.getEmergencyBreak()) {
			eventQueue.getFirst().effect();
			eventQueue.removeFirst();
		}
	}
}
