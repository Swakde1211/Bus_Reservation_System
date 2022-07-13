package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {

	private static Conn conn;
	private Connection c;

	private Conn() {
		try {
			Class.forName("org.postgresql.Driver");

		
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bus_Registration", "postgres", "root");

		}

		catch (Exception e) {

		}
	}
	
	public static Conn getConnection() {
		if(conn == null)
			conn = new Conn();
		return conn;
	}
	
	public Connection getDBConnection() {
		return c;
	}
}