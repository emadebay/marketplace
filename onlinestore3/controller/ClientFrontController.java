/***
 * Author: Emmanuel Adebayo
 * File: ClientFrontController.java
 * Package: controller
 * Purpose: The Class uses the front controller pattern to dispatch of users request
 * The request are then sent to the Dispatcher(Dispatcher.java) which will inovke the necessary commands if necessary
 * 
 */

package controller;
//Scanner for input taking
import java.util.Scanner;

//Import the dispatcher
//handles request processed by the front controller
import controller.ClientDispatcher;

//import model objects
import model.Account;
import model.CustomerAccount;

//import the product model class
import model.ProductInterface;
import model.Product;

//import the container class interface and the implementation
//Handles and acts a storage for the accounts
import model.AccountsInterface;
import model.AccountsContainer;

//import the container class interface and the implementation
//Handles and acts a storage for the Products
import model.ProductsInterface;
import model.ProductsContainer;

import controller.SimilarAccFactoryInfc;
import controller.SimilarAccFactoryImpl;

//an array list package used for manipulating and storing clients' cart items
import java.util.ArrayList;

//necssary packages for the rmi packages
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.*;
import java.rmi.registry.*;

public class ClientFrontController {


    //A singleton object
    //There can only be one front controller object
    private static ClientFrontController uniqueInstance;

    //A factory variable is that produce account model when needed
    private SimilarAccFactoryInfc accountsFactory;
    //the dispatcher variable that handles and processes user request
    private ClientDispatcher dispatcher;

    //a customer variable account that stores users session
    //Also used to check for permissions 
    //when users are about to perform certain operation
    private CustomerAccount sessionHolder;
    private CustomerAccount loggedInUser;

    //The Authorization object 
    private Authorization auth;

    //An arraylist of products variable that will be used
    //by the client to store items added to cart before checkout
    private ArrayList<ProductInterface> cart;

    //holds the connection from the rmi registry
    private AccountsInterface stub;

    private ProductsInterface stubProducts;

