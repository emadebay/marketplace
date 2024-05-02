//-*- java -*-
//$file: Authorization.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller
//purpse: handles and checks users privileges 
//====================================================

package controller;
import model.AccountsInterface;
import model.Account;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import java.rmi.*;
import java.rmi.registry.*;

import java.util.ArrayList;

public class Authorization {

    //stores the type of user in process
    private String type;
    //stores the expected type of user in process
    private String expected;
    public Authorization(String type, String expected){
       this.type = type;
       this.expected = expected;
    }

    /**
     * userPermission
     * 
     */
    public boolean userPermission(){

        //returns true if type and expected match
        //otherwise a user is not allowed to perform certain operation
        return this.type.compareTo(this.expected) ==0;
       
    }
}