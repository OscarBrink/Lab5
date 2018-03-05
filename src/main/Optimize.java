package main;

import genericSimulator.*;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketEvents.StartEvent;
import supermarketSimulator.supermarketEvents.StopSimEvent;
import supermarketSimulator.supermarketState.SupermarketState;
import supermarketSimulator.supermarketView.SupermarketView;

public class Optimize {
	
	SupermarketState state;
	Simulator simulator;
	boolean isRunning = false;
	int numberOfOptimizations = 10;
	int decreaseLimit = 10;

	public Optimize(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed, int openCashiers) {

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

		eventQueue.addEvent(new StartEvent(0, state, eventQueue));
		eventQueue.addEvent(new StopSimEvent(stopTime, state));

		SupermarketView view = new SupermarketView(state);

		simulator = new Simulator(eventQueue, state, view);
		
		System.out.println(state.getEmergencyBreak());
		//runOpti(openCashiers);
		System.out.println(state.getEmergencyBreak());
		
		int i = 0;
		while(i < numberOfOptimizations) {
			//if(isRunning) {
			//} else {
				runOpti(openCashiers);
				System.out.println(i);
				i++;
			//}
		}
		
	}
	
	void runOpti(int openCashiers) {
		isRunning = true;
		simulator.runWithNoPrint();
		while(true) {
			if(state.getEmergencyBreak()) {
				break;
			}
		}
		System.out.println(state.getNumberOfMissedCustomers());
		isRunning = false;
		return;
	}
	
	void optimizeCashiers() {
		int numberOfRunsWithoutDecreasing = 0;
		while(numberOfRunsWithoutDecreasing)
	}

}
