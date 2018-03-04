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

	public TimeState(long seed, double lambda, double pickMin, double pickMax, double payMin, double payMax) {
		arrivalTimeGenerator = new ExponentialRandomStream(lambda, seed);
		pickTimeGenerator = new UniformRandomStream(pickMin, pickMax, seed);
		payTimeGenerator = new UniformRandomStream(payMin, payMax);
	}

	public double arrivalTime() {
		return arrivalTimeGenerator.next();
	}

	public double pickTime() {
		return pickTimeGenerator.next();
	}

	public double paymentTime() {
		return payTimeGenerator.next();
	}
}
