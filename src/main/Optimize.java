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
	static int openCashiers;
	static int numberOfOptimizations= 10;
	boolean isRunning;
	SupermarketState state;
	

	public Optimize(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed, int openCashiers) {
			
		//run(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax, seed, openCashiers);
		int i = 0;
		while(i < numberOfOptimizations) {
			if (isRunning) {
			} else {
				openCashiers = optimizeCashiers();
				run(stopTime, maxAmountCustomers, lambda, PickMin, PickMax, payMin, payMax, seed, openCashiers);
				i++;
			}
		}
	}
	
	void run(double stopTime, int maxAmountCustomers, double lambda, double PickMin, double PickMax, double payMin, double payMax, long seed, int openCashiers) {
		RunSimulator runSim = new RunSimulator(
			stopTime,
			maxAmountCustomers,
			lambda,
			PickMin,
			PickMax,
			payMin,
			payMax,
			seed,
			openCashiers
			
	);	isRunning = true;
		while(!runSim.getState().getEmergencyBreak()) {}
		isRunning = false;
	}



		static int optimizeCashiers() {
			return 3;
		
	}
}
	
	
	

