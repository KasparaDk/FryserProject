package presentation;

import java.time.LocalDate;

import java.util.List;

import logic.DatabaseConnection;
import logic.Product;
import logic.ProductController;
import logic.ProductType;

public class TestProduct {
	
	Product test = new Product(0, "sovs", LocalDate.of(2018, 3, 21), "500g", ProductType.BEEF, "godt");
	Product test1 = new Product(0, "Pølse", LocalDate.of(2018, 11, 1), "500g",ProductType.SAUSAGE, "Nederste hylde");
	Product test2 = new Product(0, "Kylling", LocalDate.of(2018, 12, 18), "2000g", ProductType.CHICKEN, "Midterste hylde");
	Product test3 = new Product(0, "Bøff", LocalDate.of(2018, 10, 21), "2344g", ProductType.FATFISH, "Sove hylde");
	Product test4 = new Product(0, "Is", LocalDate.of(2018, 9, 17), "100g", ProductType.SMOKEDMEAT, "Midterste hul");
	Product test5 = new Product(0, "Suppe", LocalDate.of(2019, 1, 13), "1234g", ProductType.MINCEDMEAT, "Et Hemmeligt sted");
	Product test6 = new Product(0, "Jeg ved ikke hvad", LocalDate.of(2018, 8, 21), "5000g", ProductType.LAMB, "Sjove ting");
	Product test7 = new Product(0, "ØL", LocalDate.of(2018, 12, 13), "2000g", ProductType.MINCEDFISH, "Jeg vil sove");
//	Product testupdate = new Product(2, "opdater", "01/01/2020", "opdater", ProductType.BEEF, "opdater");

	
	ProductController productController = new ProductController(DatabaseConnection.newConnection("JanProjectDB"));
//	CheckDate checkDate = new CheckDate();
	
	public void add() {
		productController.addProduct(test);
		productController.addProduct(test1);
		productController.addProduct(test2);
		productController.addProduct(test3);
		productController.addProduct(test4);
		productController.addProduct(test5);
		productController.addProduct(test6);
		productController.addProduct(test7);
	}
	public void getAll() {
		List<Product> product2 = productController.getAllProducts();
		for (Product product : product2)
		System.out.println(product);
	}
	
	public void update() {
		productController.updateProduct(test1);
	}
	public void slet() {
		productController.deleteProduct(test1);
	}
	public void tjek() {
		test.checkDate();
	}
	
	public void getDate() {
		System.out.println(test.getExpireDate());
	}
	
	public void daysBetween() {
		System.out.println(test.daysBetweenTwoDates());
	}
}
