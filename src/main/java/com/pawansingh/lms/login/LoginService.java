package com.pawansingh.lms.login;

import com.pawansingh.lms.dao.DatabaseService;
import com.pawansingh.lms.dao.LoginDao;

import java.sql.Connection;
import java.util.Scanner;

public class LoginService {
    Scanner sc = new Scanner(System.in);
    public void doLogin(){
        System.out.println("Please provide username");
        String username = sc.nextLine();
        System.out.println("Please provide password");
        String password = sc.nextLine();

        try(Connection conn = DatabaseService.getConnection()){
            LoginDao loginDao = new LoginDao();
            String userType = loginDao.doLogin(conn,username,password);

            if (userType == null){
                System.out.println("Invalid user");
                return;
            }else{
                if (userType.equals("admin")){
                    System.out.println("Login successful as Admin");

                }else if(userType.equals("user")){
                    System.out.println("Login successful as Student");

                }else{
                    System.out.println("User does not exist");
                }
            }
        }catch (Exception e){
            System.out.println("Exception at Login Service try1");
        }


    }
}
