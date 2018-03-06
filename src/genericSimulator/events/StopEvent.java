package genericSimulator.events;

import genericSimulator.state.State;

public class StopEvent extends Event {

	protected State state;

	@Override
	public String getEventName() {
		return "StopEvent";
	}

	@Override
	public void effect() {
		state.stopSim();
	}

	@Override
	public String[] getPrintInfo() {
		return new String[0];
	}

}
