package presentation;

import logic.Product;
import logic.ProductType;

public class Main {

	public static void main(String[] args) {
		Product test = new Product(0, "Pølse", "25/10/2018", "500g", ProductType.SAUSAGE, "Nederste hylde");
		Product test2 = new Product(0, "Kylling", "15/02/2018", "2000g", ProductType.CHICKEN, "Midterste hylde");
		test.checkDate(test);
		test2.checkDate(test2);
		Menu menu = new Menu();
		menu.start();
		

		
	}
}
