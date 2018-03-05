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
	EventQueue eventQueue;
	SupermarketView view;
	Simulator simulator;
	boolean isRunning = false;
	int configurationRuns = 10;
	int tempMaxMin;
	int maxMin = 99;
	int maxMissedThreshold = 0;
	int currentNumberOfMissedCustomers;
	int maxNumberOfCashiers;
	int openCashiers;
	int openCashiersStart = 1;

	
	public Optimize(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed) {
	
		optimizeCashiers(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax, seed);


		

	}
	
	
	void test(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed) {


	

		//simulator = new Simulator(eventQueue, state, view);		
		int i = 0;
		while(i < 10) {
			eventQueue = new EventQueue();
			state = new SupermarketState(eventQueue);
			state.setMaxCustomers(maxAmountCustomers);
			state.setLambda(lambda);
			state.setPickMin(PickMin);
			state.setPickMax(PickMax);
			state.setPayMin(payMin);
			state.setPayMax(payMax);
			state.setSeed(seed);
			state.initTimeState();
			state.setOpenCashiers(i);
			eventQueue.addEvent(new StartEvent(0, state, eventQueue));
			eventQueue.addEvent(new CloseSupermarketEvent(10.0, state, eventQueue));
			eventQueue.addEvent(new StopSimEvent(stopTime, state));

			view = new SupermarketView(state);

			simulator = new Simulator(eventQueue, state, view);	
			simulator.runWithNoPrint();
			currentNumberOfMissedCustomers = this.state.getNumberOfMissedCustomers();
			System.out.println(currentNumberOfMissedCustomers);
			i++;
		}
	}

	
	int runOpti(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed,int openCashiers) {
		eventQueue = new EventQueue();
		state = new SupermarketState(eventQueue);
		state.setMaxCustomers(maxAmountCustomers);
		state.setLambda(lambda);
		state.setPickMin(PickMin);
		state.setPickMax(PickMax);
		state.setPayMin(payMin);
		state.setPayMax(payMax);
		state.setSeed(seed);
		state.initTimeState();
		state.setOpenCashiers(openCashiers);
		eventQueue.addEvent(new StartEvent(0, state, eventQueue));
		eventQueue.addEvent(new CloseSupermarketEvent(10.0, state, eventQueue));
		eventQueue.addEvent(new StopSimEvent(stopTime, state));

		view = new SupermarketView(state);

		simulator = new Simulator(eventQueue, state, view);	
		simulator.runWithNoPrint();

		return state.getNumberOfMissedCustomers();
	}
	
	void optimizeCashiers(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed) {
		int runsWithoutADecrease = 0;
		openCashiers = openCashiersStart;
		while(maxMin > 0) {
				tempMaxMin = 99;
				runsWithoutADecrease = 0;
				while(runsWithoutADecrease < configurationRuns) {	
					currentNumberOfMissedCustomers = runOpti(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax, seed, openCashiers);
					if(currentNumberOfMissedCustomers < tempMaxMin) {
						tempMaxMin = currentNumberOfMissedCustomers;	
						runsWithoutADecrease = 0;				
					} else {
						runsWithoutADecrease++;
					} 
				}
				maxMin = tempMaxMin;
				System.out.println("Kassor: "+openCashiers);
				System.out.println("Maximalt antal missade kunder: "+maxMin);
				openCashiers++;
				
				
					
			}
	}

}
