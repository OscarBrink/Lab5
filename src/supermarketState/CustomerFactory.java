package supermarketState;

public class CustomerFactory {

	static int amount = 0;

	public Customer newCustomer() {
		return new Customer(++amount);
	}
}
