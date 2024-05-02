
//-*- java -*-
//$file: ServerFrontController.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller

package controller;

import java.util.Scanner;

import model.ProductsInterface;
import model.ProductsContainer;

import controller.Dispatcher;

import model.Account;
import model.AdminAccount;
import model.CustomerAccount;

import model.AccountsInterface;
import model.AccountsContainer;


import controller.SimilarAccFactoryInfc;
import controller.SimilarAccFactoryImpl;

import controller.AccountsFactoryInterface;
import controller.AccountsFactoryImplementation;

import java.util.Scanner;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.*;
import java.rmi.registry.*;

public class ServerFrontController {

    //A singleton object
    private static ServerFrontController uniqueInstance;

    //The private dispatcher object
   private Dispatcher dispatcher;
   private Account sessionHolder;

    private Authorization auth;
   //AccountsInterface accs;

    //account factory
    private SimilarAccFactoryInfc accountsFactory;

    //account container facory
    private AccountsFactoryInterface accountsContainerFactory;

    //stub for accounts conatainer
    AccountsInterface stubAccounts;

    //
    //a private constructor that makes sure there is only one instance
    //
   private  ServerFrontController(){
      try{
        //  this.dispatcher = new Dispatcher();
        this.dispatcher = Dispatcher.getInstance();

        //container factory
        this.accountsContainerFactory = new AccountsFactoryImplementation();
        //account object factory
        this.accountsFactory = new SimilarAccFactoryImpl();

         this.sessionHolder = this.accountsFactory.createAdminAccount();

        String name = "//localhost:2044/OnlineStore";
        // AccountsInterface stub = (AccountsInterface)Naming.lookup(name);
        stubAccounts = this.accountsContainerFactory.createAccountsContainer();
        Naming.rebind(name, stubAccounts);
        
      }catch(Exception e){
         System.out.println(e.getMessage());
         System.out.println("sfc error------");
      }
   }

    //
    //isAuthenticUser
    //handles and checks for user access rights
    public boolean isAuthenticUser(){
        //first check if user is permiited to do so;
        try{
            auth = new Authorization(this.sessionHolder.getType(), "admin");
            return auth.userPermission();
        }catch(Exception e){
            System.out.println();
        }

        return false;
    }

