package main;

import genericSimulator.*;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketEvents.CloseSupermarketEvent;
import supermarketSimulator.supermarketEvents.StartEvent;
import supermarketSimulator.supermarketEvents.StopSimEvent;
import supermarketSimulator.supermarketState.SupermarketState;
import supermarketSimulator.supermarketView.SupermarketView;

public class RunSimulator {
	
	SupermarketState state;

	public RunSimulator(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed, int openCashiers) {

		EventQueue eventQueue = new EventQueue();
		state = new SupermarketState(eventQueue);
		state.setMaxCustomers(maxAmountCustomers);
		state.setOpenCashiers(openCashiers);
		state.setLambda(lambda);
		state.setPickMin(PickMin);
		state.setPickMax(PickMax);
		state.setPayMin(payMin);
		state.setPayMax(payMax);
		state.setSeed(seed);

		state.initTimeState();

		eventQueue.addEvent(new StartEvent(0, state, eventQueue));
		eventQueue.addEvent(new CloseSupermarketEvent(10.0, state, eventQueue));
		eventQueue.addEvent(new StopSimEvent(stopTime, state));

		SupermarketView view = new SupermarketView(state);

		Simulator simulator = new Simulator(eventQueue, state, view);

		simulator.run();
	}
	
	SupermarketState getState() {
		return this.state;
	}

}
