package com.example.soukaina.shopping_app_advanced_java_FINAL;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soukaina.shopping_app_advanced_java_prj2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// MainActivity contains the main user interface that gets displayed once the user opens the application 
public class MainActivity extends AppCompatActivity {

    // Declare references
    TextView selectedItemName;
    TextView priceOutput;
    TextView itemsListOutput;
    TextView itemsListLabel;
    EditText quantityInput;
    EditText priorityInput;
    EditText budgetInput;
    MyDatabaseHandler dbHandler;
    // instantiate classes
    ShopCart Obj = new ShopCart(this);
    InputValidation validation = new InputValidation(this);
    ShopCart shopItems = new ShopCart();
    Item itemObj = new Item();

    List<Item> itemsArray = new ArrayList<Item>();
    Double budget = 0.0;

    // Create objects with name and price that will be displayed in the drop down men
    Item Apple = new Item("Apple", 5.0);
    Item Vase = new Item("Vase", 40.0);
    Item Bread = new Item("Bread", 6.0);
    Item Juice = new Item("Juice", 3.0);
    Item Jeans = new Item("Jeans", 30.0);
    Item Mug = new Item("Mug", 25.0);
    Item Book = new Item("Book", 35.0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the references by linking each one with its proper ID specified in the .xml file
        priceOutput = (TextView) findViewById(R.id.priceLabel);
        quantityInput = (EditText) findViewById(R.id.quantityInqut_id);
        priorityInput = (EditText) findViewById(R.id.priorityInput_id);
        itemsListOutput = (TextView) findViewById(R.id.itemsList);
        budgetInput = (EditText) findViewById(R.id.budget_input_id);
        itemsListLabel = (TextView) findViewById(R.id.itemsList_label);


        // Create a spinner for items names and link it with its Id (itemsComBox) in the xml file
        final Spinner products_spinner = (Spinner) findViewById(R.id.itemsComBox);
        // create an arraylist as list of the items that will be displyed in the spinner
        final List<String> spinnerArray = new ArrayList<String>(
                Arrays.asList("Apple", "Vase", "Bread", "Juice", "Jeans", "Mug", "Book"));

        // create an array adapter to populate the spinner
        ArrayAdapter<String> adapterNames = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapterNames.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        products_spinner.setAdapter(adapterNames);

        // Set a Listener to handel the actions performed on the dropdown menu
        products_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // get the selected item from the spinner and save it in userInputName
                selectedItemName = (TextView) products_spinner.getSelectedView();
                // Display the price of the selected item
                selectedItemPrice(view);
            }

