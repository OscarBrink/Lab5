package genericSimulator.events;

public abstract class StartEvent extends Event {
	public StartEvent(int time) {
		super.time = time;
	}

	@Override
	public String getEventName() {
		return "StartSupermarketEvent";
	}
}
