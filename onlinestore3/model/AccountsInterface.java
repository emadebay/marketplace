//-*- java -*-
//$file: AccountsInterface.java 2023-03-25 
//Author: Emmanuel Adebayo
//====================================================

/**
    @interface: Account
    This interface defines some key functionality and structure of what an AccountsContainer should look like
    for both an admin and a regular client
 */

package model;
//imported the necessary rmi package
import java.rmi.*;
import java.util.ArrayList;
import model.Account;
import model.AdminAccount;
import model.CustomerAccount;

//imported the observer interface
//please refer to the concrete implementation (AccountsContainer.java) of this class to see why it is needed
import controller.Observer;

public interface AccountsInterface extends Remote {


    /**
    addUser(Account admin)
     */
    public void addUser(Account admin) throws RemoteException;

    /**
    addObserver(Observer obs)
    */
    public void addObserver(Observer obs) throws RemoteException;

    // /**
    // ifAdminExist(String name)
    // */
    // public boolean ifAdminExist(String name) throws RemoteException;

    // /**
    // getAdmin() 
    // */
    // public Account getAdmin() throws RemoteException;


    //getUserAccounts
    public ArrayList<Account> getUserAccounts() throws RemoteException;

    public void removeAccount(String username, String type, int password) throws RemoteException;

    //getUserAccount
    public Account getUserAccount(String username, int password, String type) throws RemoteException;

    //
    //showUsers
    //
    public void showUsers() throws RemoteException;
    
    


}
