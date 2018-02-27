package events;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EventQueue {

	private ArrayList<Event> queueList = new ArrayList<>();

	/**
	 * Adds a new item at the end of the queue.
	 *
	 * @param   event   Event to be added.
	 */
	public void addEvent(Event event) {
		queueList.add(event);
	}

	/**
	 * Removes the first object from the queue.
	 */
	public void removeFirst() throws NoSuchElementException {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		queueList.remove(0);
	}

	/**
	 * Gets the first object in the queue.
	 */
	public Object getFirst() throws NoSuchElementException {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		return queueList.get(0);
	}

	/**
	 * Gets the current size of the queue.
	 *
	 * @return  int Current size.
	 */
	public int size() {
		return queueList.size();
	}

}
