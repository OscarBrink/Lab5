package supermarketSimulator.supermarketState;

public class CustomerFactory {

	private int amount = 0;

	public CustomerFactory() {

	}

	public Customer newCustomer() {
		return new Customer(++amount);
	}
}
