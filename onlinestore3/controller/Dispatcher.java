//-*- java -*-
//$file: Dispatcher.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller

package controller;
import controller.Command;
import controller.CreateAdminCommand;
import controller.LoginCommand;
import controller.AddNewAccountCommand;
import controller.AddProductCommand;
import controller.RemoveProductCommand;
import controller.UpdateProductCommand;
import controller.ViewProductsCommad;

import model.AccountsInterface;
import model.AccountsContainer;

import controller.AccountsFactoryInterface;
import controller.AccountsFactoryImplementation;

import controller.Observer;
import controller.AccountsObserver;

import controller.Authorization;

import model.Account;
import model.AdminAccount;

import model.ProductsInterface;
import model.ProductsContainer;

import model.ProductInterface;
import model.Product;

//Arraylist
import java.util.ArrayList;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import java.rmi.*;
import java.rmi.registry.*;

//Scanner for input
import java.util.Scanner;


public class Dispatcher{
    //a unique singleton object
    private static Dispatcher uniqueInstance;

    static AccountsFactoryInterface factory = new AccountsFactoryImplementation();
    private AccountsInterface accs;

    private Command cmd;

    //authorization object for verification of users
    //used to check if users exists before calling the login command
    private Authorization auth;

    //An observer that is notified when an account is created and then sent to thr rmi registry
    private Observer ob;

    private Account sessionHolder;

    private ProductsInterface products;

    //an array list of commands to be executed later
    private ArrayList<Command> adminJobsToLater = new ArrayList<Command>();

    //constructor
    //made private because i am using the singleton pattern
    //to make sure there is only one instance of this object
    private Dispatcher(){

    }

    //
    //handleSignUprequest
    //handles a user sign up request
    //
    public void handleSignUprequest(String username, String addr, String type, int password,  
    Account sessionHolder, AccountsInterface stub)
    {
        cmd  = new CreateAdminCommand(username, addr, type, password, sessionHolder,stub);
        this.processCommand(cmd);
        //cmd.execute();
    }

    //
    //handleLoginRequest
    //handles login request
    public void handleLoginRequest(AccountsInterface stub, Account sessionHolder, String username, int password){

        cmd = new LoginCommand(stub, sessionHolder, username, password, "admin");
        //cmd.execute();
        this.processCommand(cmd);
        
    }

    //
    //handleUpdateProductRequest
    //process updating product
    public void handleUpdateProductRequest(String name, String desc, double price, int quantity, ProductsInterface stub ){
        cmd = new UpdateProductCommand(name, desc, price, quantity, stub);
        //cmd.execute();
        this.processCommand(cmd);
    }
    //
    //handleAddingNewProductRequest
    //process adding new product
    public void handleAddingNewProductRequest(String name, String desc, double price, int quantity, ProductsInterface stub){
        cmd = new AddProductCommand(name, desc, price, quantity, stub);
        this.processCommand(cmd);
        //cmd.execute();
    }
    //
    //handleAddingNewAccount
    //process new adding of account
    public void handleAddingNewAccount(AccountsInterface stub, String username, String addr, String type, int passsword, Account acc){
        cmd = new AddNewAccountCommand(stub, username, addr, type, passsword, acc);
        this.processCommand(cmd);
        //cmd.execute();
    }
    //
    //handleRemoveItemRequest
    //remove item 
    public void handleRemoveItemRequest(ProductsInterface stub, String nameOfProduct){
        cmd = new RemoveProductCommand(stub, nameOfProduct);
        this.processCommand(cmd);
        //cmd.execute();
    }
     //
    //removeCertainAccountRequest
    //removes account
    public void removeCertainAccountRequest(AccountsInterface stub, String username, String type, int password){
        cmd = new RemoveAccountCommand(stub, username, type, password);
        this.processCommand(cmd);
        //cmd.execute();
    }
    //
    //handleProductsDisplayRequest
    //
    public void handleProductsDisplayRequest(ProductsInterface stub){
        try {
            cmd = new ViewProductsCommad(stub);
            this.processCommand(cmd);
        }catch(Exception e){
            System.out.println("Command execution failed");
        }
    }
     //
    //processCommand
    //takes command as a parameter and executes them
     public void processCommand(Command cmd){

        try{
            cmd.execute();
        }catch(Exception e){
            System.out.println("command could not be executed");
        }
    }

     //
    //getInstance
    //returns a single instance of this object
    //synchronized
    public static synchronized Dispatcher getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Dispatcher();
        }

        return uniqueInstance;
   }
    


}