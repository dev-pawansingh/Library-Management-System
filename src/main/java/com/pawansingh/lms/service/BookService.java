package com.pawansingh.lms.service;

import com.pawansingh.lms.dao.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookService {
    Scanner sc = new Scanner(System.in);
    private static int sr_no = 0;

    public void searchBook() {
        System.out.println("Enter the book name");
        String book = sc.nextLine();
        System.out.println("Enter the author name");
        String author = sc.nextLine();

        String sql = "Select * from books where name = ? and author_name = ?";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book);
            ps.setString(2, author);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("qty") + " Book available");
            } else {
                System.out.println("Book not available");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in BookService -> searchBook");
            e.printStackTrace();
        }

    }

    public void addNewBook() {
        sr_no +=1;
        System.out.println("Enter the book name");
        String book = sc.nextLine();
        System.out.println("Enter the author name");
        String author = sc.nextLine();
        System.out.println("Enter the quantity of book");
        int qty = sc.nextInt();

        // check if book already present
        String check = "select * from books where name = ? and author_name = ?";
        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement ps = conn.prepareStatement(check)) {

            ps.setString(1, book);
            ps.setString(2, author);

            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                String sql = "insert into books(sr_no,name,author_name,qty) values (?,?,?,?)";

                try (Connection conn2 = DatabaseService.getConnection();
                     PreparedStatement ps2 = conn2.prepareStatement(sql)) {

                    ps2.setString(1, String.valueOf(sr_no));
                    ps2.setString(2, book);
                    ps2.setString(3, author);
                    ps2.setString(4,String.valueOf(qty));

                    ps2.executeUpdate();
                    System.out.println("Successfully added a new book");

                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("Error in BookService -> searchBook");
                    e.printStackTrace();
                }
            }else{
                System.out.println("This book already exist");
            }



        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in BookService -> searchBook");
            e.printStackTrace();
        }
    }

    public void updateQuantity() {
        System.out.println("Enter the book name");
        String book = sc.nextLine();
        System.out.println("Enter the author name");
        String author = sc.nextLine();
        System.out.println("Enter the quantity of book");
        int qty = sc.nextInt();

        String sql = "update books set qty = ? where name = ? and author_name = ?";
        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1,String.valueOf(qty));
            ps.setString(2, book);
            ps.setString(3, author);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully updated book quantity");
            } else {
                System.out.println("No such book found to update.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in BookService -> searchBook");
            e.printStackTrace();
        }
    }
    public void showAllBook() {

        String sql = "Select * from books";

        try (Connection conn = DatabaseService.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()){
                System.out.println("Book id -> " + rs.getString("book_id") + ", Serial No. -> " + rs.getString("sr_no")
                + ", Book Name -> " + rs.getString("name") + ", Book Author -> " + rs.getString("author_name")
                + ", Quantity -> " + rs.getString("qty"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in BookService -> searchBook");
            e.printStackTrace();
        }

    }
}
