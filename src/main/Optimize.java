package main;

import java.util.Random;

import genericSimulator.*;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketEvents.CloseSupermarketEvent;
import supermarketSimulator.supermarketEvents.StartSupermarketEvent;
import supermarketSimulator.supermarketEvents.StopSupermarketEvent;
import supermarketSimulator.supermarketState.SupermarketState;
import supermarketSimulator.supermarketView.SupermarketView;

public class Optimize {
	
	SupermarketState state;
	EventQueue eventQueue;
	SupermarketView view;
	Simulator simulator;
	Random randomSource;
	boolean isRunning = false;
	int configurationRuns = 10;
	int tempMaxMin;
	int tempMin;
	int maxMin;
	int maxMissedThreshold = 0;
	int currentNumberOfMissedCustomers;
	int maxNumberOfCashiers;
	int openCashiers;
	int openCashiersStart = 1;
	int nextSeed;
	int numberOfFailedRuns;
	int numberOfCustomers;
	
	public Optimize(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax) {
	
		
		//optimizeCashiers(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax, seed);
		randomSeed(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax);
	}

	int runOpti(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed, int openCashiers) {
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
		eventQueue.addEvent(new StartSupermarketEvent(0, state, eventQueue));
		eventQueue.addEvent(new CloseSupermarketEvent(10.0, state, eventQueue));
		eventQueue.addEvent(new StopSupermarketEvent(stopTime, state));
		

		view = new SupermarketView(state);

		simulator = new Simulator(eventQueue, state, view);	
		simulator.runWithNoPrint();
		return state.getNumberOfMissedCustomers();
	}
	
	int optimizeCashiers(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed) {
		numberOfCustomers = maxAmountCustomers;
		openCashiers = 0;
		maxMin = numberOfCustomers;
		while(maxMin > 0) {
				if(openCashiers > numberOfCustomers) {
					numberOfFailedRuns++;
					return 0;
				}
				tempMaxMin = numberOfCustomers;
					currentNumberOfMissedCustomers = runOpti(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax, seed, openCashiers);
					if(currentNumberOfMissedCustomers < tempMaxMin) {
						tempMaxMin = currentNumberOfMissedCustomers;		
					} 
					maxMin = tempMaxMin;
					System.out.println("Kunder: "+numberOfCustomers);
					System.out.println("Kassor: "+openCashiers);
					System.out.println("Maximalt antal missade kunder: "+maxMin);
					if (maxMin == maxMissedThreshold) {
						return openCashiers;
					}
				openCashiers++;		
			}
		System.out.println("openCashiers: "+openCashiers);
		return openCashiers;
	}
	
	void randomSeed(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax) {
		randomSource = new Random();
		
		int minOpenCashiers = 0;
		int runsWithoutIncrease = 0;		
		tempMin = 0;
		while(runsWithoutIncrease < configurationRuns) {
			nextSeed = randomSource.nextInt();
		System.out.println(nextSeed);
			minOpenCashiers = optimizeCashiers(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax, nextSeed);
			System.out.println("minOpenCashiers: "+minOpenCashiers);
			if(minOpenCashiers > tempMin) {
				tempMin = minOpenCashiers;	
				runsWithoutIncrease = 0;		
			} else {
				runsWithoutIncrease++;
			} 
		}
		System.out.println("Högsta minimiantal kassor: "+tempMin);
		System.out.println("Failed runs: "+numberOfFailedRuns);
		}
		
		

}
	

	

