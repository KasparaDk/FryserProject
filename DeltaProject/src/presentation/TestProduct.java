package presentation;

import java.util.List;

import logic.CheckDate;
import logic.DatabaseConnection;
import logic.Product;
import logic.ProductController;
import logic.ProductType;

public class TestProduct {
	
	Product test = new Product(0, "sovs", "på", "09/03/2034", ProductType.BEEF, "godt");
	Product test1 = new Product(0, "Pølse", "15/09/2018", "500g",ProductType.SAUSAGE, "Nederste hylde");
	Product test2 = new Product(0, "Kylling", "25/03/2018", "2000g", ProductType.CHICKEN, "Midterste hylde");
	Product test3 = new Product(0, "Bøff", "12/12/2053", "2344g", ProductType.FATFISH, "Sove hylde");
	Product test4 = new Product(0, "Is", "31/07/2013", "100g", ProductType.SMOKEDMEAT, "Midterste hul");
	Product test5 = new Product(0, "Suppe", "01/02/1995", "1234g", ProductType.MINCEDMEAT, "Et Hemmeligt sted");
	Product test6 = new Product(0, "Jeg ved ikke hvad", "15/10/1980", "5000g", ProductType.LAMB, "Sjove ting");
	Product test7 = new Product(0, "ØL", "06/02/2020", "2000g", ProductType.MINCEDFISH, "Jeg vil sove");
	Product testupdate = new Product(2, "opdater", "01/01/2020", "opdater", ProductType.BEEF, "opdater");

	
	ProductController productController = new ProductController(DatabaseConnection.newConnection("JanProjectDB"));
	CheckDate checkDate = new CheckDate();
	
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
		productController.updateProduct(testupdate);
	}
	public void slet() {
		productController.deleteProduct(test2);
	}
	public void tjek() {
		checkDate.checExpirekDate(test2);
		
	}
	
	public void getDate() {
		checkDate.getExpireDate(test2);
	}
	
	public void daysBetween() {
		checkDate.daysBetweenTwoDates(test2);
	}
}
