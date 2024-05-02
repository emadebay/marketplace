//-*- java -*-
//$file: AccountsFactoryImplementation.java 2023-03-25 
//Author: Emmanuel Adebayo
//====================================================
/**
    @class AccountsFactoryImplementation
    This class is concrete implementation of the AccountsFactoryInterface
    The purpose of this class is to use the abstract factory pattern to create an Accounts object
    Which will shield the client/callee from the creation of the account object
 */
package controller;

//imported the AccountsInterface and AccountsContainer
import model.AccountsInterface;
import model.AccountsContainer;

public class AccountsFactoryImplementation implements AccountsFactoryInterface{
    //a private acccounts that is created and returned to the client
    private AccountsInterface acccounts;
    
    /**
    @createAccountsContainer() ---- creates and return an accountscontainer to be used
    @param[in] 

     */
    public AccountsInterface createAccountsContainer(){

        //create new account and return it
        //using the abstract factory pattern
        try {
            acccounts = new AccountsContainer();
        }catch(Exception e){
            System.out.println("Server err: " + e.getMessage());
 		    System.out.println("Container factory error");
        }
        return  acccounts;
    }

}
