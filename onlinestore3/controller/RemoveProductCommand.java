//-*- java -*-
//$file: RemoveProductCommand.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller


package controller;

import java.util.Scanner;

import model.ProductsInterface;
import model.ProductsContainer;

import model.ProductInterface;
import model.Product;


import controller.Command;

//
//removes product
//

public class RemoveProductCommand implements Command{
   
   private ProductsInterface prods;
   private String nameOfProduct;

    //
    //constructor
    //
    public RemoveProductCommand(ProductsInterface prods, String nameOfProduct){
        
        try{
            //prods.removeProducts(nameOfProduct);

            this.prods = prods;
            this.nameOfProduct = nameOfProduct;
        }catch(Exception e){
            System.out.println("products could not be removed");
        }
    }
    //
    //execute
    //
    public void execute(){
        //attempt ot remove product from store
        try{
            
            System.out.println("Attempting to remove product from store---------------------");
            this.prods.removeProducts(this.nameOfProduct);
            System.out.println("product succesfully removed from store");

        }catch(Exception e){
            System.out.println("product could not be removed");
        }
    }
}