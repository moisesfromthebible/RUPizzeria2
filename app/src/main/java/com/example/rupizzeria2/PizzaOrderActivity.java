package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    }

    /**
     * Sets Button intents
     */
    private void createButtonIntents(){
        Button goHome = findViewById(R.id.goHome);

        goHome.setOnClickListener(v -> {
            Intent intent = new Intent(PizzaOrderActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Sets spinner intents
     */
    private void createSpinnerIntents(){
        Spinner pizzaStyle = findViewById(R.id.pizzaStyle);

        pizzaStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String state = parent.getItemAtPosition(position).toString();
                createPizzaFactory(state);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createPizzaFactory(String pizzaStyleString){
        pizza = null;

        if(pizzaStyleString.equals("New York Style")){
            pizzaFactory = new NYPizza();
        }else{
            pizzaFactory = new ChicagoPizza();
        }
    }


}