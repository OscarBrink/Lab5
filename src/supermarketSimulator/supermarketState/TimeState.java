package supermarketState;

public class TimeState {
	static long tempSeed = 1;
	static double tempLambda = 1.d;
	static double tempLower = 10.d;
	static double tempUpper = 20.d;

	static ExponentialRandomStream exp = new ExponentialRandomStream(tempLambda, tempSeed);
	static UniformRandomStream uni = new UniformRandomStream(tempLower, tempUpper, tempSeed);

	public static double arrivalTime() {
		return exp.next();
	}

	public static double pickTime() {
		return uni.next();
	}

	public static double paymentTime() {
		return uni.next();
	}
}
