//-*- java -*-
//$file: SimilarAccFactoryImpl.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller

package controller;

import controller.SimilarAccFactoryInfc;
import model.Account;
import model.AdminAccount;
import model.CustomerAccount;


public class SimilarAccFactoryImpl implements SimilarAccFactoryInfc{

    //
    //SimilarAccFactoryImpl
    //
    public SimilarAccFactoryImpl (){

    }
    //
    //createAdminAccount
    //get AdminAccount object
    public AdminAccount createAdminAccount(){
        AdminAccount admin = null;
        try {
            admin = new AdminAccount();
        } catch(Exception e){
            System.out.println("error occured in creating account");
        }

        return admin;
    }

    //
    //createCustomerAccount
    //get CustomerAccount object
    public CustomerAccount createCustomerAccount(){
        CustomerAccount cust = null;
        try {
            cust = new CustomerAccount();
        
        }
        catch(Exception e){
            System.out.println("Error occured in creating account");
        }
        return cust;
    }
}