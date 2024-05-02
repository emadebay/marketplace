//
//Emmanuel Adebayo
//Products container.java
//Houses a list of products

package model;

import java.util.ArrayList;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.ProductInterface;
import model.Product;

import model.ProductsInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import java.rmi.*;
import java.rmi.registry.*;

import java.io.*;

public class ProductsContainer extends UnicastRemoteObject implements ProductsInterface, java.io.Serializable, Cloneable{
    private ArrayList<ProductInterface> prods =  new ArrayList<ProductInterface>();


    //
    //constructor
    //

    public ProductsContainer()throws RemoteException{
        super();
        //Initial products at the server at the beginnig and start of server;
        ProductInterface prodts;

        prodts = new Product();
        prodts.setName("iphone");
        prodts.setPrice(100);
        prodts.setDescription("a mobile and smart phone");
        prodts.setAvailableQuantity(500);
        this.addToProducts(prodts);

        prodts = new Product();
        prodts.setName("toothpaste");
        prodts.setPrice(20);
        prodts.setDescription("for teeth");
        prodts.setAvailableQuantity(3);
        this.addToProducts(prodts);

        System.out.println("products created");

    }

    //
    //getProducts
    //returns product list
    public ArrayList<ProductInterface> getProducts() {
        return prods;
    }

    //
    //addToProducts
    //append to products container
    public void addToProducts(ProductInterface prod){
        try {
         this.prods.add(prod);
         System.out.println("There are: "+ prods.size() +" in the store currently");
        } catch(Exception e){
            System.out.println("could not load items in the store");
        }
    }

    //
    //updateProduct
    //updates the description of items
    public void updateProduct(String name, String description, int quantity, double price) {
        try {
            System.out.println("Attempting to update products");

            for (ProductInterface prod : prods) {
                if(prod.getName().compareTo(name) == 0) {

                    //add new updated info
                    prod.setName(name);
                    prod.setDescription(description);
                    prod.setAvailableQuantity(quantity);
                    prod.setPrice(price);
                    break;
                }
                
             }
        } catch(Exception e){
            System.out.println("Can not remove products");
            e.printStackTrace();
        }
    }

    //
    //removeProducts
    //remove products from list
    public void removeProducts(String name) {
        
        try {
            System.out.println("Attempting to remove products");

            for (ProductInterface prod : prods) {
                if(prod.getName().compareTo(name) == 0) {
                    this.prods.remove(prod);
                    break;
                }
        }
        } catch(Exception e){
            System.out.println("Can not remove products");
            e.printStackTrace();
        }
    }

    //
    //displayProducts
    //shows products and information
    public void displayProducts(){
        System.out.println("List of items available and their corresponding information");
        int i = 1;
        for (ProductInterface prod : prods){

            System.out.println("Item" + i);
            System.out.println("Product name "+prod.getName());
            System.out.println("Available quantity "+prod.getAvailableQuantity());
            System.out.println("product description "+prod.getDescription());
            System.out.println("product's price $"+prod.getPrice());

            System.out.println("\n");
            i++;
        }
    }

    //
    //returns a clone of object
    //
   public Object clone() throws CloneNotSupportedException{
        return super.clone();
   }

    //getProduct
    //get a product object by name and quantity
    //
    public ProductInterface getProduct(String name, int quantity){
       // ProductInterface productTobeReturned = new Product();

        for(ProductInterface prod: prods){
            if(name.compareTo(prod.getName()) == 0 && quantity <= prod.getAvailableQuantity()){
                

                //updated quantity
                int newQuantity = prod.getAvailableQuantity() - quantity;
                prod.setAvailableQuantity(newQuantity);
                System.out.println(quantity+ " of "+ name + " has been removed from the inventory list");
                return prod;
            }
        }

        System.out.println("Attempt to get product from store failed");
        System.out.println("Either because of incorrect product name or exceeds available quantity");

        return null;
    }

    
}