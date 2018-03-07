package genericSimulator.events;

/**
 * Class implements a generic Event to start the simulation.
 *
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public abstract class StartEvent extends Event {
	/**
	 * Constructor.
	 *
	 * @param time Time that this Event is to be executed at.
	 */
	public StartEvent(int time) {
		super.time = time;
	}

	@Override
	public String getEventName() {
		return "StartEvent";
	}
}
