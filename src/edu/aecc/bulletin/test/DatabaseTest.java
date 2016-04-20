package edu.aecc.bulletin.test;

import java.util.HashMap;

import edu.aecc.bulletin.io.MySQL;
import edu.aecc.bulletin.io.Query;

public class DatabaseTest {

	public static void main(String[] args) {
		Query q = MySQL.query("SELECT * FROM events WHERE eventdate > NOW()");
		while (q.hasNext()) {
			HashMap<String, String> data = q.next();
			System.out.println(String.format("%s: {%s, %s}", data.get("id"), data.get("name"), data.get("eventdate")));
		}
	}
}