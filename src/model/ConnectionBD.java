package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
	//singleton par la conexión BD
	private static Connection connection;
	//constructor privado
	private ConnectionBD(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("No encuentro jdbc");
		}
		try {
			connection = DriverManager.
					getConnection("jdbc:sqlite:BD/users.bd");
		} catch (SQLException e) {
			System.out.println("Error en la conexión a la BD");
		}
	}
	public static Connection getConnectionBD(){
		if (connection == null)
			new ConnectionBD();
		return connection;
	}
	public static void main(String[] args) {
		Connection connection = getConnectionBD();
	}
	
}
