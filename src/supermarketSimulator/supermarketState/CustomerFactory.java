package supermarketSimulator.supermarketState;

public class CustomerFactory {

	private int amount = -1;

	public CustomerFactory() {

	}

	public Customer newCustomer() {
		return new Customer(++amount);
	}
}
