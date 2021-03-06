package com.example.soukaina.shopping_app_advanced_java_FINAL;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author Soukaina
 */

// Class
public class Item extends Product  {

    private int priority;
    private int quantity;

    // Default constuctor
    Item() {
        super();
    }

    // Constructor
    Item(String itmName, double itmPrice) {
        super(itmName , itmPrice);

    }
    // Constructor
    Item(String itmName, double itmPrice, int itmQuantity, int itmPriority) {
        super(itmName, itmPrice);
        quantity=itmQuantity;
       priority = itmPriority;

    }


    // Getter
    public int getPriority() {
        return priority;
    }

    public int getQuantity() {
        return quantity;
    }

    // setters
    public void setPriority(int itmPriority) {

        priority = itmPriority;
    }

    public void setQuantity(int itmQuantity) {

        quantity = itmQuantity;
    }


    // Equals methods to compare objects by name
    public boolean equals(Item obj) {
        return (super.getName().equalsIgnoreCase(obj.getName()));
    }


    //  Sort the list by priority
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Item>  sortListByPriority(List<Item> sortArray) {

        //sorting the list using lambda expression and streams
        sortArray = sortArray.stream().sorted(
                Comparator.comparing(p -> p.priority)).collect(Collectors.toList());

        return sortArray;
    }


}
