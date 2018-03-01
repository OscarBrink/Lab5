package events;

public class CloseSupermarketEvent extends Event {

	@Override
	public String getEventName() {
		return "CloseSupermarketEvent";
	}

	@Override
	public void effect() {
		// ändra Supermarketstate == closed m.m.

	}

}
