//-*- java -*-
//$file: CreateCustomerCommand.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller


package controller;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import java.rmi.*;
import java.rmi.registry.*;

import controller.Command;

import model.AccountsInterface;
import model.AccountsContainer;

import model.Account;
import model.CustomerAccount;

public class CreateCustomerCommand implements Command {
    private AccountsInterface stub;
    private Account sessionHolder;

    //
    //CreateCustomerCommand
    public CreateCustomerCommand(String username, String addr, String type, int password,  
        Account sessionHolder, AccountsInterface stub) 
    {
        //set the sessionholder
        try{
            sessionHolder.setName(username);
            sessionHolder.setAddress(addr);
            sessionHolder.setType(type);
            sessionHolder.setPassword(password);

            //stub.addUser(sessionHolder);
            this.sessionHolder = sessionHolder;
            this.stub = stub;
        }catch(Exception e){
            System.out.println("create admin command error--------");
            System.out.println(e.getMessage());
        }
    }

    //
    //
    //execute
    public void execute() {
        //add user to the container of users
        try{
            System.out.println("Attempting to add customer to the system-------------------");
            this.stub.addUser(this.sessionHolder);
            System.out.println("customer added to the system");

        }catch(Exception e){
            System.out.println("Create Customer Command error: " + e.getMessage());
 		    
        }

    }
}