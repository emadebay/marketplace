//-*- java -*-
//$file: AccountsFactoryInterface.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller
//====================================================
/**
    @interface: AccountsFactoryInterface
    This interface defines some key functionality of the classes that implements it
    It uses abstract factory pattern
 */


package controller;
import model.AccountsInterface;
import model.AccountsContainer;


public interface AccountsFactoryInterface{

    //createAccountsContainer
    //abstract method to be implemented
    public AccountsInterface createAccountsContainer();

    
}