package presentation;

import logic.Product;
import logic.ProductType;

public class Main {

	public static void main(String[] args) {
		Product test = new Product(0, "Pølse", "15/09/2018", "500g", ProductType.SAUSAGE, "Nederste hylde");
		Product test2 = new Product(0, "Kylling", "15/02/2018", "2000g", ProductType.CHICKEN, "Midterste hylde");
		Product test3 = new Product(0, "RøgetFisk", "14/01/2019", "100g", ProductType.SMOKEDFISH, "En hylde");
//		test.checkDate();
		test2.checkDate(test2);
		test3.checkDate(test3);
		Menu menu = new Menu();
		menu.start();
		

		
	}
}
