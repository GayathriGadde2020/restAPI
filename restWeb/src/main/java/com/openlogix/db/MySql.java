package com.openlogix.db;

import java.sql.Statement;
import java.util.ArrayList;
import javax.json.JsonException;
import javax.json.bind.JsonbException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import Implementation.User;

public class MySql {

	private String hostName;
	private Integer port;
	private String userName;
	private String password;
	private String url;
	private String dbName;
	private Connection connect = null;

	public MySql(String hostName, Integer port, String userName, String password, String dbName) {
		super();
		this.hostName = hostName;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.dbName = dbName;
		this.url = "jdbc:mysql://" + this.hostName + ":" + this.port + "/" + this.dbName + "?serverTimeZone=UTC";
	}

	public void Connect() {
		// Connection connect = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connection is established");
		} catch (ClassNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		// if (connect == null) {
		try {
			connect = DriverManager.getConnection(this.url,this.userName,this.password);
			System.out.println("Connected to SQL");
		} catch (SQLException e) { // TODO
			e.printStackTrace();
		}
	}
	// }

	private String toJson(ArrayList<User> users) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(users);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public String performQuery(String query) throws Exception {
		if (this.connect == null) {
			System.out.println("Connector is not Initialized");
			throw new Exception("Connector not found");
		} else {
			ArrayList<User> users = new ArrayList<User>();
			Statement statement = this.connect.createStatement();
			ResultSet rs = statement.executeQuery(query);
			try {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					//System.out.println("Name: " + name);
					User user = new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
					users.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return this.toJson(users);
		}
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
