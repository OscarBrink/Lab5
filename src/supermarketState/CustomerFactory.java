package supermarketState;

public class CustomerFactory {

	static int amount = 0;

	public static Customer newCustomer() {
		return new Customer(++amount);
	}
}
