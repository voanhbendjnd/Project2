package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnectionJDBC {
	 	private static String url = "jdbc:mysql://localhost:3306/estatebasic?autoReconnect=true&useSSL=false";
	    private static String username = "root";
	    private static String password = "1607";
	    public static Connection getConnection() {
	    	Connection con = null;
	    	try {
	    		con = DriverManager.getConnection(url, username, password);
	    		return con;
	    	}catch(Exception ex) {
	    		JOptionPane.showMessageDialog(null, ex);
	    	}
	    	return con;
	    }
	
}
