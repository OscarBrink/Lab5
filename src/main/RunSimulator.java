package main;

import genericSimulator.*;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketEvents.CloseSupermarketEvent;
import supermarketSimulator.supermarketEvents.StartSupermarketEvent;
import supermarketSimulator.supermarketEvents.StopSupermarketEvent;
import supermarketSimulator.supermarketState.SupermarketState;
import supermarketSimulator.supermarketView.SupermarketView;

public class RunSimulator {
	
	SupermarketState state;

	public RunSimulator(double stopTime, double closeTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed, int openCashiers) {

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

		eventQueue.addEvent(new StartSupermarketEvent(0, state, eventQueue));
		eventQueue.addEvent(new CloseSupermarketEvent(closeTime, state, eventQueue));
		eventQueue.addEvent(new StopSupermarketEvent(stopTime, state));

		SupermarketView view = new SupermarketView(state);

		Simulator simulator = new Simulator(eventQueue, state, view);

		simulator.run();
	}
	
	

}
