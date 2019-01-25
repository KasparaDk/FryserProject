package presentation;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import logic.DatabaseConnection;
import logic.Mailer;
import logic.Product;
import logic.ProductController;
import logic.ProductType;

public class TestProduct {
	
	Product test = new Product(0, "sovs", LocalDate.of(2018, 8, 21), "500g", ProductType.BEEF, "Skuffe 3");
	Product test1 = new Product(0, "Pølse", LocalDate.of(2018, 11, 1), "500g",ProductType.SAUSAGE, "Nederste hylde");
	Product test2 = new Product(0, "Kylling", LocalDate.of(2018, 12, 18), "2000g", ProductType.CHICKEN, "Midterste hylde");
	Product test3 = new Product(0, "Bøff", LocalDate.of(2018, 11, 21), "2344g", ProductType.FATFISH, "Sove hylde");
	Product test4 = new Product(0, "Is", LocalDate.of(2018, 9, 17), "100g", ProductType.SMOKEDMEAT, "Midterste hul");
	Product test5 = new Product(0, "Suppe", LocalDate.of(2019, 1, 13), "1234g", ProductType.MINCEDMEAT, "Skuffe 1");
	Product test6 = new Product(0, "Ost", LocalDate.of(2018, 8, 21), "5000g", ProductType.CHEESE45, "Kurv");
	Product test7 = new Product(0, "Banan", LocalDate.of(2018, 12, 13), "2000g", ProductType.BANANA, "skuffe 2");
	Product test8 = new Product(0, "Broccoli", LocalDate.of(2019, 1, 10), "500g", ProductType.BROCCOLI, "I toppen til venster");
	Product test9 = new Product(0, "Mail test 1", LocalDate.of(2019, 1, 22), "mail test 1", ProductType.BACON, "Mail test 1");
	Product test10 = new Product(0, "Mail test 2", LocalDate.of(2019, 1, 12), "mail test 2", ProductType.BACON, "Mail test 2");
	Product test11 = new Product(0, "Mail test 3", LocalDate.of(2018, 18, 24), "mail test 3", ProductType.BACON, "Mail test 3");
//	Product testupdate = new Product(2, "opdater", "01/01/2020", "opdater", ProductType.BEEF, "opdater");
	
	Main main = new Main();
	
	Menu menu = new Menu();

	Mailer mailer = new Mailer();
	
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
		productController.addProduct(test8);
		productController.addProduct(test9);
		productController.addProduct(test10);
		productController.addProduct(test11);
		
//		productController.addProduct(test);
//		productController.addProduct(test2);
//		productController.addProduct(test3);
//		productController.addProduct(test4);
//		productController.addProduct(test5);
//		productController.addProduct(test7);
//		
//		productController.addProduct(test);
//		productController.addProduct(test2);
//		productController.addProduct(test3);
//		productController.addProduct(test4);
//		productController.addProduct(test5);
//		productController.addProduct(test7);
//		
//		productController.addProduct(test);
//		productController.addProduct(test2);
//		productController.addProduct(test3);
//		productController.addProduct(test4);
//		productController.addProduct(test5);
//		productController.addProduct(test7);
//		
//		productController.addProduct(test);
//		productController.addProduct(test2);
//		productController.addProduct(test3);
//		productController.addProduct(test4);
//		productController.addProduct(test5);
//		productController.addProduct(test7);
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
		List<Product> product2 = productController.getAllProducts();
		for (Product productSlet : product2)
		productController.deleteProduct(productSlet);
	}
	public void tjek() {
		List<Product> product2 = productController.getAllProducts();
		for (Product product : product2)
		product.checkDate();
	}
	
	public void getDate() {
		System.out.println(test.getExpireDate());
	}
	
	public void daysBetween() {
		System.out.println(test.daysBetweenTwoDates());
	}
	public void mail() {
		
		Properties login = new Properties();
		try (FileReader reader = new FileReader("login.properties.txt")) {
			login.load(reader);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String email = login.getProperty("email");
		String password = login.getProperty("password");
		
		StringBuilder sb = new StringBuilder();
		List<Product> product2 = productController.getAllProducts();
		for (Product product : product2) {
			String foo = product.checkDate();
			if (foo != null) {
				sb.append(foo);
			}
		}
		mailer.send(email, password,"kalle-drengen@hotmail.com","Fryser status" , sb.toString() );
	}
}
