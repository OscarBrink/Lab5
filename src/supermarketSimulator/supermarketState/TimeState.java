package supermarketSimulator.supermarketState;

/**
 * Class contains methods to generate eventtimes.
 * 
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public class TimeState {

	private ExponentialRandomStream arrivalTimeGenerator;
	private UniformRandomStream pickTimeGenerator;
	private UniformRandomStream payTimeGenerator;

	public TimeState(long seed, double lambda, double pickLower, double pickUpper, double payLower, double payUpper) {
		arrivalTimeGenerator = new ExponentialRandomStream(lambda, seed);
		pickTimeGenerator = new UniformRandomStream(pickLower, pickUpper, seed);
		payTimeGenerator = new UniformRandomStream(payLower, payUpper, seed);
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
