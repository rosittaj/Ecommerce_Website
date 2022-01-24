
package com.clientserver.dao;

import java.sql.Connection;
import java.sql.SQLException;



public class ConnectionUtil {
	private static Connection connection;

	public void setConnection(Connection connection) {
		ConnectionUtil.connection = connection;
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			return MySqlConnectionUtil.getMySqlConnection();
		}
		return connection;

	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}

	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (Exception e) {
		}
	}
}
