package com.example.natasaandzic.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int number=0;

    private EditText nameEt;
	private CheckBox soyMilkCb;
	private CheckBox chocolateCb;
	private TextView numberOfCoffeesTv;

	private Button incrementBtn;
	private Button decrementBtn;

	private TextView totalPriceTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    nameEt = (EditText)findViewById(R.id.name);

	    soyMilkCb = (CheckBox)findViewById(R.id.soyMilkCheckbox);
	    chocolateCb = (CheckBox)findViewById(R.id.chocolateCheckbox);
	    numberOfCoffeesTv = (TextView)findViewById(R.id.quantityNumber);

	    incrementBtn = (Button)findViewById(R.id.inc);
	    decrementBtn = (Button)findViewById(R.id.dec);

	    totalPriceTv = findViewById(R.id.orderSummaryNumber);

	    incrementBtn.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    number++;
			    if(number>30) {
				    Toast.makeText(MainActivity.this, "You cannot have more than 30 cups of coffee", Toast.LENGTH_SHORT).show();
				    number=30; }
			    numberOfCoffeesTv.setText("" + number);
		    }
	    });

	    decrementBtn.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    if (number > 0) {
				    Toast.makeText(MainActivity.this, "You cannot have less than 0 cups of coffee", Toast.LENGTH_SHORT).show();
				    number--;
				    numberOfCoffeesTv.setText("" +number);
			    } else {
				    number = 0;
				    numberOfCoffeesTv.setText("" +number);
			    }
		    }
	    });
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String value = nameEt.getText().toString();
        boolean hasSoyMilk = soyMilkCb.isChecked();
        boolean hasChocolate = chocolateCb.isChecked();
        int price = calculatePrice(hasSoyMilk, hasChocolate);
        String fullMessage = createOrderSummary(price, hasSoyMilk, hasChocolate, value);
        totalPriceTv.setText(fullMessage);

       Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " +value);
        intent.putExtra(Intent.EXTRA_TEXT, "Just Java order for " +fullMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
          startActivity(intent);
            }
       }



    private int calculatePrice(boolean soy, boolean choco) {
        int basePrice=190;
        if(soy==true)
            basePrice=basePrice + 30;
        if(choco==true)
            basePrice=basePrice + 50;
        return number * basePrice;
    }


    private String createOrderSummary(int price, boolean soy, boolean choco, String name) {
    	String quantity = numberOfCoffeesTv.getText().toString();
        String priceMessage = "Name " +name;
        priceMessage+= "\nQuantity: " +quantity;
        priceMessage+= "\nSoy milk? " +soy;
        priceMessage+="\nChocolate? " +choco;
        priceMessage+="\nTotal: " +number*price + " RSD";
        priceMessage+="\nThank you! :)";
        return priceMessage;

    }
}