//-*- java -*-
//$file: AdminAccount.java 2023-03-25 
//Author: Emmanuel Adebayo
//====================================================

/**
    @class: AdminAccount
    This class is a concrete implementation of the Account Interface
    it implements the methods defined in the interface
    please refer to Account.java in the model package
*/

package model;

import java.io.*;

//imported the necessary rmi package
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class AdminAccount extends UnicastRemoteObject implements Account, java.io.Serializable{

    //stores admin name
    private String name;
    //stores admin address
    private String addr;
    //stores Account type
    private String type;

    //stores password
    private int password;


    //construction
    /**
    constructor ----- 
     */
    public AdminAccount()throws RemoteException{
		super();
        
	}

    //getters
     /**
    getType() return Account type
    @param[in]
    return String
     */
    public String getType() {
        return type;
    }
     /**
    getName() returns admin name
    @param[in]
    return String
     */
    public String getName() {
        return name;
    }
     /**
    getAddress() return Admin address
    @param[in]
    return String
     */
    public String getAddress() {
        return addr;
    }


    /**
    getPassword()
    @param[in]
    return String
     */
    public int getPassword() {
        return this.password;
    }

    //Setters
    /**
    setType()
    @param[in]
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
    setName()
    @param[in]
     */
    public void setName(String name) {
        this.name = name;
    }

    //Setters
    /**
    setAddress()
    @param[in]
     */
    public void setAddress(String addr) {
        this.addr = addr;
    }

    
    /**
    setPassword()
    @param[in] 
     */
    public void setPassword(int password) {
        this.password = password;
    }

}