package supermarketSimulator.supermarketEvents;

import genericSimulator.events.Event;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketState.SupermarketState;

/**
 * This class is the event that closes the store. Contains the effect() which is
 * the action taken when this event occurs.
 * 
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public class CloseSupermarketEvent extends Event {
	private SupermarketState state;
	private EventQueue que;

	public CloseSupermarketEvent(double time, SupermarketState state, EventQueue que) {
		super.time = time;
		this.state = state;
		this.que = que;

	}

	@Override
	public String getEventName() {
		return "CloseEvent";
	}

	/**
	 * Closes store.
	 */
	@Override
	public void effect() {
		state.setCurrTime(time);
		state.increaseIdleTime();
		state.closeStore();

	}

	@Override
	public String[] getPrintInfo() {
		return new String[] { String.format("%.2f", time), getEventName(), "" };
	}

}
