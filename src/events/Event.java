package events;

public abstract class Event {
	protected int time;

	public int getTime() {
		return time;
	}

	public abstract String getEventName();

	public abstract void effect();
}
