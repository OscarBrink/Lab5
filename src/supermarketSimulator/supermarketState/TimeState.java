package supermarketSimulator.supermarketState;

public class TimeState {/*
	static long tempSeed = 1;
	static double tempLambda = 1.0;
	static double tempLower = 0.5;
	static double tempUpper = 1.0;
*/
	private long seed;
	private double 	lambda,
			pickLower,
			pickUpper,
			payLower,
			payUpper;

	private ExponentialRandomStream arrivalTimeGenerator;
	private UniformRandomStream pickTimeGenerator;
	private UniformRandomStream payTimeGenerator;

	/*
	static ExponentialRandomStream exp = new ExponentialRandomStream(tempLambda, tempSeed);
	static UniformRandomStream uni = new UniformRandomStream(tempLower, tempUpper, tempSeed);
	*/

	public TimeState(long seed, double lambda, double pickLower, double pickUpper, double payLower, double payUpper) {
		arrivalTimeGenerator = new ExponentialRandomStream(lambda, seed);
		pickTimeGenerator = new UniformRandomStream(pickLower, pickUpper, seed);
		payTimeGenerator = new UniformRandomStream(payLower, payUpper);
	}

	public double arrivalTime(double currentTime) {
		return currentTime + arrivalTimeGenerator.next();
	}

	public double pickTime(double currentTime) {
		return currentTime + pickTimeGenerator.next();
	}

	public double paymentTime(double currentTime) {
		return currentTime + payTimeGenerator.next();
	}
}
