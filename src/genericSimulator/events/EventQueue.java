package genericSimulator.events;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EventQueue {

	private ArrayList<Event> queueList = new ArrayList<>();

	public Event get(int index) {
		return queueList.get(index);
	}


	/**
	 * Adds a new item at the end of the queue.
	 *
	 * @param   event   Event to be added.
	 */
	public void addEvent(Event event) {
		queueList.add(findInsertionPosition(event), event);
	}

	/*
	 * Uses binary search to find position to insert new event.
	 */
	private int findInsertionPosition(Event event) {
		if (queueList.isEmpty()) {
			return 0;
		}

		double eventTime = event.getTime();
		if (eventTime <= queueList.get(0).getTime()) {
			return 0;
		} else if (eventTime >= queueList.get(queueList.size()-1).getTime()) {
			return queueList.size();
		}

		int searchVal = 1;
		int lowestChecked = 0, highestChecked = queueList.size() - 1;

		while ((queueList.get(searchVal - 1).getTime() > eventTime
				|| queueList.get(searchVal).getTime() < eventTime)) {
			searchVal = (lowestChecked + highestChecked ) / 2;

			if (queueList.get(searchVal).getTime() < eventTime) {
				lowestChecked = searchVal + 1;
			} else if (queueList.get(searchVal).getTime() > eventTime) {
				highestChecked = searchVal - 1;
			}
		}
		return searchVal;
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
	public Event getFirst() throws NoSuchElementException {
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

	public ArrayList<Event> getQueueList() {
		return queueList;
	}
}
