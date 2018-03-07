package genericSimulator.view;

import java.util.Observable;
import java.util.Observer;

/**
 * Implements a generic view to provide output for the user of the
 * simulation.
 */
public abstract class View implements Observer{

	@Override
	public abstract void update(Observable arg0, Object arg1);

}
