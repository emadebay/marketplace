
//-*- java -*-
//$file: UpdateProductCommand.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller


package controller;

import java.util.Scanner;

import model.ProductsInterface;
import model.ProductsContainer;

import model.ProductInterface;
import model.Product;

import controller.Command;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import java.rmi.*;
import java.rmi.registry.*;

public class UpdateProductCommand implements Command {

    private ProductsInterface prods;

    private ProductsInterface stub;

   private String name; 
   private String desc; 
   private double price; 
   private int quantity;
   

    //
    //constructor
    //
    public UpdateProductCommand(String name, String desc, double price, int quantity, ProductsInterface stub){
        
        try{
             //stub.updateProduct(name, desc, quantity, price);
             this.stub = stub;
             this.name = name;
             this.desc = desc;
             this.price = price;
             this.quantity = quantity;
        }
        catch(Exception e){
            System.out.println("Update product command error");
            e.printStackTrace();
        }
    }

    //
    //execute
    //updates the product
    public void execute(){

         try{

            //System.out.println("Product has been put in the container");
            if(this.stub != null){
                this.stub.updateProduct(this.name, this.desc, this.quantity, this.price);
                System.out.println("product has been succesfully updated. ");
                System.out.println("Enter V/v to view the updates");
            } else{
                System.out.println("product could not be updated");
            }

        }catch(Exception e){
            System.out.println("Update product command error");
            e.printStackTrace();
        }
    }
}