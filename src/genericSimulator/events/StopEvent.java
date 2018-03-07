package genericSimulator.events;

import genericSimulator.state.State;

/**
 * Class implements a generic Event to stop the simulation.
 *
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public abstract class StopEvent extends Event {

	protected State state;

	@Override
	public String getEventName() {
		return "StopEvent";
	}

	@Override
	public void effect() {
		state.stopSim();
	}

}
