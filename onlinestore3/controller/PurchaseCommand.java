//-*- java -*-
//$file: PurchaseCommand.java 2023-03-25 
//Author: Emmanuel Adebayo
//package: controller

package controller;


import controller.Command;

public class PurchaseCommand implements Command{

    private boolean result;
    //
    //PurchaseCommand
    //
    public PurchaseCommand(boolean result){
        this.result = result;
    }

    //
    //execute
    //
    public void execute(){
        if(this.result == true){

            System.out.println("Items purchased");
        } else{

            System.out.println("Items could not be  purchased");
            System.out.println("Please make sure cart is not empty");
        }
    }
}