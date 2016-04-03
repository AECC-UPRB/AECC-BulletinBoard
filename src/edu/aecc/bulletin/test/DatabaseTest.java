package edu.aecc.bulletin.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:3306/aeccdb";
		String user = "aecc";
		String password = "password";
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM events WHERE eventdate > NOW()");
			while (rs.next())
				System.out.println(String.format("{%d} : '%s', '%s', '%s'", rs.getInt(1), rs.getString(2),
						rs.getString(4), rs.getString(5)));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}