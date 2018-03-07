package supermarketSimulator.supermarketEvents;

import genericSimulator.events.StopEvent;
import supermarketSimulator.supermarketState.SupermarketState;

/**
 * This class is the event that stops the simulation. Inherits the effect()
 * which is the action taken when this event occurs.
 * 
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public class StopSupermarketEvent extends StopEvent {
	public StopSupermarketEvent(double time, SupermarketState state) {
		super.time = time;
		super.state = state;
	}

	@Override
	public String[] getPrintInfo() {
		return new String[] { String.valueOf(time), getEventName(), "" };
	}

}
