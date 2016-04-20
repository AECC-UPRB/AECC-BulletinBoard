package edu.aecc.bulletin.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class MySQL {

	private static Connection connection;
	private static Statement statement;

	static {
		try {
			File f = new File("dbcredentials");
			if (!f.exists()) {
				System.err.println("'dbcredentials' file does not exist");
				System.exit(0);
			}
			BufferedReader br = new BufferedReader(new FileReader(f));
			String db = br.readLine();
			String user = br.readLine();
			String password = br.readLine();
			br.close();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, user, password);
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				close();
			}
		});
	}

	public static Query query(String query) {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] columns = null;
		try {
			ResultSet r = statement.executeQuery(query);
			ResultSetMetaData meta = r.getMetaData();
			columns = new String[meta.getColumnCount()];
			while (r.next()) {
				HashMap<String, String> row = new HashMap<String, String>();
				for (int x = 1; x <= meta.getColumnCount(); x++) {
					row.put(meta.getColumnName(x), r.getString(x));
					columns[x - 1] = meta.getColumnName(x);
				}
				result.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			close();
		}
		return new Query(result, columns);
	}

	private static void close() {
		try {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}