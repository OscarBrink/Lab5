package main;

import genericSimulator.Simulator;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketEvents.StartEvent;
import supermarketSimulator.supermarketEvents.StopSimEvent;
import supermarketSimulator.supermarketState.SupermarketState;
import supermarketSimulator.supermarketView.SupermarketView;

public class Optimize {
	static double stopTime;
	static int maxAmountCustomers;
	static double lambda;
	static double PickMin;
	static double PickMax;
	static double payMin;
	static double payMax;
	static long seed;
	static int numberOfCashiers;
	static int numberOfOptimizations= 10;
	
	static RunSimulator runSim = new RunSimulator(
			stopTime,
			maxAmountCustomers,
			lambda,
			PickMin,
			PickMax,
			payMin,
			payMax,
			seed,
			numberOfCashiers
	);
	
	
	public Optimize(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed) {

		EventQueue eventQueue = new EventQueue();
		SupermarketState state = new SupermarketState(eventQueue);
		state.setMaxCustomers(maxAmountCustomers);

		eventQueue.addEvent(new StartEvent(0, state, eventQueue));
		eventQueue.addEvent(new StopSimEvent(stopTime, state));

		SupermarketView view = new SupermarketView(state);

		Simulator simulator = new Simulator(eventQueue, state, view);

		
		int i = 0;
		while(i < numberOfOptimizations) {
			if (!state.getEmergencyBreak()) {
			} else {
				numberOfCashiers = optimizeCashiers();
				runSim = new RunSimulator(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax, seed, numberOfCashiers);
			}
		}
	}
	
void run(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed, int numberOfCashiers) {
	

	
}




static int optimizeCashiers() {
	while(true) {
		
	}
}
	
	
	
}
