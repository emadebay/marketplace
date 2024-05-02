//-*- java -*-
//$file: AddNewAccountCommand.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller
//purpse:Implements the command interface
//====================================================


/**
 * This class uses the command pattern to add new item to the store inventory list
 */


package controller;

import java.util.Scanner;

//Accounts Factory
import controller.SimilarAccFactoryInfc;
import controller.SimilarAccFactoryImpl;

//Account models
import model.Account;
import model.AdminAccount;

//Acounts interface and implementation
import model.AccountsInterface;
import model.AccountsContainer;

import controller.Observer;

public class AddNewAccountCommand implements Command {

    private AccountsInterface stub;
    private Account acc;

    /**
     * AddNewAccountCommand
     * param[in] AccountsInterface stub, String username, 
     * param[in] String addr, String type, int password, Account acc
     */

    public AddNewAccountCommand(AccountsInterface stub, String username, String addr, String type, int password, Account acc){
        try {
            
            //set account name
            acc.setName(username);
            acc.setAddress(addr);
            acc.setType(type);
            acc.setPassword(password);

            //initialize stub and acc
            this.stub = stub;
            this.acc = acc;

        }catch(Exception e){
            System.out.println("Operation failed");
            e.printStackTrace();
        }
    }

    /**
     * execute()
     * displays confirmation message
     */
    public void execute(){

        //add user to the accounts container
        try {
            this.stub.addUser(this.acc);
           System.out.println("New account added to container");

        }catch(Exception e){
            System.out.println("could not create account for user");
            e.printStackTrace();
        }
       
    }
}