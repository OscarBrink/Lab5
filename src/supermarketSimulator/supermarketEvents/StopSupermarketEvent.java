package supermarketSimulator.supermarketEvents;

import genericSimulator.events.StopEvent;
import supermarketSimulator.supermarketState.SupermarketState;

public class StopSupermarketEvent extends StopEvent {
	//private SupermarketState state;

	public StopSupermarketEvent(double time, SupermarketState state) {
		super.time = time;
		super.state = state;
	}

	@Override
	public String getEventName() {
		return "StopEvent";
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
