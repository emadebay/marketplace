//-*- java -*-
//$file: SimilarAccFactoryInfc.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller

package controller;

import model.Account;
import model.CustomerAccount;
import model.AdminAccount;

public interface SimilarAccFactoryInfc {

     //
    //createAdminAccount
    //get AdminAccount object
    public AdminAccount createAdminAccount();

     //
    //createCustomerAccount
    //get CustomerAccount object
    public CustomerAccount createCustomerAccount();
}