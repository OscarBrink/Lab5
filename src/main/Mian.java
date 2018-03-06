package main;

public class Mian {

	public static void main(String[] args) {
		RunSimulator runSim = new RunSimulator(
				999.0,
				8,
				2,
				0.6,
				0.9,
				0.35,
				0.6,
				1,
				5
		);
		
		Optimize opti = new Optimize(
			999.0,
			8,
			2,
			0.6,
			0.9,
			0.35,
			0.6
		);
	}
}
