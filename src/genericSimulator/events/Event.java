package genericSimulator.events;

/**
 * Class implements an abstract Event that can be run during a simulation.
 */
public abstract class Event {
	protected double time;

	/**
	 * @return double gets the time that the
	 * Event is executed in the simulation.
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @return String name of the Event.
	 */
	public abstract String getEventName();

	/**
	 * Executes the effect of the Event
	 */
	public abstract void effect();

	/**
	 * Gets a String array containing information about the Event.
	 * @return returns an array with info about the Event.
	 */
	public abstract String[] getPrintInfo();

}
