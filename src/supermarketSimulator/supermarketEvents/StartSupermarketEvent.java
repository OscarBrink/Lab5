package supermarketSimulator.supermarketEvents;

import genericSimulator.events.*;
import supermarketSimulator.supermarketState.*;

/**
 * This class is the start event of the simulation. Contains the effect() which
 * is the action taken when this event occurs.
 * 
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
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
		return "StartEvent";
	}

	/**
	 * The simulation has started, an arrive event is created.
	 */
	@Override
	public void effect() {
		new ArriveEvent(state.getTimeState().arrivalTime(time), state, eventQueue);
	}

	@Override
	public String[] getPrintInfo() {
		return new String[] { String.format("%.2f", time), getEventName(), "" };
	}

}
