//-*- java -*-
//$file: ClientDispatcher.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller
//purpse: uses the front controller pattern to mediate and direct traffic when needed
//====================================================

package controller;

import java.util.Scanner;
import controller.Command;
import controller.CreateAdminCommand;
import controller.LoginCommand;

import controller.CreateCustomerCommand;
import controller.PurchaseCommand;
import controller.AddItemCommand;
import controller.ViewProductsCommad;

import model.AccountsInterface;
import model.AccountsContainer;

import model.ProductsInterface;
import model.ProductsContainer;

import model.ProductInterface;
import model.Product;

import java.util.ArrayList;

import java.util.Scanner;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.*;
import java.rmi.registry.*;


import controller.Observer;

import model.Account;
import model.CustomerAccount;


public class ClientDispatcher {

    //a sungleton object
    private static ClientDispatcher uniqueInstance;

    //a private observer object 
    private Observer ob;

    private ProductsInterface prods;
    
    //command reference when necessary
    private Command cmd;


    //an array of commands related to checking out
    //used and deallocated when items are checked out
    private ArrayList<Command> jobs;

    /***
     * ClientDispatcher
     */

    public static synchronized ClientDispatcher getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new ClientDispatcher();
        }

        return uniqueInstance;
    }
    private ClientDispatcher(){
        this.jobs = new ArrayList<Command>();
    }

    /**
     * handleItemPurchaseRequest
     * param[in]: CustomerAccount cust, ArrayList<ProductInterface> items
     */
    public void handleItemPurchaseRequest(CustomerAccount cust, ArrayList<ProductInterface> items )
    {
       try{
            if(this.jobs.size() >= 1){
                cmd = new PurchaseCommand(true);
                this.jobs.add(cmd);
                //cmd.execute();

                //loop through jobs and execute the commands
                for(Command command: this.jobs){
                    //command.execute();
                    this.processCommand(cmd);
                }
                //clear items cart array
                items.clear();
                //clear command array
                jobs.clear();
                
            } else {
                //other wise 
                //purchase can not be made
                cmd = new PurchaseCommand(false);
                //cmd.execute();
                this.processCommand(cmd);
            }
            
       }catch(Exception e){
        System.out.println("error in client dispatcher ------- purchasing items");
        System.out.println(e.getMessage());
       }
    }

    /**
     * handleSignUprequest
     * handles and redirect signing up process
     */
    public void handleSignUprequest(String username, String addr, String type, int password,  
    CustomerAccount sessionHolder, AccountsInterface stub)
    {
        cmd  = new CreateCustomerCommand(username, addr, type, password, sessionHolder,stub);

        //System.out.println
        //cmd.execute();
        this.processCommand(cmd);
    }

    /**
     * handleLoginRequest
     * handles and redirect loggin in process
     */
    public void handleLoginRequest(AccountsInterface stub, CustomerAccount sessionHolder, String username, int password){

        cmd = new LoginCommand(stub, sessionHolder, username, password, "cust");
        this.processCommand(cmd);
        //cmd.execute();
        
    }


    /**
     * handleAddingItemsToCart
     * handles and redirect adding items to cart process
     */
    public void handleAddingItemsToCart(ArrayList<ProductInterface> cart, ProductInterface prod){

        try{
            cmd  = new AddItemCommand(cart, prod);

            jobs.add(cmd);
        }catch(Exception e){
            System.out.println("Error adding items to cart");
            System.out.println(e.getMessage());
        }

    }

    /**
     * handleProductsDisplayRequest
     * displays products
     */
    public void handleProductsDisplayRequest(ProductsInterface stub){
        //takes the stub as a parameter
        //displays the products in the container
        //
        // try{
        //     ArrayList<ProductInterface> prods = stub.getProducts();
        //      int i = 1;
        //     for (ProductInterface prod : prods){

        //         System.out.println("Item" + i);
        //         System.out.println("product name: "+prod.getName());
        //         System.out.println("product quantity: "+prod.getAvailableQuantity());
        //         System.out.println("product description "+prod.getDescription());
        //         System.out.println("product price: $"+prod.getPrice());

        //         System.out.println("\n");
        //         i++;

        //         System.out.println();
        //     }
        // }catch(Exception e){

        // }

        cmd  = new ViewProductsCommad(stub);
        this.processCommand(cmd);
        //cmd.execute();
    }

    public void processCommand(Command cmd){

        try{
            cmd.execute();
        }catch(Exception e){
            System.out.println("command could not be executed");
        }
    }

  
}