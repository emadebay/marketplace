//-*- java -*-
//$file: AddItemCommand.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller
//purpse:Implements the command interface
//====================================================

/****
 * This class uses the command pattern to add an item to the cart
 * User will add the item to the cart with this object
 */
package controller;
import java.util.ArrayList;
import model.ProductInterface;
import model.Product;
import controller.Command;

public class AddItemCommand implements Command{

    private ArrayList<ProductInterface> cart;
    private ProductInterface prod;
    /**
     * AddItemCommand(ArrayList<ProductInterface> cart, ProductInterface prod)
     * param[in] cart, product
     * add the items to the cart
     */
    public AddItemCommand(ArrayList<ProductInterface> cart, ProductInterface prod){

        try{
            //cart.add(prod);
            this.cart = cart;
            this.prod = prod;
        }catch(Exception e){
            System.out.println("item could not be added to cart");
        }
    }

    /**
     * execute()
     * display confirmation message
     */
    public void execute(){
        
        //add the product to the cart
        //potential error is handled
       try{
            this.cart.add(this.prod);
            System.out.println(this.prod.getName() +": was been added to cart");
            
       }catch(Exception e){
            System.out.println("Product could not be updated");
       }
    }
}