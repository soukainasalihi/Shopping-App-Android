package com.example.soukaina.shopping_app_advanced_java_FINAL;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;

/**
 *
 * @author Soukaina
 */
// interface that handles printing
public interface PrintReceipt {

    public String printShoppedItems(List<Item> itemsArr);

    public String printNotShoppedItems(MyDatabaseHandler dbHandler,List<Item> itemsArr);

}
