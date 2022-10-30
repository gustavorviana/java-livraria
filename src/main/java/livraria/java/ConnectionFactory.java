package livraria.java;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.cj.QueryReturnType;

public class ConnectionFactory {
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/bdlivraria", "root", "root");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
