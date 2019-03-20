package com.ymk.tso.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class ConnectionUtil {

	private static Map<String, String> connectionMap = new HashMap<>();

	public static Connection getConnection() {

		if (!connectionMap.isEmpty() && connectionMap.size()==4) {
			return getConnectionByProperties(connectionMap.get("driver"), connectionMap.get("url"), connectionMap.get("username"), connectionMap.get("password"));
		}

		return null;
	}

	private static Connection getConnectionByProperties(String... properties) {
		String driver = properties[0];
		String url = properties[1];
		String userId = properties[2];
		String password = properties[3];
 
		try {
			Class.forName(driver).newInstance();
			System.out.println("connection successful!");
			return DriverManager.getConnection(url, userId, password);
		} catch (Exception ex) {
			System.out.println("connection error!");
			ex.printStackTrace();
		}

		return null;
	}

	public static void setConnectionProperties(Map<String, String> props) {
		connectionMap = props;
	}
}
