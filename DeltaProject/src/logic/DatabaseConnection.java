package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection newConnection(String databaseName) {
		String connectionString = "jdbc:sqlserver://142.93.228.145:1433;" + "databaseName=" + databaseName + ";"
				+ "user=sa;password=myStrongPassword!";
		//TEST
		try {
			System.out.println("Connecting to database...");

			Connection connection = DriverManager.getConnection(connectionString);

			System.out.println("Connected to database");

			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Could not connect to database!");
			System.out.println(e.getMessage());
		}
		return null;
	}
}