    /**
     * Constructor
     */
    private ClientFrontController(){
        try{
        //initiate the account factory
        this.accountsFactory = new SimilarAccFactoryImpl();

        //initiate the session holder
        sessionHolder = new CustomerAccount();
        //initiate the dispatcher objetc
        //dispatcher = new ClientDispatcher();

        dispatcher = ClientDispatcher.getInstance();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * startClientFrontCrontroller
     * start the while loop until user quits
     */
    public void startClientFrontCrontroller(){

        Scanner readObj = new Scanner(System.in);
        System.out.println("welcome----");
         while(true){

            //user can enter L/l,S/s, O/o to login , sign up and exit out of the system
            System.out.println("Enter L/l to login");
            System.out.println("Enter S/s to sign up");
            System.out.println("please enter O/o to shut down server");

            //read user inputs
            String clientOption = readObj.nextLine();
            
            if( clientOption.compareTo("L") == 0 || clientOption.compareTo("l") == 0 ){

                try{
                    //connect to the registry and process the request
                    String name = "//in-csci-rrpc01.cs.iupui.edu:2044/OnlineStore";
                    stub = (AccountsInterface)Naming.lookup(name);
                    this.dispatchLoginRequest(stub);
                }catch(Exception e){
                    System.out.println("Login failure");
                }

                while(true){

                    System.out.println("Enter K/k to add items to cart");
                    System.out.println("Enter V/v to view store products");
                    System.out.println("Enter P/p to check out items products");
                    System.out.println("Enter E/e to exit");

                    String clientToDo = readObj.nextLine();

                    if ( clientToDo.compareTo("K") == 0 ||  clientToDo.compareTo("k") == 0 ){
                        try{
                            //connect to the registry and process the request
                            String name = "//in-csci-rrpc01.cs.iupui.edu:2045/OnlineStore";
                            stubProducts = (ProductsInterface)Naming.lookup(name);

                            this.dispatchPurchaseitemRequest(stubProducts);
                          
                        }catch(Exception e){
                            System.out.println("could not connect to products registry");
                            System.out.println(e.getMessage());
                        }
                    }

                    else if( clientToDo.compareTo("P") == 0 ||  clientToDo.compareTo("p") == 0 ){
                        this.disptatchCheckoutItems(this.cart);
                    }
                  
                    else if( clientToDo.compareTo("V") == 0 ||  clientToDo.compareTo("v") == 0 ){
                        try{
                            //connect to the registry and process the request
                            String name = "//in-csci-rrpc01.cs.iupui.edu:2045/OnlineStore";
                            stubProducts = (ProductsInterface)Naming.lookup(name);
                            ProductsInterface viewOnly = (ProductsInterface)stubProducts.clone();
                            this.dispatchProductDisplayRequest(viewOnly);
                            
                        }catch(Exception e){
                           System.out.println("coud not connect to server");
                        }
                    }
                    else if( clientToDo.compareTo("E") == 0 ||  clientToDo.compareTo("e") == 0 ){
                        
                        System.exit(0);
                        break;
                    }
                    
                }



                continue;
            }   
            else if( clientOption.compareTo("S") == 0 || clientOption.compareTo("s") == 0 ){

                try{
                    //connect to the registry and process the request
                    String name = "//in-csci-rrpc01.cs.iupui.edu:2044/OnlineStore";
                    stub = (AccountsInterface)Naming.lookup(name);
                    this.dispatchSignUpRequest(stub);
                }catch(Exception e){
                    
                }

                continue;
            } 
            else if( clientOption.compareTo("O") == 0 || clientOption.compareTo("o") == 0 ){
                System.out.println("signing out ------------");
                System.exit(0);
            } 
            else {
                System.out.println("inalid option");
                continue;
            }
        }
    }

    /**
     * disptatchCheckoutItems(ArrayList<ProductInterface> cart)
     */
    public void disptatchCheckoutItems(ArrayList<ProductInterface> cart){
        this.dispatcher.handleItemPurchaseRequest(this.loggedInUser, cart);
    }  

    /**
     * dispatchProductDisplayRequest
     * param[in] ProductsInterface stub
     */
    public void dispatchProductDisplayRequest(ProductsInterface stub) {

         if(this.isAuthenticUser() != true){
                System.out.println("ypu are not allowed to perform operation");
                return;
            }
        try{
            this.dispatcher.handleProductsDisplayRequest(stub);
        }catch(Exception e){

        }
    }

    /**
     * isAuthenticUser
     * returns true or false if a user is allowed to perform operation
     */
    public boolean isAuthenticUser(){
        //first check if user is permiited to do so;
        try{
            auth = new Authorization(this.loggedInUser.getType(), "cust");
            return auth.userPermission();
        }catch(Exception e){
            System.out.println("");
        }
        return false;
    }

     /**
     * * dispatchPurchaseitemRequest
     * param[in] ProductsInterface stub
     */
    public void dispatchPurchaseitemRequest(ProductsInterface stub){

        //check for user permission
         if(this.isAuthenticUser() != true){
                System.out.println("ypu are not allowed to perform operation");
                return;
            }
        
        //if permitted
        try{

            System.out.println("you are authorized to perform this operation");

            System.out.println("you have this amount in account: "+ this.loggedInUser.getFunds());
            System.out.println("These are the available products");
            this.dispatchProductDisplayRequest(stub);

            //create a new cart for user
           this.cart = new ArrayList<ProductInterface>();

            //take input
            Scanner readObj = new Scanner(System.in);
            System.out.println("please Enter the name of items you would like to buy");
            String name = readObj.nextLine();

            System.out.println("please Enter the quantity");
            int quantity = readObj.nextInt();

            ProductInterface prod = stub.getProduct(name, quantity);

            //check if product exist
            if(prod != null){
                System.out.println("Attempting to add items to cart");
                this.dispatcher.handleAddingItemsToCart(this.cart, prod);
                System.out.println("Enter P/p whenever you are ready to checkout");
            } else{
                System.out.println("product was not added to cart");
                System.out.println("Enter items withing range of availability");
                return;
            }

        }catch(Exception e){
            System.out.println("could not add item to cart");
            System.out.println("Please make sure item is in inventory");
           
        }
    }
    /**
     * * dispatchSignUpRequest
     * param[in] AccountsInterface stub
     */
    public void dispatchSignUpRequest(AccountsInterface stub){

       try {
            Scanner readObj = new Scanner(System.in);
            System.out.println("Welcome to the account creation portal\n");

            System.out.println("please Enter your prefered username");
            String username = readObj.nextLine();

            System.out.println("please Enter your address");
            String addr = readObj.nextLine();

            String type = "cust";

            System.out.println("please Enter a four digit number for password");
            int password = readObj.nextInt();
            
            this.sessionHolder = new CustomerAccount();

            dispatcher.handleSignUprequest(username, addr, type, password, this.sessionHolder, stub);
       }catch(Exception e){
            System.out.println("Could not dispatch cusomer sign up request");
       }
    }

    /**
     * dispatchLoginRequest
     * param[in] AccountsInterface stub
     */
    public void dispatchLoginRequest(AccountsInterface stub){


        try{
         Scanner readObj = new Scanner(System.in);

         System.out.println("Please enter you username");
         String username = readObj.nextLine();

         System.out.println("please enter your passsword");
         int password = readObj.nextInt();
      
        Account user = stub.getUserAccount(username, password, "cust");

        if(user != null){
            this.loggedInUser = new CustomerAccount();
            this.loggedInUser.setName(user.getName());
            this.loggedInUser.setAddress(user.getAddress());
            this.loggedInUser.setPassword(user.getPassword());
            this.loggedInUser.setType(user.getType());
            this.loggedInUser.setFunds(new Double(1000));
            this.dispatcher.handleLoginRequest(stub, this.sessionHolder, username, password);
            System.out.println("you have been awarded unlimited funds");
        } else{
            System.out.println("user does not exist");
            return;
        }

        

      } catch(Exception e){
         System.out.println("dispatch Login Request error");
         System.out.println(e.getMessage());
      }
    }

    //getInstance
    //returns singleton object 
    //There can be only one instance of this object
    //One front controller for the client side
    public static synchronized ClientFrontController getInstance(){
         if (uniqueInstance == null){
            uniqueInstance = new ClientFrontController();
        }

        return uniqueInstance;
    }


}