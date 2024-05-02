//-*- java -*-
//$file: ViewProductsCommad.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller
//purpse:Implements the command interface
//Displays the products 
//====================================================

package controller;

import controller.Command;

import model.ProductsInterface;
import model.ProductsContainer;

import model.ProductInterface;

import java.util.ArrayList;


public class ViewProductsCommad implements Command {

    private ProductsInterface stub;
    private ArrayList<ProductInterface> prods;



    //
    //ViewProductsCommad
    //constructor
    public ViewProductsCommad(ProductsInterface stub){
        try{
            this.stub = stub;
            this.prods = this.stub.getProducts();
        }catch(Exception e){
            System.out.println("Error attempting to set stub and prods");
        }
        
    }

    //
    //execute
    //
    public void execute(){

        try{
             int i = 1;
              for (ProductInterface prod : this.prods){

                System.out.println("Item" + i);
                System.out.println("product name: "+prod.getName());
                System.out.println("product quantity: "+prod.getAvailableQuantity());
                System.out.println("product description "+prod.getDescription());
                System.out.println("product price: $"+prod.getPrice());

                System.out.println("\n");
                i++;

                System.out.println();
            }

        }catch(Exception e){
            System.out.println("could not display products");
        }
    }

}