//-*- java -*-
//$file: CreateAdminCommand.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller
//purpse: use the  command pattern to create an admin
//====================================================


package controller;
import java.util.Scanner;

import controller.SimilarAccFactoryInfc;
import controller.SimilarAccFactoryImpl;

import model.Account;
import model.AdminAccount;

import model.AccountsInterface;
import model.AccountsContainer;



public class CreateAdminCommand implements Command{

    //SimilarAccFactoryInfc factory = new SimilarAccFactoryImpl();
    private Account sessionHolder;
    private AccountsInterface stub;
    
    //CreateAdminCommand
    //param[in] username, addr, type, password, sessionholder, stub
    public CreateAdminCommand(String username, String addr, String type, int password,  
        Account sessionHolder, AccountsInterface stub)
    {   
        //set the session holder
        try{
            sessionHolder.setName(username);
            sessionHolder.setAddress(addr);
            sessionHolder.setType(type);
            sessionHolder.setPassword(password);

            //stub.addUser(sessionHolder);
            this.stub = stub;
            this.sessionHolder = sessionHolder;
        }catch(Exception e){
            System.out.println("create admin command error--------");
            System.out.println(e.getMessage());
        }

    }

    //
    //execute
    //add user to the stub container
    public void execute(){

        try{
            System.out.println("Attempting to add user to the system----------------");
            this.stub.addUser(this.sessionHolder);
            System.out.println("User has been added to the system");
        }catch(Exception e){
            System.out.println("User could not be added");
        }
        
    }

  
}