package com.pers.utils;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseUtil {
	public static Map<String, String> objMap = new LinkedHashMap<String, String>();
	public static Connection con;
	public static java.sql.Statement stmnt;

	// Creating and returning a connection
	public Connection getConnection() {
		String url = "";
		String username = "";
		String password = "";

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection(url, username, password);
		}

		catch (SQLException | ClassNotFoundException e) {

			// Custom Message or Log can be used here
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return con;
	}

	// Executing Query and Storing the results into a Map
	public static Map<String, String> ExecuteQueryToMap(String strQuery) {
		try {
			if (objMap != null) {
				objMap.clear();
			}
			DatabaseUtil DbUtil = new DatabaseUtil();
			DbUtil.getConnection();
			stmnt = con.createStatement();
			// Storing results into a ResultSet
			ResultSet rs = stmnt.executeQuery(strQuery);

			// Pushing the data of ResultSet into a Map
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= columns; ++i) {
					objMap.put(md.getColumnName(i), rs.getObject(i).toString());
				}
			}
		} catch (SQLException e) {
			// Custom Message or Log can be used here
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		// Close active Database Connection
		finally {
			try {
				stmnt.close();
				con.close();
			} catch (Exception ex3) {
				ex3.printStackTrace();
			}
		}
		return objMap;
	}

}
