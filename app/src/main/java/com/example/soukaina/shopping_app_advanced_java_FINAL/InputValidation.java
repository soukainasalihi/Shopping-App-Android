package com.example.soukaina.shopping_app_advanced_java_FINAL;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @author Soukaina salihi
 */
public class InputValidation {

    // context is a reference to the current Activity (MainActivity)
    private static Context context;

    // Explicitly passing the context into the InputValidation as parameter when it gets created
    // Doing this will allow the toast message to be displayed in the MainActivity
    // even if it was called from this class
    public InputValidation(Context c) {
        context = c;
    }


    // input validation / check for negative numbers and non numeric values
    public boolean getPositiveInteger(String input, String atribute) {

        if (!input.trim().matches("^[0-9]*$")) {

            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
            dlgAlert.setTitle("Invalid Input");
            dlgAlert.setMessage("Make sure you enter a positive whole number for " + atribute
                    + ". (NO letters or special characters)");
            // display a button that allows the user to exit the alert box
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    });
            return false;
        }

        return true;


    }


    // input validation / check for negative numbers and non numeric values
    public boolean getPositiveDouble(String input, String atribute) {

        if (!input.trim().matches("\\d*\\.?\\d+")) {

            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
            dlgAlert.setTitle("Invalid Input");
            dlgAlert.setMessage("Make sure you enter a positive number for " + atribute
                    + ". (NO letters or special characters)");
            // display a button that allows the user to exit the alert box
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    });
            return false;
        }

        return true;


    }
}
