package main;

import genericSimulator.*;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketEvents.StartEvent;
import supermarketSimulator.supermarketEvents.StopSimEvent;
import supermarketSimulator.supermarketState.SupermarketState;
import supermarketSimulator.supermarketView.SupermarketView;

public class RunSimulator {

	public RunSimulator(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed) {

		EventQueue eventQueue = new EventQueue();
		SupermarketState state = new SupermarketState(eventQueue);
		state.setMaxCustomers(maxAmountCustomers);

		eventQueue.addEvent(new StartEvent(0, state, eventQueue));
		eventQueue.addEvent(new StopSimEvent(stopTime, state));

		SupermarketView view = new SupermarketView(state);

		Simulator simulator = new Simulator(eventQueue, state, view);

		simulator.run();
	}

}
