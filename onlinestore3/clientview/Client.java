/***
 * 
 * Emmanuel Adebayo
 * Package: clientView
 * File: Client.java
 * Use: Entry point for the client interaction with the client side of the program
 * It houses the ClientFrontController.java which dispatches the user requests
 */




package clientview;

//import the java rmi remote packages
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.*;
import java.rmi.registry.*;

//scanner for input;
import java.util.Scanner;

//import the ClientFrontController
import controller.ClientFrontController;

//The main program 
public class Client {
    //A private for for the front controller
    private static ClientFrontController cfc;
    public static void main(String args[]) {

        //The front controller is initiated and started
        // A singleton object
        //There can only be on front controller while program is running
        cfc = ClientFrontController.getInstance();
        cfc.startClientFrontCrontroller();


    }
 

    
}


