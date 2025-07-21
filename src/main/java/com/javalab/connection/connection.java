package com.javalab.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
  public static Connection getConnection() {
    Connection connection = null;
    var dataBaseName = "student_db";
    var url = "jdbc:mysql://localhost:3306/" + dataBaseName;
    var userName = "myuser";
    var password = "mypassword";
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(url, userName, password);

    } catch (ClassNotFoundException | SQLException e) {
      System.out.println("Error connecting to the database: " + e.getMessage());
    }

    return connection;
  }

  public static void main(String[] args){
    var connection = getConnection();
    if (connection != null) {
      System.out.println("Connection established successfully!");
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println("Error closing the connection: " + e.getMessage());
      }
    } else {
      System.out.println("Failed to establish a connection.");
    }
  }
}
