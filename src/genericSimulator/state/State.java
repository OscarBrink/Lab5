package genericSimulator.state;

import java.util.Observable;

public class State extends Observable { 
	private boolean emergencyBreak = false;
	int currentTime;

	public boolean getEmergencyBreak() {
		return emergencyBreak;
	}

	public void stopSim() {
		emergencyBreak = true;
	}
}
