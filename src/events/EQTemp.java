package events;

/*
 * EventQueue with main for testing.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EQTemp {

	private ArrayList<Event> queueList = new ArrayList<>();

	public static void main(String[] args) {
		EQTemp q = new EQTemp();

		for (int i = 1; i < 20; i++) {
			q.queueList.add(new Event(i));
		}
		q.queueList.remove(17);
		q.queueList.remove(13);
		q.queueList.remove(12);
		q.queueList.remove(8);
		q.queueList.remove(3);

		Event ev = new Event(20);
		Event ev2 = new Event(17, true);
		q.addEvent(ev);
		q.addEvent(ev2);
		q.addEvent(new Event(23));
		q.addEvent(new Event(23));
		q.addEvent(new Event(22));
		q.addEvent(new Event(1, true));

		for (Event e : q.queueList) {
			System.out.print(e);
		}
	}

	static class Event {
		private int time;
		private boolean debug = false;
		Event(int time) {
			this.time = time;
		}
		Event(int time, boolean debug) {
			this.debug = debug;
			this.time = time;
		}
		public int getTime() {
			return time;
		}
		public String toString() {
			if (debug) {
				return "(" + time + "d) ";
			}
			return "(" + time + ") ";
		}
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
		if (size() == 0) {
			return 0;
		}

		int eventTime = event.getTime();
		if (eventTime <= queueList.get(0).getTime()) {
			return 0;
		} else if (eventTime >= queueList.get(queueList.size()-1).getTime()) {
			return queueList.size();
		}

		int searchVal = 1;
		int lowestChecked = 0, highestChecked = queueList.size() - 1;

		while ((queueList.get(searchVal - 1).getTime() > eventTime
				|| queueList.get(searchVal).getTime() <= eventTime)) {
			searchVal = (lowestChecked + highestChecked ) / 2;

			if (queueList.get(searchVal).getTime() < eventTime) {
				lowestChecked = searchVal + 1;
			} else if (queueList.get(searchVal).getTime() < eventTime) {
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
