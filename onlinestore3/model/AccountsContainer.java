//-*- java -*-
//$file: AccountsContainer.java 2023-03-25 
//Author: Emmanuel Adebayo
//====================================================

/**
    @class: AccountsContainer
    This class is a concrete implementation of the AccountsInterface
    it implements the methods defined in the interface
*/


package model;

import java.io.*;

//imported the necessary rmi package
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;

//imported the Account interface and concrete implementations to be used
import model.Account;
import model.AdminAccount;
import model.CustomerAccount;


//Imported the observer generalizations and concrete implmentation
import controller.Observer;
import controller.AccountsObserver;

public class AccountsContainer extends UnicastRemoteObject implements AccountsInterface, java.io.Serializable{
    //a private array of adminaccount
    //This array holds all of the registered admin account
    private ArrayList<Account> accounts =  new ArrayList<Account>();


    //a private array of observers
    //This array holds all of the registered observers
    private ArrayList<Observer> obsevers =  new ArrayList<Observer>();

    //a private admin Account to be returned when called upon
    private Account admin;

    public AccountsContainer()throws RemoteException{
        super();
    }
    
    //
    //returns the array of Account
    //
    public ArrayList<Account> getUserAccounts() {
        return this.accounts;
    }


    /**
    addUser(Account admin) --- add admin to admin list and notifies observer
    @param[in] admin
     */
    public void addUser(Account admin) {
        
        try {
            //tries adds admin into the array of adminAccount
            accounts.add(admin);
            System.out.println(admin.getName()+ ": has been added: " + admin.getType());

            //If it is succesful in added it into the array of adminAccount
            //The list of observers are notified 
            //Their update() methods are called
            //The chnages are reflected are a new Accounts object is sent to the rmi registry
            this.notifyObservers();

            //clear the observers
            //no more observers
            obsevers.clear();

        } catch(Exception e) {
            //Otherwise, if it fails
            //handles error
			System.out.println("Server err: " + e.getMessage());
 		    e.printStackTrace();
		}
    }

    //
    //removeAccount
    //loops through list and delets account object
    public void removeAccount(String username, String type, int password) {
        try {
            for(Account acc : accounts){
                if(acc.getName().compareTo(username) == 0 && acc.getType().compareTo(type)==0 && acc.getPassword() == password){

                    this.accounts.remove(acc);
                    System.out.println("Account deleted");
                    break;
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
    addObserver(Observer obs) ---- adds observer to the observer list
    @param[in]
    */
    public void addObserver(Observer obs){

        obsevers.add(obs);
	    System.out.println("Added observer");
    }

    /**
    notifyObservers() ---- loops through the list of observes 
    and calls their update method
    Their update() method sends a new and updated instance of the
    AccountsContainer to the rmi registry
    */
    public void notifyObservers(){
        if(obsevers.size() != 0){
            for (Observer obs : obsevers){
                obs.update();
                System.out.println("observer notified");
            }
        }
    }

    //
    //getUserAccount
    //returns an Account object
    public Account getUserAccount(String username, int password, String type) {

        Account admin = null;
        try {
            for(Account acc : accounts){
                if(acc.getName().compareTo(username) == 0 && acc.getType().compareTo(type)==0 && acc.getPassword() == password){

                    admin = acc;
                    return admin;
                    
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return admin;
    }

    /**
     * showUsers
     * displays users list
     */
    public void showUsers() {
        try {
            for(Account acc : accounts){
               System.out.println("account holder name: "+ acc.getName());
               System.out.println("account holder type: "+ acc.getType());
               System.out.println("account holder address: "+ acc.getAddress());
               System.out.println("account holder password: "+ acc.getPassword());
            }
        }catch(Exception e){
            System.out.println("could not show users");
        }
    }
}
