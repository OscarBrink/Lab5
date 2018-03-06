package genericSimulator.state;

import java.util.Observable;

public abstract class State extends Observable {
	private boolean emergencyBreak = false;
	int currentTime;

	public boolean getEmergencyBreak() {
		return emergencyBreak;
	}

	public void stopSim() {
		emergencyBreak = true;
	}

	@Override
	public synchronized void setChanged() {
		super.setChanged();
	}

	public abstract void updateState();

}
