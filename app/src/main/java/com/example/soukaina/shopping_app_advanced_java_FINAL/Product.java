package com.example.soukaina.shopping_app_advanced_java_FINAL;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Soukaina
 */
public class Product {

    // variables
    private String name;
    private double price;

    // default constructor
    public Product() {

    }
   public Product(String itmName) {

       name = itmName;
    }

    //constructor
    public Product(String itmName, double itmPrice) {

        name = itmName;
        price = itmPrice;
    }

    // getter 
    public String getName() {

        return name;
    }

    public double getPrice() {
        return price;
    }

    // setters
    public void setName(String itmName) {
        name = itmName;
    }

    public void setPrice(double itmPrice) {

        price = itmPrice;
    }

    
}
