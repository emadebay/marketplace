//-*- java -*-
//$file: RemoveAccountCommand.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller

package controller;

import java.util.Scanner;
import controller.Command;

import controller.SimilarAccFactoryInfc;
import controller.SimilarAccFactoryImpl;

import model.Account;
import model.AdminAccount;

import model.AccountsInterface;
import model.AccountsContainer;

//
//RemoveAccountCommand
//handles remover of account
public class RemoveAccountCommand implements Command{
  
    private AccountsInterface stub;
    private String username;
    private String type;
    private int password;
    //
    //constructor
    //
    public RemoveAccountCommand(AccountsInterface stub, String username, String type, int password){
       
       try{

            //stub.removeAccount(username, type, password);
            this.stub = stub;
            this.username = username;
            this.type = type;
            this.password  = password;
       }catch(Exception e){
            System.out.println("error occured while attempting to remove account");
        }

    }

    //execute
    //
    //removes account
    public void execute(){
        try {
            System.out.println("Attempting to remove account--------");
            
            this.stub.removeAccount(this.username, this.type, this.password);
        }catch(Exception e){
            System.out.println("Error occured while attempting to remove account");
            e.printStackTrace();
        }
    }
}