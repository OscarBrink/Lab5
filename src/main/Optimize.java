package main;

import genericSimulator.*;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketEvents.CloseSupermarketEvent;
import supermarketSimulator.supermarketEvents.StartEvent;
import supermarketSimulator.supermarketEvents.StopSimEvent;
import supermarketSimulator.supermarketState.SupermarketState;
import supermarketSimulator.supermarketView.SupermarketView;

public class Optimize {
	
	SupermarketState state;
	Simulator simulator;
	boolean isRunning = false;
	int configurationRuns = 10;
	int maxMin = 99;
	int maxMissedThreshold = 0;
	int currentNumberOfMissedCustomers;
	int maxNumberOfCashiers;
	int tempCashiers;

	public Optimize(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed, int openCashiers) {

		tempCashiers = openCashiers;
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

		simulator = new Simulator(eventQueue, state, view);		
		
		optimizeCashiers(tempCashiers);

	}
	
	
	
	int runOpti(int tempCashiers) {
		state.setOpenCashiers(tempCashiers);
		simulator.runWithNoPrint();
		System.out.println(this.state.getNumberOfMissedCustomers());	
		return state.getNumberOfMissedCustomers();
	}
	
	int optimizeCashiers(int tempCashiers) {
			int runsWithoutADecrease = 0;

			while(maxMin > 0) {
				while(runsWithoutADecrease < configurationRuns) {		
				currentNumberOfMissedCustomers = runOpti(tempCashiers);
				System.out.println(currentNumberOfMissedCustomers);
					if(currentNumberOfMissedCustomers < maxMin) {
						maxMin = currentNumberOfMissedCustomers;			
					} else if(currentNumberOfMissedCustomers == maxMin) {
						runsWithoutADecrease++;
					}
				} 	
			} return maxMin;
	}

}
