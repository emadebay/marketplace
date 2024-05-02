//-*- java -*-
//$file: AddProductCommand.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller
//purpse:Implements the command interface
//====================================================


/***
 * This class uses the command pattern to add product the inventory list
 */
package controller;

import java.util.Scanner;

import model.ProductsInterface;
import model.ProductsContainer;

import model.ProductInterface;
import model.Product;

import controller.Command;

public class AddProductCommand implements Command{
    private ProductsInterface stub;
    private ProductInterface prod;
    /**
     * AddProductCommand
     * param[in] String name, String desc, double price, 
     * param[in] int quantity, ProductsInterface stub
     */
    public AddProductCommand(String name, String desc, double price, int quantity, ProductsInterface stub){
       
       try{

            this.prod = new Product();
            System.out.println("Attempting to add products to invetory list-------------------");
            //set product parameters
            prod.setName(name);
            prod.setDescription(desc);
            prod.setPrice(price);
            prod.setAvailableQuantity(quantity);

            this.stub = stub;
        
       }catch(Exception e){

            System.out.println("AddProductCommand error");
       }

    }

    /**
     * execute()
     * displays confirmation message
     */
    public void execute(){

        
        //add products to the products container
    
        try{
            System.out.println("Attempting to add products------------------");
            this.stub.addToProducts(this.prod);
            System.out.println("Products has been added");
        }catch(Exception e){
            System.out.println("Error in adding items");
        }
    }
}