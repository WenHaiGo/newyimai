package com.easybuy.dbutil;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//工具类得到connection
public class DBUtil {

	public static Connection getConn() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql", "root", "123456");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void DBclose(Connection conn, Statement stm, ResultSet rs) throws SQLException {
		if (conn != null)
			conn.close();

		if (stm != null)
			stm.close();
		if (rs != null)
			rs.close();
	}

	public static void DBclose(Connection conn, Statement statement) throws SQLException {
		if (conn != null)
			conn.close();

		if (statement != null)
			statement.close();

	}

}
