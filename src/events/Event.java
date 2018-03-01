package events;

public abstract class Event {
	private int time;

	public int getTime() {
		return time;
	}
	
	public abstract String getEventName();
}
