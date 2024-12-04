package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizzeria2.model.*;

/**
 * Pizza Order Activity Class
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class PizzaOrderActivity extends AppCompatActivity {

    /** Pizza Factory */
    private PizzaFactory pizzaFactory;

    /** Pizza Object */
    private Pizza pizza;

    private Spinner pizzaStyle;

    private RadioGroup sizeGroup;


    /**
     * On Create method
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_order);

        createButtonIntents();
        createSpinnerIntents();
        createRadioGroup();
    }

    /**
     * Sets Button intents
     */
    private void createButtonIntents(){
        Button goHome = findViewById(R.id.goHome);
        Button addToCart = findViewById(R.id.addToCart);
        Button clear = findViewById(R.id.clear);

        goHome.setOnClickListener(v -> {
            Intent intent = new Intent(PizzaOrderActivity.this, MainActivity.class);
            startActivity(intent);
        });

        addToCart.setOnClickListener(v -> {
            if (validateOptions()){
                Order newOrder = Order.getInstance();
                newOrder.addPizza(pizza);
                Intent intent = new Intent(PizzaOrderActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        clear.setOnClickListener(v -> {
            sizeGroup.clearCheck();
            pizzaStyle.setSelection(0);
        });
    }

    private boolean validateOptions(){
        return pizzaStyle.isSelected() && sizeGroup.isSelected();
    }

    /**
     * Sets spinner intents
     */
    private void createSpinnerIntents(){
        pizzaStyle = findViewById(R.id.pizzaStyle);

        pizzaStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String state = parent.getItemAtPosition(position).toString();
                createPizzaFactory(state);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pizzaFactory = null;
                pizza = null;
            }
        });
    }

    /**
     * Creates the pizza factory based on user selection
     *
     * @param pizzaStyleString Selected pizza style
     */
    private void createPizzaFactory(String pizzaStyleString){
        pizza = null;

        if(pizzaStyleString.equals("New York Style")){
            pizzaFactory = new NYPizza();
        }else{
            pizzaFactory = new ChicagoPizza();
        }
    }

    /**
     * Creates the RadioGroup for the android studio
     */
    private void createRadioGroup(){
        sizeGroup = findViewById(R.id.sizeRadioGroup);

        sizeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton smallButton = findViewById(R.id.smallButton);
            RadioButton mediumButton = findViewById(R.id.mediumButton);
            RadioButton largeButton = findViewById(R.id.largeButton);
        });
    }

}