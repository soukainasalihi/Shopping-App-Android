package com.example.soukaina.shopping_app_advanced_java_FINAL;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Soukaina
 */
public class ShopCart implements PrintReceipt {

    // variables declaration
    private double totalPrice;
    private int itemCount;
    private double bankAccount;
    private Context context;

    private Context context1;


    public ShopCart(Context context) {
        this.context1 = context;
    }

    // constructor
    ShopCart() {

        totalPrice = 0;
        itemCount = 0;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(double bankAccount) {
        this.bankAccount = bankAccount;
    }


    // this method adds priority from user's input to the chosen items in the array
    public void addPriority(int x, List<Item> items, String priorityInput) {

        //  the input is valid so set the priority
        int ValidInput = Integer.parseInt(priorityInput);
        items.get(x).setPriority(ValidInput);


    }

    // add quantity of the item from user input
    public void addQuantity(int x, List<Item> items, String QuantityInput) {

        // the input is valid so set the quantity
        int ValidInput = Integer.parseInt(QuantityInput);
        items.get(x).setQuantity(ValidInput);


    }

    // this function checks for duplicates items. if there in one, it asks
    // the user whether he wants to remove the previously saved item in the list
    // or keep the old version of the item and remove the last choices for the
    // duplicated item and
    public boolean checkDuplicateItems(Item obj, List<Item> itemsArr) {
        // loop through the list to see if the object exist if yes make the flag true
        for (int i = 0; i < itemsArr.size(); i++) {
            // using the equal method to check for duplicate objects
            if (itemsArr.get(i).equals(obj)) {

                // let the user know that there is duplicates
                if (true) {

                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context1);
                    dlgAlert.setTitle("Duplicate items!");
                    dlgAlert.setMessage("The item (" + itemsArr.get(i).getName() + ") is being updated with the " +
                            "latest input values for priority and quantity ");

                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }

                            });
                    // remove the previously saved item in the list
                    itemsArr.remove(i);

                    return true; // meaning get the new data


                } else {

                    return false;
                }
            }

        }
        // in case there was no duplicates get the new data
        return true;
    }

    // this method does the calculation to get the total price
    public double perchaseItems(List<Item> itemsArr, double budget) {
        setBankAccount(budget);
        for (int i = 0; i < itemsArr.size(); i++) {
            totalPrice += itemsArr.get(i).getPrice() * itemsArr.get(i).getQuantity();
            if (totalPrice <= budget) {
                itemCount++;
            } else {
                totalPrice -= itemsArr.get(i).getPrice() * itemsArr.get(i).getQuantity();
                setTotalPrice(totalPrice);

                break;
            }
        }
        setTotalPrice(totalPrice);
        // price= getTotalPrice();
        return getTotalPrice();
    }

    // prints list of item each time the user adds an item
    public String printAddedItems(List<Item> itemsArr) {
        String outputs = "";
        outputs += String.format("%8s%22s%18s%18s\n", "Name", "Price", "Quantity", "Priority");

        for (int i = 0; i < itemsArr.size(); i++) {
            outputs += String.format("%10s%22.1f%20d%20d\n", itemsArr.get(i).getName(), itemsArr.get(i).getPrice(),
                    itemsArr.get(i).getQuantity(), itemsArr.get(i).getPriority());

        }
        return outputs;
    }

    // Print the shopped list of items with the prices, priorities and quantities
    @Override
    public String printShoppedItems(List<Item> itemsArr) {
        String outputs = "";
        outputs += String.format("%8s%22s%18s%18s\n", "Name", "Price", "Quantity", "Priority");

        for (int i = 0; i < itemCount; i++) {
            outputs += String.format("%10s%22.1f%20d%20d\n", itemsArr.get(i).getName(), itemsArr.get(i).getPrice(),
                    itemsArr.get(i).getQuantity(), itemsArr.get(i).getPriority());

        }

        return outputs;
    }

    // Print the list of items that wasn't shopped with  the prices, priorities and quantities
    @Override
    public String printNotShoppedItems(MyDatabaseHandler dbHandler, List<Item> itemsArr) {
        String outputs = "";
        outputs += String.format("%8s%22s%18s%18s\n", "Name", "Price", "Quantity", "Priority");

        for (int i = itemCount; i < itemsArr.size(); i++) {
            outputs += String.format("%10s%22.1f%20d%20d\n", itemsArr.get(i).getName(), itemsArr.get(i).getPrice(),
                    itemsArr.get(i).getQuantity(), itemsArr.get(i).getPriority());
            // save not shopped items once the checkout button is clikced
            Item product = new Item(itemsArr.get(i).getName(), itemsArr.get(i).getPrice(),
                    itemsArr.get(i).getQuantity(), itemsArr.get(i).getPriority());
            dbHandler.addProduct(product);
        }
        return outputs;
    }


}
