package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

	private Connection connection;

	public ProductController(Connection connection) {
		this.connection = connection;
	}

	public boolean addProduct(Product product) {

		String sql = "INSERT INTO vare (" + "varename," + "purchaseDate," + "amount," + "varetype,"
				+ "note) VALUES (?, ?, ?, ?, ?)";
		System.out.println(sql);

		try {
			PreparedStatement add = connection.prepareStatement(sql);
			add.setString(1, product.getName());
			add.setLong(2, product.getPurchaseDate().toEpochDay());
			add.setString(3, product.getAmount());
			add.setString(4, product.getType());
			add.setString(5, product.getNote());

			int nRows = add.executeUpdate();

			if (nRows != 1) {
				return false;
			}
			return true;

		} catch (SQLException e) {
			System.out.println("Failed to add: " + product);
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean updateProduct(Product product) {
		String sql = "UPDATE vare SET " + "varename = ?," + "purchaseDate = ?," + "amount = ?," + "varetype = ?,"
				+ "note = ? " + "WHERE vareID = ?";
		try {
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, product.getName());
			update.setLong(2, product.getPurchaseDate().toEpochDay());
			update.setString(3, product.getAmount());
			update.setString(4, product.getType());
			update.setString(5, product.getNote());
			update.setInt(6, product.getVareID());

			System.out.println(sql);
			System.out.println(update);
			update.executeUpdate();
			return true;
		} catch (SQLException e) {
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
				LocalDate purchaseDate = LocalDate.ofEpochDay(resultSet.getLong("purchaseDate"));
				String amount = resultSet.getString("amount");
				ProductType type = ProductType.valueOf(resultSet.getString("varetype"));
				String note = resultSet.getString("Note");

				Product product = new Product(vareID, name, purchaseDate, amount, type, note);

				ProductListe.add(product);

			}
		} catch (SQLException e) {
			System.out.println("Error executing SQL statmennt");
			System.out.println(e.getMessage());
		}
		return ProductListe;
	}
}
