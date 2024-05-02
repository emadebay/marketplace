//-*- java -*-
//$file: CustomerAccount.java 2023-03-25 
//Author: Emmanuel Adebayo
//====================================================

/**
    @class: CustomerAccount
    This class is a concrete implementation of the Account Interface
    it implements the methods defined in the interface
    please refer to Account.java in the model package
 */

package model;
import java.io.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerAccount extends UnicastRemoteObject implements  Account, java.io.Serializable{

    //stores admin name
    private String name;
    //stores admin address
    private String addr;
    //stores Account type
    private String type;
     //stores password
    private int password;

    private Double funds;
    
    //construction
    /**
    constructor ----- 
     */
    public CustomerAccount()throws RemoteException{
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
    getName() returns Customer name
    @param[in]
    return String
     */
    public String getName() {
        return name;
    }

     /**
    getAddress() return Customer address
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


    public Double getFunds(){
        return this.funds;
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

    public void setFunds(Double funds){
        this.funds = funds;
    }
}