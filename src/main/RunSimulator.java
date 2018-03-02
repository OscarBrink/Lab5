package main;

import genericSimulator.*;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketEvents.StartEvent;
import supermarketSimulator.supermarketEvents.StopSimEvent;
import supermarketSimulator.supermarketState.SupermarketState;
import supermarketSimulator.supermarketView.SupermarketView;

public class RunSimulator {

	public static void runSimulation(double stopTime) {

		EventQueue eventQueue = new EventQueue();
		SupermarketState state = new SupermarketState(eventQueue);
		eventQueue.addEvent(new StartEvent(0, state));
		eventQueue.addEvent(new StopSimEvent(stopTime));
		SupermarketView view = new SupermarketView(state);

		Simulator simulator = new Simulator(eventQueue, state, view);

		simulator.run();
	}

}
