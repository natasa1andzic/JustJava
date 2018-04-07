package com.example.natasaandzic.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
       // String message = "Total: " +numberOfCoffees*190+ "RSD";
       // displayMessage(message);
        TextView name = findViewById(R.id.name);
        String value = name.getText().toString();
        CheckBox soyMilk = findViewById(R.id.soyMilkCheckbox);
        boolean hasSoyMilk = soyMilk.isChecked();
        CheckBox chocolate = findViewById(R.id.chocolateCheckbox);
        boolean hasChocolate = chocolate.isChecked();
        int price = calculatePrice(hasSoyMilk, hasChocolate);
        String fullMessage = createOrderSummary(price, hasSoyMilk, hasChocolate, value);
        displayMessage(fullMessage);

       // Intent intent = new Intent(Intent.ACTION_SENDTO);
      //  intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        // intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " +name);
        //intent.putExtra(Intent.EXTRA_TEXT, "Just Java order for " +name);
        //if (intent.resolveActivity(getPackageManager()) != null) {
          //  startActivity(intent);
           // }
      //  }
    }

    private void displayPrice(int number) {
        TextView ukupnaCena =  findViewById(R.id.orderSummaryNumber);
        ukupnaCena.setText("" + number);
    }

    public void increment(View view) {
        numberOfCoffees++;
        if(numberOfCoffees>30) {
            Toast.makeText(this, "You cannot have more than 30 cups of coffee", Toast.LENGTH_SHORT).show();
            numberOfCoffees=30; }
        display(numberOfCoffees);
    }

    public void decrement(View view) {
        if (numberOfCoffees > 0) {
            Toast.makeText(this, "You cannot have less than 0 cups of coffee", Toast.LENGTH_SHORT).show();
            numberOfCoffees--;
            display(numberOfCoffees);
        } else {
            numberOfCoffees = 0;
            display(numberOfCoffees);
        }
    }

    private int calculatePrice(boolean soy, boolean choco) {
        int basePrice=190;
        if(soy==true)
            basePrice=basePrice + 30;
        if(choco==true)
            basePrice=basePrice + 50;
        return numberOfCoffees * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView numberOfCoffees = (TextView) findViewById(R.id.quantityNumber);
        numberOfCoffees.setText("" + number);
    }

    private void displayMessage(String m) {
        TextView message = findViewById(R.id.orderSummaryNumber);
        message.setText(m);
    }

    private String createOrderSummary(int price, boolean soy, boolean choco, String name) {
        String priceMessage = "Name " +name;
        priceMessage+= "\nQuantity: " +numberOfCoffees;
        priceMessage+= "\nSoy milk? " +soy;
        priceMessage+="\nChocolate? " +choco;
        priceMessage+="\nTotal: " +numberOfCoffees*price + " RSD";
        priceMessage+="\nThank you! :)";
        return priceMessage;

    }
}