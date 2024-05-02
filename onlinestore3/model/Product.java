//-*- java -*-
//$file: Product.java 2023-03-25 
//Author: Emmanuel Adebayo
//====================================================

/**
    @class: Product
    This class is a concrete implementation of the ProductInterface
    it implements the methods defined in the interface
    please refer to ProductInterface.java in the model package
*/


package model;
import java.io.*;

import model.ProductInterface;

public class Product implements java.io.Serializable, ProductInterface{

    //holds price of product
    private double price;
    //hols description of product
    private String description;
    //holds name of product
    private String name;
    //holds quantity of product available
    private int availableQuantity;

    /**
    setPrice(double price) ---- sets price of product
    @param[in] price
    */
    public void setPrice(double price){
        this.price = price;
    }

    /**
    setDescription(String description)------ sets description
    @param[in] description
    */
    public void setDescription(String description){
        this.description = description;
    }
    
    /**
    setName(String name) ----- set products name
    #param[in] name
    */
    public void setName(String name){
        this.name = name;
    }

    /**
    setAvailableQuantity(int availableQuantity) --- sets availabailty of product
    @param[in] availableQuantity
    */
    public void setAvailableQuantity(int availableQuantity){
        this.availableQuantity = availableQuantity;
    }

    //getters
    /**
    double getPrice()
    returns price of product
    */
    public double getPrice(){
        return price;
    }

    /**
    String getDescription()
    returns description of product
    */
    public String getDescription(){
        return description;
    }

    /**
    String getName()
    returns name of product
    */
    public String getName(){
        return name;
    }

    /**
    getAvailableQuantity()
    returns quantity of product available
    */
    public int getAvailableQuantity(){
        return availableQuantity;
    }


}