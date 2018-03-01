package view;

import java.util.Observable;
import java.util.Observer;

public abstract class View implements Observer{

	public abstract void update(Observable arg0, Object arg1);

}
