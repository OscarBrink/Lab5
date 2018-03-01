package events;

public class StopSimEvent extends Event {

	@Override
	public String getEventName() {
		return "StopSimEvent";
	}

	@Override
	public void effect() {
		// Stoppa simulationen.

	}

}
