//-*- java -*-
//$file: ProductInterface.java 2023-03-25 
//Author: Emmanuel Adebayo
//====================================================

/**
    @interface: ProductInterface
    This interface defines some key functionality and structure of what a product should look like
    This class is implemented by the Product class. Refer to Product.java in the model package
 */

package model;


public interface ProductInterface {
    //Note---- these are abstract method

    //setters

    /**
    setPrice(double price) ---- sets price of product
    @param[in] price
    */
    public void setPrice(double price);

    /**
    setDescription(String description)------ sets description
    @param[in] description
    */
    public void setDescription(String description);

    /**
    setName(String name) ----- set products name
    #param[in] name
    */
    public void setName(String name);


    /**
    setAvailableQuantity(int availableQuantity) --- sets availabailty of product
    @param[in] availableQuantity
    */
    public void setAvailableQuantity(int availableQuantity);

    //getters
    /**
    double getPrice()
    returns price of product
    */
    public double getPrice();

    /**
    String getDescription()
    returns description of product
    */
    public String getDescription();

    /**
    String getName()
    returns name of product
    */
    public String getName();

    /**
    getAvailableQuantity()
    returns quantity of product available
    */
    public int getAvailableQuantity();
}