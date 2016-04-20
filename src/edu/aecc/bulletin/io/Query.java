package edu.aecc.bulletin.io;

import java.util.ArrayList;
import java.util.HashMap;

public class Query {

	private int idx;
	private ArrayList<HashMap<String, String>> data;
	private String[] columnList;

	public Query(ArrayList<HashMap<String, String>> src, String[] columns) {
		data = src;
		idx = 0;
		columnList = columns;
	}

	public boolean hasNext() {
		return idx < data.size();
	}

	public HashMap<String, String> next() {
		return data.get(idx++);
	}

	public String[] getColumns() {
		return columnList;
	}
}