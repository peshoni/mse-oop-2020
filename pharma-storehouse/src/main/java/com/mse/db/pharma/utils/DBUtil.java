package com.mse.db.pharma.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private Connection cachedConnection = null;
	private static final DBUtil instance = new DBUtil();

	private static final String CONNECTION_STRING = "jdbc:oracle:thin:@172.16.251.135:1521:orcl";
	private static final String DB_NAME = "c##ex20_petar_kursova";
	private static final String DB_PASS = "123456";

//	private static final String LOCAL_CONNECTION_STRING = "jdbc:oracle:thin:@localhost:1521:orcl";
//	private static final String LOCAL_DB_NAME = "c##petar_ivanov_kursova";
//	private static final String LOCAL_DB_PASS = "123456";

	public DBUtil() {
	}

	public static DBUtil getInstance() {
		return instance;
	}

	public Connection getConnection() {
		try {
			if (cachedConnection == null || cachedConnection.isClosed() || !cachedConnection.isValid(10)) {
				System.out.println("Attempting to get a new connection to DB!");
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
//				cachedConnection = DriverManager.getConnection(LOCAL_CONNECTION_STRING, LOCAL_DB_NAME, LOCAL_DB_PASS);
				cachedConnection = DriverManager.getConnection(CONNECTION_STRING, DB_NAME, DB_PASS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.cachedConnection;
	}

	public static void closeConnectionIfAny() {
		try {
			instance.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
