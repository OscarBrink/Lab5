package events;

public abstract class Event {
	protected double time;

	public double getTime() {
		return time;
	}

	public abstract String getEventName();

	public abstract void effect();
}
