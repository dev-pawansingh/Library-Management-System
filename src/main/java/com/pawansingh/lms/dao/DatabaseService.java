package com.pawansingh.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    public static Connection conn;
    private static final String url = "jdbc:mysql://localhost:3306/lms";
    private static final String user = "root";
    private static final String password = "Pawansingh@1";

    private static Connection createConn(){

        try{
            // Loading JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creating connection
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Database connection created successfully.");
//            conn.close();
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("My Sql JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB connection failed");
            e.printStackTrace();
        }
        return conn;
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
            return createConn();
    }
}
