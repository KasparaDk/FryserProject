package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
	
	private Connection connection;
	private ResultSet resultSet;
	private int updateQuery = 0;
	

	public ProductController(Connection connection) {
		this.connection = connection;
	}

	
	
	public boolean addProduct(Product product) {
		
			String sql = "INSERT INTO vare (" 
//		+ "vareID," 
		+ "varename," 
		+ "purchaseDate," 
		+ "amount," 
		+ "varetype," 
		+ "note) VALUES (?, ?, ?, ?, ?)";
			System.out.println(sql);
			
			try {
				PreparedStatement add = connection.prepareStatement(sql);
				add.setString(1, product.getName());
				add.setLong(2, product.getPurchaseDate().toEpochDay());
				add.setString(3, product.getAmount());
				add.setString(4, product.getType());
				add.setString(5, product.getNote());
//			System.out.println(sql);
//			System.out.println(add);

//			Statement statement = connection.createStatement();
//			int nRows = statement.executeUpdate(sql);
			
			int nRows = add.executeUpdate();

			if (nRows != 1) {
				return false;
			}
//			System.out.println("Nr. rows: " + nRows);

			// get auto-generated key
//resultSet = add.executeQuery("SELECT SCOPE_IDENTITY()");

//			if (resultSet.next()) {
//				int id = resultSet.getInt(1);
//				product.setVareID(id);
//				System.out.println("hej fejl");
//			}
			System.out.println("det virker sku");
			return true;

		} catch (SQLException e) {
			System.out.println("Failed to add: " + product);
			System.out.println(e.getMessage());
			return false;
		}

	}

public boolean updateProduct(Product product) {
	String sql = "UPDATE vare SET " 
			+ "varename = ?," 
			+ "purchaseDate = ?," 
			+ "amount = ?," 
			+ "varetype = ?," 
			+ "note = ? "
			+ "WHERE vareID = ?";
	try {
		PreparedStatement update = connection.prepareStatement(sql);			
		update.setString(1, product.getName());
		update.setLong(2, product.getPurchaseDate().toEpochDay());
		update.setString(3, product.getAmount());
		update.setString(4, product.getType());
		update.setString(5, product.getNote());
		update.setInt(6, product.getVareID());
//		String sql = "UPDATE vare SET varename=" + product.getName()	
//		+ "', amount='" + product.getAmount() 
//		+ "', purchaseDate='" + product.getPurchaseDate()
//		+ ", varetype='" + product.getType()
//		+ ", note='" + product.getNote()
//		+ "WHERE vareID=" + product.getVareID();
		
		System.out.println(sql);
		System.out.println(update);
		update.executeUpdate();
		return true;
//		int nRows = update.executeUpdate();
//		return (nRows == 1);
	} catch(SQLException e) {
		System.out.println("Could not update the product");
		System.out.println(e.getMessage());
		
	}
	return false;
	
}

public boolean deleteProduct(Product product) {
	String sql = "DELETE FROM vare WHERE vareID= ?";
	try {
		PreparedStatement delete = connection.prepareStatement(sql);
		delete.setInt(1, product.getVareID());
		
		System.out.println(sql);

//		Statement statment = connection.createStatement();

		int nRows = delete.executeUpdate();

		return (nRows == 1);
	} catch (SQLException e) {
		System.out.println("Could not delete Student: " + product);
		System.out.println(e.getMessage());
		return false;
	}
}

public List<Product> getAllProducts() {
	return getAllProductWhere("1=1");
}

private List<Product> getAllProductWhere(String whereClause) {
	List<Product> ProductListe = new ArrayList<>();

	try {
		String sql = "SELECT * FROM vare WHERE " + whereClause;
		System.out.println(sql);

		Statement statment = connection.createStatement();
		ResultSet resultSet = statment.executeQuery(sql);
				
		while (resultSet.next()) {
			int vareID = resultSet.getInt("VareID");
			String name = resultSet.getString("varename");
			LocalDate purchaseDate = LocalDate.ofInstant(Instant.ofEpochMilli(resultSet.getLong("purchaseDate")), ZoneId.systemDefault());
//			LocalDate date =  resultSet.getDate("purchaseDate").toLocalDate();
			String amount = resultSet.getString("amount");
			ProductType type = ProductType.valueOf(resultSet.getString("varetype"));
			String note = resultSet.getString("Note");

			Product product = new Product(vareID, name, purchaseDate, amount, type, note);

			ProductListe.add(product);

//			System.out.println(id + ", " + lastName + ", " + firstName + ", " + semesterNr);
		}
	} catch (SQLException e) {
		System.out.println("Error executing SQL statmennt");
		System.out.println(e.getMessage());
	}
	return ProductListe;
}
}
