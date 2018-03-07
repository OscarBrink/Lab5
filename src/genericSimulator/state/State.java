package genericSimulator.state;

import java.util.Observable;

/**
 * Class describes the current state of the simulation.
 *
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public abstract class State extends Observable {
	private boolean emergencyBreak = false;

	/**
	 * @return 	boolean that is true if simulation should break execution.
	 * 			false otherwise.
	 */
	public boolean getEmergencyBreak() {
		return emergencyBreak;
	}

	/**
	 * Stops the simulation.
	 */
	public void stopSim() {
		emergencyBreak = true;
	}

	/**
	 * Updates state for next Event in EventQueue.
	 */
	public abstract void updateState();

}
