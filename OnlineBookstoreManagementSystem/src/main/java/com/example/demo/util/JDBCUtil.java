package com.example.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
@Configuration
public class JDBCUtil {

	    private static final String URL = "jdbc:mysql://localhost:3306/onlinebookstore";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	    }
	    
}
