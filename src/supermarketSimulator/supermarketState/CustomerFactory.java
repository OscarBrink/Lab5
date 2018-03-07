package supermarketSimulator.supermarketState;

/**
 * Class represents a customer of the store.
 *
 * @author Josefine Bexelius
 * @author Oscar Brink
 * @author Lisa Jonsson
 * @author Marc Nilsson
 */
public class CustomerFactory {

	private int amount = -1;

	public CustomerFactory() {

	}

	public Customer newCustomer() {
		return new Customer(++amount);
	}
}
