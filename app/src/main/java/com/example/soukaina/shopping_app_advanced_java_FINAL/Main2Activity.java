package com.example.soukaina.shopping_app_advanced_java_FINAL;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.soukaina.shopping_app_advanced_java_prj2.R;

// author Soukaina salihi

public class Main2Activity extends AppCompatActivity {

    // create  references
    TextView purchasedItems;
    TextView notPurchasedItems;
    EditText totalPrice;
    EditText budget;
    EditText currentBalance;
    ShopCart shopItems = new ShopCart();


    // Print out the shopped and not shopped items that were passed as strings trough intent
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // initialize the references by linking each one with its proper ID specified in the .xml file
        purchasedItems = (TextView) findViewById(R.id.shoppedItems_id);
        notPurchasedItems = (TextView) findViewById(R.id.notShoppedItems_id);
        totalPrice = (EditText) findViewById(R.id.totalPrice_id);
        budget = (EditText) findViewById(R.id.budget_id);
        currentBalance = (EditText) findViewById(R.id.CurrentBalance_id);

        // Make the edit text views not editable, only to output results
        totalPrice.setKeyListener(null);
        budget.setKeyListener(null);
        currentBalance.setKeyListener(null);

        // print out the shopped and not shopped items that were passed as strings trough intent
        Intent intent = getIntent();
        purchasedItems.setText(intent.getStringExtra("purchased"));
        notPurchasedItems.setText(intent.getStringExtra("notPurchased"));

        // print the total price to UI print the budget to the UI and current balance
        totalPrice.setText(intent.getStringExtra("total"));
        budget.setText(intent.getStringExtra("budget"));
        currentBalance.setText(intent.getStringExtra("balance"));


    }

    // Go back the main activity once the continue shopping button is clicked
    public void continueShoppingClicked(View view) {

        if (view.getId() == R.id.continue_Shopping_Button) {
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
    }

    // Exit the app once the exit button is clicked
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exitClicked(View view) {
        if (view.getId() == R.id.exit_Button) {
            finishAffinity();
            System.exit(0);
        }

    }
}
