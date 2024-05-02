//-*- java -*-
//$file: Account.java 2023-03-25 
//Author: Emmanuel Adebayo
//====================================================

/**
    @interface: Account
    This interface defines some key functionality and structure of what an account should look like
    for both an admin and a regular client
 */

package model;
//imported the necessary rmi package
import java.rmi.*;


public interface Account extends Remote {

    /**
    getType()
    @param[in]
    return String
     */
    public String getType() throws RemoteException;
    /**
    getName()
    @param[in]
    return String
     */
    public String getName() throws RemoteException;
    /**
    getAddress()
    @param[in]
    return String
     */
    public String getAddress() throws RemoteException;

    /**
    getPassword()
    @param[in]
    return String
     */
    public int getPassword() throws RemoteException;

     /**
    setType()
    @param[in]
     */
    public void setType(String type) throws RemoteException;
     /**
    setName()
    @param[in]
     */
    public void setName(String name) throws RemoteException;
     /**
    setAddress()
    @param[in]
     */
    public void setAddress(String addr) throws RemoteException;

    /**
    setPassword()
    @param[in] 
     */
    public void setPassword(int password) throws RemoteException;

}