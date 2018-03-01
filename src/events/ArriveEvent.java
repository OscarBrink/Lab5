package events;

public class ArriveEvent extends Event {

	public ArriveEvent(int time) {
		// Customer c = createCustomer(); Skapa kund i kundfabriken.
		super.time = time;

	}

	@Override
	public String getEventName() {
		return "ArriveEvent";
	}

	@Override
	public void effect() {
		/*
		 * if(butiken är öppen){ if(supermarketstate.getCurrentCustomers() <
		 * supermarketState.getMaxCustomers()) {
		 * 
		 * } else { supermarketState.missedCustomers ++; } }
		 */
	}
}
