package genericSimulator.events;

/**
 * Class provides an abstraction of a generic Event that can be run
 * during. the simulation
 *
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public abstract class Event {
	protected double time;

	/**
	 * @return The time that this Event is executed at.
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @return The string identifier/name of the Event
	 */
	public abstract String getEventName();

	/**
	 * Executes the effect of this Event
	 */
	public abstract void effect();

	/**
	 * @return A String array containing information about the Event.
	 */
	public abstract String[] getPrintInfo();

}
