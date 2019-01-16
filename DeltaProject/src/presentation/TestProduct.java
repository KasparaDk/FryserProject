package presentation;

import java.util.List;

import logic.CheckDate;
import logic.DatabaseConnection;
import logic.Product;
import logic.ProductController;
import logic.ProductType;

public class TestProduct {
	
//	Product test = new Product(0, "lort", "på", "09/03/2034", ProductType.BEEF, "godt");
	Product test = new Product(0, "Pølse", "15/09/2018", "500g",ProductType.SAUSAGE, "Nederste hylde");
	Product test2 = new Product(0, "Kylling", "15/02/2016", "2000g", ProductType.CHICKEN, "Midterste hylde");
	Product testupdate = new Product(2, "Update", "01/01/3019", "Update", ProductType.FRUIT, "En Update");
	
	ProductController productController = new ProductController(DatabaseConnection.newConnection("JanProjectDB"));
	CheckDate checkDate = new CheckDate();
	
	public void add() {
		productController.addProduct(test2);

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
	
}
