//Emmanuel Adebayo
//ProductsInterface.java
//

package model;
import java.rmi.*;

import model.ProductInterface;
import model.Product;

import java.util.ArrayList;

public interface ProductsInterface extends Remote {

    //
    //addToProducts
    //
    public void addToProducts(ProductInterface prod) throws RemoteException;
    //
    //removeProducts
    //
    public void removeProducts(String name) throws RemoteException;

    //
    //displayProducts
    //
    public void displayProducts() throws RemoteException;

     //
    //updateProduct
    //
    public void updateProduct(String name, String description, int quantity, double price) throws RemoteException;

    //
    //getProduct
    //
    public ProductInterface getProduct(String name, int quantity) throws RemoteException;

    //
    //clone
    //
    public Object clone() throws RemoteException, CloneNotSupportedException;

    //
    //getProducts
    //
    public ArrayList<ProductInterface> getProducts() throws RemoteException;

    
}