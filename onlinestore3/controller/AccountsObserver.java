//-*- java -*-
//$file: AccountsObserver.java 2023-03-25 
//Author: Emmanuel Adebayo
//====================================================
/**
    @class AccountsObserver
    This class is concrete implementation of the Observer interface
    The purpose of this class is to use observer pattern to listen to changes in the AccountsContainer class
    Please refer to AccountsContainer.java for more details
*/


package controller;

//imported the rmi packages
import java.rmi.*;
import java.rmi.registry.*;
//imported the accounts interface and concrete implementation (container)
import model.AccountsInterface;
import model.AccountsContainer;

//The interface of which this class impelements
import controller.Observer;

//AccountsObserver
public class AccountsObserver implements Observer{

     //A private Accounts 
    //This variable will be sent to the rmi registry
    //Whenever the update function is called from the AccountsContainer class
    private AccountsInterface subj;
    
     /**
        @Constructor()
        @param[in] AccountsInterface subject

     */
    public AccountsObserver(AccountsInterface subject){
        this.subj = subject;
    }

    /**
    update()
    sends the private acccount to the rmi registry
    as soon as notify method is called from the AccountsContainer
    */
    public void update(){
        //send accounts
        try {
            String name = "//localhost:2044/OnlineStore";
		    Naming.rebind(name,this.subj);
            System.out.println("Admin added and ready\n");
        }catch(Exception e){
            System.out.println("server could not be updated");
            System.out.println("Server err: " + e.getMessage());
 		    e.printStackTrace();
        }
    }
}
