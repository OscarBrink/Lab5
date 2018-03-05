package supermarketSimulator.supermarketEvents;

import genericSimulator.events.Event;
import supermarketSimulator.supermarketState.SupermarketState;

public class StopSimEvent extends Event {
	private SupermarketState state;

	public StopSimEvent(double time, SupermarketState state) {
		super.time = time;
		this.state = state;
	}

	@Override
	public String getEventName() {
		return "StopSimEvent";
	}

	@Override
	public void effect() {
		state.stopSim();
	}

	@Override
	public String[] getPrintInfo() {
		return new String[]{
				String.valueOf(time),
				getEventName(),
				""
		};
	}

}
