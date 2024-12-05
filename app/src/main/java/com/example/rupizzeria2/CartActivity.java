package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizzeria2.model.*;

public class CartActivity extends AppCompatActivity {

    private final Order currentOrder = Order.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        createButtonIntents();
        loadListView();
    }

    /**
     * Creates the button intents
     */
    private void createButtonIntents(){
        Button goHome = findViewById(R.id.goHome2);
        Button cancelOrder = findViewById(R.id.cancelOrder);
        Button placeOrder = findViewById(R.id.placeOrder);

        goHome.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        });

        cancelOrder.setOnClickListener(v -> {
            currentOrder.clear();
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
        });

        placeOrder.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, OrdersPlacedActivity.class);
            startActivity(intent);
        });
    }

    private void loadListView(){
        ListView pizzaListView = findViewById(R.id.pizzaListView);

        ArrayAdapter<Pizza> toppingsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                currentOrder.getPizzas()
        );

        pizzaListView.setAdapter(toppingsAdapter);
    }


}