    //
    //startFrontController
    //loads the front controller
   public void startFrontController(){
    try{
        //products container added to registry
        ProductsInterface accs = new ProductsContainer();
        String nameOfRegistry = "//localhost:2045/OnlineStore";
        Naming.rebind(nameOfRegistry, accs);

    }catch(Exception e){
        System.out.println("error in setting up registry");
    }
    //while loop that takes input and stops based on some conditions
    //processes user inputs
      while(true){

                try {
                   
                   Scanner readObj = new Scanner(System.in);
                    System.out.println("Hello and welcome!------------ Online store");

                    System.out.println("please enter Y/y to enter your login credentials if you have already created one");
                    System.out.println("please enter N/n to sign up as an admin");
                    System.out.println("please enter F/f to shut down server");
                    

                    String opt = readObj.nextLine();

                    if( opt.compareTo("Y")  ==0 || opt.compareTo("y")  ==0) {
                        
                        try{
                           String name = "//localhost:2044/OnlineStore";
                           AccountsInterface stub = (AccountsInterface)Naming.lookup(name);
                          
                           this.dispatchLoginRequest(stub);

                           System.out.println("welcome: "+ sessionHolder.getName());

                        }catch(Exception e){
                           System.out.println(e.getMessage());
                        }

                            while(true){
                                System.out.println("What would you like to do today");
                                

                                System.out.println("Please enter C/c to create an account for a new user");
                                System.out.println("Please enter A/a to add items");
                                System.out.println("Please enter R/r to remove items");
                                System.out.println("Please enter D/d to remove certain account");
                                System.out.println("Please enter U/u to update an item");
                                System.out.println("Please enter V/v to online store items");
                                System.out.println("please eneter E/e to sign out and exit the system");

                                Scanner readAdminInput = new Scanner(System.in);
                                String adminToDo = readAdminInput.nextLine();

                                
                                //if user enters exit
                                
                                if( adminToDo.compareTo("E")  ==0 || adminToDo.compareTo("e")  ==0){
                                    System.out.println("shutting down server");
                                    break;
                                } 
                                else if ( adminToDo.compareTo("C")  ==0 || adminToDo.compareTo("c")  ==0){
                                   
                                    String name = "//localhost:2044/OnlineStore";
                                    AccountsInterface stub = (AccountsInterface)Naming.lookup(name);

                                    this.dispatchCreateNewAccountForUserRequest(stub);
                                    Naming.rebind(name, stub);
                                }
                                else if ( adminToDo.compareTo("A")  ==0 || adminToDo.compareTo("a")  ==0){
                                    String name = "//localhost:2045/OnlineStore";
                                    ProductsInterface stub = (ProductsInterface)Naming.lookup(name);

                                    this.dispatchAddingProductRequest(stub);
                                    Naming.rebind(name, stub);
                                    
                                }
                                else if ( adminToDo.compareTo("R")  ==0 || adminToDo.compareTo("r")  ==0){
                                    String name = "//localhost:2045/OnlineStore";
                                    ProductsInterface stub = (ProductsInterface)Naming.lookup(name);
                                    this.dispatchRemoveItemRequest(stub);
                                    Naming.rebind(name, stub);
                                    
                                }
                                else if ( adminToDo.compareTo("D")  ==0 || adminToDo.compareTo("d")  ==0){
                                    
                                    try{
                                        String name = "//localhost:2044/OnlineStore";
                                        AccountsInterface stub = (AccountsInterface)Naming.lookup(name);
                                        
                                        if(stub == null){
                                            System.out.println("Accounts are not ready");
                                            System.out.println("Please make sure you are logged in");
                                            
                                        } else {
                                            stub.showUsers();
                                            this.removeCertainAccountrequest(stub);
                                            Naming.rebind(name, stub);
                                        }
                                    }catch(Exception e){
                                        System.out.println(e.getMessage());
                                    }    

                                    
                                    
                                }
                                else if ( adminToDo.compareTo("U")  ==0 || adminToDo.compareTo("u")  ==0){
                                     try{
                                        

                                        System.out.println("please select enter item you would like to update");
                                        String name = "//localhost:2045/OnlineStore";
                                        ProductsInterface stub = (ProductsInterface)Naming.lookup(name);
                                        stub.displayProducts();
                                        this.dispatchUpdateProductRequest(stub);

                                        Naming.rebind(name,stub);
                                    }catch(Exception e){
                                        System.out.println("user interface error");
                                    }
                                    
                                } 
                                else if ( adminToDo.compareTo("V")  ==0 || adminToDo.compareTo("v")  ==0){
                                    try{
                                        
                                        String name = "//localhost:2045/OnlineStore";
                                        ProductsInterface stub = (ProductsInterface)Naming.lookup(name);
                                        this.dispatchViewProductsRequest(stub);
                                    }catch(Exception e){
                                        System.out.println("server problems.....");
                                        System.out.println(e.getMessage());
                                    }
                                    
                                } 
                                else{
                                    //
                                    break;
                                }

        
                            }
                        //end of checking for authentic user
                        }
                    //}
                    

                    else if(opt.compareTo("N")  ==0 || opt.compareTo("n")  ==0) {

                        String name = "//localhost:2044/OnlineStore";
                        
                        this.dispatchSignUpRequest(this.stubAccounts);
                       
                        Naming.rebind(name,this.stubAccounts);
                        continue;
                    } else if(opt.compareTo("F")  ==0 || opt.compareTo("f")  ==0){
                        System.out.println("shutting down server");
                        System.exit(0);
                    }
                    else {
                        System.out.println("Please select out of the listed options below");
                    }
                } catch(Exception e){
                    System.out.println("Server err: " + e.getMessage());
                    e.printStackTrace();
                }

            } 
   }

    //
    //dispatchSignUpRequest
    //handles and process signing up request
   public void dispatchSignUpRequest(AccountsInterface stub){

        try{
            Scanner readObj = new Scanner(System.in);
            System.out.println("Welcome to the account creation portal\n");

            System.out.println("please Enter your prefered username");
            String username = readObj.nextLine();

            System.out.println("please Enter your address");
            String addr = readObj.nextLine();

            String type = "admin";

            System.out.println("please Enter a four digit number for password");
            int password = readObj.nextInt();
            
            Account admin = this.accountsFactory.createAdminAccount();
            this.dispatcher.handleSignUprequest(username, addr, type, password, admin, stub);
        }catch(Exception e){
            System.out.println("Failure to dispatch sign up request");
        }
   
   }

    //
    //dispatchLoginRequest
    //handles and process logging in request
   public void dispatchLoginRequest(AccountsInterface stub){
      try{
         Scanner readObj = new Scanner(System.in);

         System.out.println("Welcome to the admin portal\n");

         System.out.println("Please enter you username");
         String username = readObj.nextLine();

         System.out.println("please enter your passsword");
         int password = readObj.nextInt();

         
         this.dispatcher.handleLoginRequest(stub, this.sessionHolder, username, password);
      } catch(Exception e){
         System.out.println("dispatchLoginRequest error");
      }

      // System.out.println("")
   }

