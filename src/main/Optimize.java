package main;

import java.util.Random;

import genericSimulator.*;
import genericSimulator.events.EventQueue;
import supermarketSimulator.supermarketEvents.CloseSupermarketEvent;
import supermarketSimulator.supermarketEvents.StartSupermarketEvent;
import supermarketSimulator.supermarketEvents.StopSupermarketEvent;
import supermarketSimulator.supermarketState.SupermarketState;
import supermarketSimulator.supermarketView.SupermarketView;

/**
 * This class returns a probable number of minimum cashiers that has to be open to not miss any customers. 
 * The class runs many simulations with different numbers of open cashiers and different random seeds to find this probable number.
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public class Optimize {
	
	SupermarketState state;
	EventQueue eventQueue;
	SupermarketView view;
	Simulator simulator;
	Random randomSource;
	long seed;
	int configurationRuns = 100;
	int minNumberOfMissedCustomers;
	int tempMaxNrOfMinCashiers;
	int maxNrOfMissedCustomersAllowed = 0;
	int currentNumberOfMissedCustomers;
	int openCashiers;
	int openCashiersAtStart = 0;
	int nextSeed;
	int numberOfFailedRuns;
	int numberOfCustomers = 99;

	/**
	 * The constructor.
	 * @param stopTime How long the simulation will run.
	 * @param maxAmountCustomers Number of maximal customers allowed in the store at any time.
	 * @param lambda Intensity of customer arrivals.
	 * @param PickMin Minimum time it takes for customer to pick groceries.
	 * @param PickMax Maximum time it takes for customer to pick groceries.
	 * @param payMin Minimum time spent at the register.
	 * @param payMax Maximum time spent at the register.
	 */
	public Optimize(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed) {
	
		System.out.println(findMinNumberOfOpenCashiersNeeded(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax, seed));
		//System.out.println("Anal kassor som krävs: "+findMinNumberOfOpenCashiersNeededWithRandomSeeds(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax));
	}

	//Runs the simulation once with the given parameters, returns number of missed customers.
	int runSimOnce(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed, int openCashiers) {
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
		eventQueue.addEvent(new CloseSupermarketEvent(20.0, state, eventQueue));
		eventQueue.addEvent(new StopSupermarketEvent(stopTime, state));
		
		view = new SupermarketView(state);

		simulator = new Simulator(eventQueue, state, view);	
		simulator.runWithNoPrint();
		numberOfCustomers = state.getTotalCustomers();
		System.out.println("Antal kunder: "+numberOfCustomers);
		return state.getNumberOfMissedCustomers();
	}
	
	//Runs "runSimOnce" with an increasing amount of open cashiers to find and return how many cashiers have to be open to not miss any customers. 
	int findMinNumberOfOpenCashiersNeeded(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed) {
		System.out.println(seed);
		openCashiers = openCashiersAtStart;
		minNumberOfMissedCustomers = numberOfCustomers;
		currentNumberOfMissedCustomers = numberOfCustomers;
		while(minNumberOfMissedCustomers > maxNrOfMissedCustomersAllowed) {
				if(openCashiers > numberOfCustomers) {
					numberOfFailedRuns++;
					return 0;
				} 
					currentNumberOfMissedCustomers = runSimOnce(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax, seed, openCashiers);
				if(currentNumberOfMissedCustomers < minNumberOfMissedCustomers) {
					minNumberOfMissedCustomers = currentNumberOfMissedCustomers;
					System.out.println("Öppna kassor: "+openCashiers);
					System.out.println("Minsta antalet missade kunder: "+minNumberOfMissedCustomers);
				} 
				
				if(minNumberOfMissedCustomers == maxNrOfMissedCustomersAllowed) {
					System.out.println("här");
					return openCashiers;
				}
				openCashiers++;				
			}
		return openCashiers;
	}
	
	//Runs "findMinNumberOfOpenCashiersNeeded" with different seeds to find a more probable number of minimum cashiers needed.
	int findMinNumberOfOpenCashiersNeededWithRandomSeeds(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax) {
		randomSource = new Random();
		int minOpenCashiers = 0;
		int runsWithoutIncrease = 0;		
		tempMaxNrOfMinCashiers = 0;
		while(runsWithoutIncrease < configurationRuns) {
			nextSeed = randomSource.nextInt();
			minOpenCashiers = findMinNumberOfOpenCashiersNeeded(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax, nextSeed);
			if(minOpenCashiers > tempMaxNrOfMinCashiers) {
				tempMaxNrOfMinCashiers = minOpenCashiers;	
				runsWithoutIncrease = 0;		
			} else {
				runsWithoutIncrease++;
			} 
		} System.out.println("Antal failed: "+numberOfFailedRuns);
		return tempMaxNrOfMinCashiers;	
	} 

}
	

	

