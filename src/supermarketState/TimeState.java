package supermarketState;


public class TimeState {
	long tempSeed = 1;
	double tempLambda = 1.d;
	double tempLower = 10.d;
	double tempUpper = 20.d;

	ExponentialRandomStream exp = new ExponentialRandomStream(tempLambda, tempSeed);
	UniformRandomStream uni = new UniformRandomStream(tempLower, tempUpper, tempSeed);
	
	public double arrivalTime() {
		return exp.next();
	}
	
	public double pickTime() {
		return uni.next();
	}
	
	public double paymentTime() {
		return uni.next();
	}
}