    //
    //dispatchUpdateProductRequest
    //handles and process updating product request
   public void dispatchUpdateProductRequest(ProductsInterface stub){
         if(this.isAuthenticUser() != true){
            System.out.println("you are not authorized");
            return;
        }
        try{
            Scanner readObj = new Scanner(System.in);

            System.out.println("Please enter product's name");
            String name = readObj.nextLine();

            System.out.println("Please enter new product desceiption");
            String desc = readObj.nextLine();

            System.out.println("Please enter new product price");
            double price = readObj.nextDouble();

            System.out.println("Please enter new quantity");
            int quantity = readObj.nextInt();

            dispatcher.handleUpdateProductRequest(name, desc, price, quantity, stub);
        }catch(Exception e){
            
        }

   }

    //
    //dispatchAddingProductRequest
    //handles and process adding product
   public void dispatchAddingProductRequest(ProductsInterface stub){

        if(this.isAuthenticUser() != true){
            System.out.println("you are not authorized");
            return;
        }
        Scanner readObj = new Scanner(System.in);

        System.out.println("Please enter product name");
        String name = readObj.nextLine();

        System.out.println("Please enter product desceiption");
        String desc = readObj.nextLine();

        System.out.println("Please enter product price");
        double price = readObj.nextDouble();

        System.out.println("Please enter quantity");
        int quantity = readObj.nextInt();

        dispatcher.handleAddingNewProductRequest(name, desc, price, quantity, stub);

   }

    //
    //dispatchCreateNewAccountForUserRequest
    //handles and process creating new account
    //
   public void dispatchCreateNewAccountForUserRequest(AccountsInterface stub){
        if(this.isAuthenticUser() != true){
            System.out.println("you are not authorized");
            return;
        }
      try {
          Account acc;

       // adding new account by a logged in admin
        System.out.println("welcome to the protal for creating new account for an admin or acustomer");
        Scanner readObj = new Scanner(System.in);

        System.out.println("Please enter the username ");
        String username = readObj.nextLine();

        System.out.println("Please enter the address ");
        String addr = readObj.nextLine();

        System.out.println("Please enter the A/a for admin and C/c for customer ");
        String type = readObj.nextLine();

        System.out.println("please enter your passsword");
        int passsword = readObj.nextInt();

        if( type.compareTo("A") ==0  || type.compareTo("a")== 0 ){
            type = "admin";
            acc = this.accountsFactory.createAdminAccount();
        } 
        else if( type.compareTo("C")==0 || type.compareTo("c")==0 ){
            type = "cust";
            // acc = new CustomerAccount();
            acc = this.accountsFactory.createCustomerAccount();
        } 
        else {
            System.out.println("Wrong entry");
            System.out.println("Please follow the prompt");
            return;
        }


        dispatcher.handleAddingNewAccount(stub, username, addr, type, passsword, acc);
      }catch(Exception e){
        System.out.println(e.getMessage());
        System.out.println("Error in submitted credentials--------");
      }

   }

    //
    //dispatchRemoveItemRequest
    //handles and process item removal from inventory
   public void dispatchRemoveItemRequest(ProductsInterface stub){

        if(this.isAuthenticUser() != true){
            System.out.println("you are not authorized");
            return;
        }
        Scanner readObj = new Scanner(System.in);

        System.out.println("Please enter product's name");
        String nameOfProduct = readObj.nextLine();

        dispatcher.handleRemoveItemRequest(stub, nameOfProduct);


   }

    //
    //removeCertainAccountrequest
    //account removal processor
   public void removeCertainAccountrequest(AccountsInterface stub){
        if(this.isAuthenticUser() != true){
            System.out.println("you are not authorized");
            return;
        }
        try{
            Scanner readObj = new Scanner(System.in);

            System.out.println("Please enter the username");
            String username = readObj.nextLine();

            System.out.println("Please enter the type of user admin or cust");
            String type = readObj.nextLine();

            System.out.println("Please enter the password of the user");
            int password = readObj.nextInt();

            dispatcher.removeCertainAccountRequest(stub, username, type, password);

        }catch(Exception e){
            System.out.println("Error in input information or account does not exist");
        }
   }

    //
    //dispatchViewProductsRequest
    //
   public void dispatchViewProductsRequest(ProductsInterface stub){

        try {
            this.dispatcher.handleProductsDisplayRequest(stub);
        }catch(Exception e){
            System.out.println("Request could not be processed");
        }
   }

    //
    //getInstance
    //returns a unique instance of this object
   public static synchronized ServerFrontController getInstance(){
    if (uniqueInstance == null){
            uniqueInstance = new ServerFrontController();
        }

        return uniqueInstance;
   }


}
