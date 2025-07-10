package com.pawansingh.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
    public String doLogin(Connection conn ,String userName, String password){
        String query = "select * from login where user_name = ? and password = ?";

        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1,userName);
            ps.setString(2, password);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return rs.getString("user_type");
                }
            }catch (Exception e){
                System.out.println("Exception at Login Dao1");
            }
        }catch (Exception e){
            System.out.println("Exception at Login Dao2");
        }
        return null;
    }
}
