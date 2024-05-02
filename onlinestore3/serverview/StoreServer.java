/***
 * 
 * Emmanuel Adebayo
 * Package: serverview
 * File: StoreServer.java
 * Use:
 */


package serverview;

import java.util.Scanner;

import controller.ServerFrontController;

import model.ProductsInterface;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import java.rmi.*;
import java.rmi.registry.*;

public class StoreServer {
  
    
    static ServerFrontController sfc;

    public static void main(String args[]){
        
        try {

            // sfc =  new ServerFrontController();
            sfc = ServerFrontController.getInstance();
            sfc.startFrontController();
            System.out.println("Front controller started");
        }catch(Exception e){
            System.out.println("Front controller could not started");
            System.exit(0);
        }
        
    }

}
