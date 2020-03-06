package com.datasource.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager
{
	private static DatabaseConnectionManager connectionManagerInstance;
	private Connection conn;
	
	private String connectionString;
	private String user;
	private String pass;
	
	
	public static DatabaseConnectionManager getConnectionManagerInstance() {
		
		if (connectionManagerInstance==null) {
			connectionManagerInstance = new DatabaseConnectionManager();
		}
		return connectionManagerInstance;
	}

	public Connection connect() {
	       System.out.println("Conn string in connect(): "+ getConnectionString());
	       System.out.println("User in connect(): "+ getUser());
	       System.out.println("Pass in connect(): "+ getPass());
		 try {
	            conn = (Connection)DriverManager.getConnection(getConnectionString(), getUser(), getPass());
	            
	            if (conn!=null) {
	                System.out.println("successfully connected to database...");
	            }
	         
	        }catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("cannot connect to database...");
	        }
		 
		return conn;
	}

	public void disconnectDB() throws SQLException{
		conn.close();
		System.out.println("Disconnected from database...");
	}

	public String getConnectionString() {
		return connectionString;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}
	
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
