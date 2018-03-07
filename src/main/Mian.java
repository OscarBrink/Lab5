package main;

public class Mian {

	public static void main(String[] args) {
//		RunSimulator runSim = new RunSimulator(
//		        999.0,
//                7,
//                3.0,
//                0.6,
//                0.9,
//                0.35,
//                0.6,
//                13,
//                2
//		);
//		
//		Optimize opti = new Optimize(
//			999.0,
//			7,
//			3.0,
//			0.6,
//			0.9,
//			0.35,
//			0.6,
//			13
//		);

		// RunSimulator runSim = new RunSimulator(
		// 999.0,
		// 7,
		// 3.0,
		// 0.6,
		// 0.9,
		// 0.35,
		// 0.6,
		// 13,
		// 3
		// );

		Optimize opti = new Optimize(999.0, 100, 50.0, 0.45, 0.65, 2.0, 3.0, 42);
	}
}
