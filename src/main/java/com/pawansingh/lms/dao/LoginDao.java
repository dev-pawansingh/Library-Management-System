package com.pawansingh.lms.dao;

import java.sql.*;

public class LoginDao {
    public String doLogin(String userName, String password){

        String query = "select * from login where username = ? and password = ?";

        try(Connection conn = DatabaseService.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1,userName);
            ps.setString(2, password);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return rs.getString("user_type");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("DatabaseServices Class not fount");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
