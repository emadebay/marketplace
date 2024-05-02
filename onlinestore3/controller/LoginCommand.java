//-*- java -*-
//$file: Login.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller


package controller;
import java.util.Scanner;

import model.AccountsInterface;
import model.AccountsContainer;

import controller.Authorization;

import model.Account;
import model.AdminAccount;
import model.CustomerAccount;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import java.rmi.*;
import java.rmi.registry.*;

public class LoginCommand implements Command{

    String username;
    int password;
    String type;
    
    Account loggedInUser;

    //
    //LoginCommand
    //
    public LoginCommand(AccountsInterface stub, Account sessionHolder, String username, int password, String type){
      //check for user
      try {
        this.loggedInUser = stub.getUserAccount(username, password,  type);

        if(this.loggedInUser != null){

          sessionHolder.setName(this.loggedInUser.getName());
          sessionHolder.setType(this.loggedInUser.getType());
          sessionHolder.setAddress(this.loggedInUser.getAddress());
          sessionHolder.setPassword(this.loggedInUser.getPassword());

        }

      }
      catch (Exception e){
        System.out.println("create login command error--------");
        System.out.println(e.getMessage());
      }

    }

    
    public void execute(){
      
      try {

            if(this.loggedInUser != null){

              System.out.println("Succesfully logged in");
              System.out.println("welcome "+ this.loggedInUser.getName());
              System.out.println("you are signed as:  "+ this.loggedInUser.getType());
            } else{
              System.out.println("could not retrieve account information");
              System.out.println("Please check credentials again");
            }
            
        }catch(Exception e){
            System.out.println("Something has gone wrong ------ please follow the prompt and try again");
            System.out.println("Server err: " + e.getMessage());
            //e.printStackTrace();
        }
       
    }

}