package com.pawansingh.lms.login;

import com.pawansingh.lms.dao.LoginDao;
import com.pawansingh.lms.service.BookService;

import java.util.Scanner;

public class LoginService {
    Scanner sc = new Scanner(System.in);
    public void doLogin(){
        System.out.println("Please provide username");
        String username = sc.nextLine();
        System.out.println("Please provide password");
        String password = sc.nextLine();

        try{
            LoginDao loginDao = new LoginDao();
            String userType = loginDao.doLogin(username,password);

            if (userType == null){
                System.out.println("Invalid user");
            }else{
                if (userType.equals("admin")){
                    System.out.println("Login successful as Admin");
                    displayAdminMenu();

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
    public void displayAdminMenu(){
        int choice;
        do{
            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
            System.out.println("1. Search a book");
            System.out.println("2. Add new book");
            System.out.println("3. Update quantity of a book");
            System.out.println("4. Show all books");
            System.out.println("5. Register Student");
            System.out.println("6. Show all registered students");
            System.out.println("7. Exit");
            System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
            System.out.println("Please enter your choice");
            choice = sc.nextInt();

            switch (choice){
                case 1: {
                    BookService bs = new BookService();
                    bs.searchBook();
                    break;
                }
                case 2:{
                    BookService bs = new BookService();
                    bs.addNewBook();
                    break;
                }
                case 3:{
                    BookService bs = new BookService();
                    bs.updateQuantity();
                    break;
                }
                case 4:{
                    BookService bs = new BookService();
                    bs.showAllBook();
                    break;
                }
                case 5:{

                }
                case 6:{

                }
                case 7:{
                    System.out.println("Thanks for using LMS");
                    break;
                }
                default:{
                    System.out.println("Please enter a valid choice");
                }

            }

        }while (choice != 7);
    }

}
