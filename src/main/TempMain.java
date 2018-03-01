package main;

import supermarketState.GenericState;
import view.GenericView;
import view.SupermarketView;

public class TempMain {

	static GenericView vy;
	
	public static void main(String[] args) {
		vy = new GenericView();
		GenericState test = new GenericState(vy);
		SupermarketView supView = new SupermarketView(vy);
		test.test();

	}

}