            // Default selection of an item in the spinner (item in position 0)
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                products_spinner.setSelection(0);
            }
        });

        // Hide the soft keyboard when app starts
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        // Make itemsListOutput text area scrollable
        itemsListOutput.setMovementMethod(new ScrollingMovementMethod());
        itemsListOutput.setText("");
        // Instantiate my database
        dbHandler = new MyDatabaseHandler(this, null, null, 1);
        // Print not shopped items saved in the database
        printDatabase();
    }

    // Output the selected item price instantly when user choose an item from the drop down menu
    public void selectedItemPrice(View view) {
        switch (selectedItemName.getText().toString()) {

            case "Apple":
                priceOutput.setText("5.0 $");
                break;
            case "Vase":
                priceOutput.setText("40.0 $");
                break;
            case "Bread":
                priceOutput.setText("6.0 $");
                break;
            case "Juice":
                priceOutput.setText("3.0 $");
                break;
            case "Jeans":
                priceOutput.setText("30.0 $");
                break;
            case "Mug":
                priceOutput.setText("25.0 $");
                break;
            case "Book":
                priceOutput.setText("35.0 $");
                break;
            default:
                break;
        }
    }

    // check for wrong inputs or if any of the text fields is empty when clicking add item button
    public boolean validateInputs() {

        // Check if the quantity text field is empty then display a flag indicating an error message
        if (quantityInput.getText().toString().matches("")) {
            quantityInput.setError("You didn't enter a quantity value!");
            return false;
        }
        // Check if the priority text field is empty then display a flag indicating an error message
        if (priorityInput.getText().toString().matches("")) {
            priorityInput.setError("You didn't enter a priority value!");

            return false;
        }

        // Display a dialog box with an error message if the input for quantity is not a positive number
        if (!validation.getPositiveInteger(quantityInput.getText().toString(), "quantity")) {

            return false;
        }
        // Display a dialog box with an error message if the input for priority is not a positive number
        if (!validation.getPositiveInteger(priorityInput.getText().toString(), "priority")) {

            return false;
        }

        // return true if all the inputs are correct
        return true;


    }

    // Check for wrong inputs or if the text field is empty for budget when checking out
    public boolean validateInputsBudget() {

        // Check if the budget text field is empty then display a flag indicating an error message
        if (budgetInput.getText().toString().matches("")) {
            Toast.makeText(this, "You didn't enter a budget value!", Toast.LENGTH_LONG).show();

            return false;
        }
        // Display a dialog box with an error message if the input for budget is not a positive number
        if (!validation.getPositiveDouble(budgetInput.getText().toString(), "Budget")) {

            return false;
        }
        // return true if all the inputs are correct
        return true;
    }


    // Event handler for the add item button
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addItem(View view) {

        if (validateInputs()) {

            // Delete the reminder and display the new shopping list instead
            dbHandler.deleteAll();
            // update the label text
            itemsListLabel.setText("Added Items to your cart :");

            // Get user inputs for item name
            itemObj.setName(selectedItemName.getText().toString());

            // Check if the item have been already entered
            // and handle the issue with alert box choices
            if (Obj.checkDuplicateItems(itemObj, itemsArray)) {

                // go through the switch case statement and add the item to ItemsArray
                switch (selectedItemName.getText().toString().toLowerCase()) {
                    case "apple":
                        addObject(Apple);
                        break;

                    case "vase":
                        addObject(Vase);

                        break;

                    case "bread":
                        addObject(Bread);

                        break;

                    case "juice":
                        addObject(Juice);
                        break;
                    case "jeans":
                        addObject(Jeans);
                        break;
                    case "mug":
                        addObject(Mug);
                        break;
                    case "book":
                        addObject(Book);
                        break;
                    default:
                        // Error message if the item name doesn't exist in the shopping list
                        Toast.makeText(this, "Make sure that the name entred is from "
                                + "the shopping list!!", Toast.LENGTH_LONG).show();
                        break;
                }

            }
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addObject(Item obj) {
        // Hide the soft keyboard when clicking on addItem button
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        // Add the object name to the array
        itemsArray.add(obj);
        // Get the item's quantity from the user and add it to the array
        shopItems.addQuantity(itemsArray.indexOf(obj), itemsArray, quantityInput.getText().toString());
        // Get the item's priority from the user and add it to the array
        shopItems.addPriority(itemsArray.indexOf(obj), itemsArray, priorityInput.getText().toString());
        // Sort the array of items based on priority
        itemsArray = Item.sortListByPriority(itemsArray);
        /* Print the added items sorted */
        itemsListOutput.setText(shopItems.printAddedItems(itemsArray));

    }

    //Print the database
    public void printDatabase() {
        // Print reminder of not shopped items from last transaction
        itemsListLabel.setText("Not shopped items from last transaction:");
        String dbString = dbHandler.databaseToString();
        itemsListOutput.append(dbString);
    }

    // Go the second activity when checkout Button is clicked
    public void checkoutButtonClicked1(View v1) throws Exception {
        double totalPrice, currentBalance;
        String purchasedItems, notPurchasedItems;

        if (itemsArray.size() != 0) {
            if (validateInputsBudget()) {
                double budget = Double.parseDouble(budgetInput.getText().toString());

                // Calculate the total price
                totalPrice = shopItems.perchaseItems(itemsArray, budget);
                currentBalance = (budget - totalPrice);
                // Save shopped items sorted into a string
                purchasedItems = shopItems.printShoppedItems(itemsArray);
                // Save not shopped items sorted
                notPurchasedItems = shopItems.printNotShoppedItems(dbHandler, itemsArray);


                // Pass information from this activity to the next activity
                // Create an intent to go the the second activity
                Intent i = new Intent(MainActivity.this, Main2Activity.class);

                // Send the outputs as a strings trough intent
                i.putExtra("purchased", purchasedItems);
                i.putExtra("notPurchased", notPurchasedItems);
                i.putExtra("total", "$ " + totalPrice);
                i.putExtra("budget", "$ " + budget);
                i.putExtra("balance", "$ " + currentBalance);

                startActivity(i);

            }
        } else {
            Toast.makeText(this, "You have to add at least one item to" +
                    " the shopping list to checkout!!", Toast.LENGTH_LONG).show();

        }

    }

}
