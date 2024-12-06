package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizzeria2.model.Order;
import com.example.rupizzeria2.model.OrderManager;
import com.example.rupizzeria2.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class OrdersPlacedActivity extends AppCompatActivity {

    private Spinner spinner;
    private ListView ordersListView;
    private final OrderManager orderManager = OrderManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_placed);

        spinner = findViewById(R.id.ordersSpinner);
        ordersListView = findViewById(R.id.ordersListView);

        createSpinner();
        createButtonIntents();
    }

    private void createSpinner() {
        ArrayAdapter<Order> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                orderManager.getOrders()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Set a listener to update the ListView when an order is selected
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Order selectedOrder = (Order) spinner.getSelectedItem();
                updateListView(selectedOrder);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Clear the ListView if nothing is selected
                updateListView(null);
            }
        });
    }

    private void createButtonIntents() {
        Button cancelOrder2 = findViewById(R.id.cancelOrder2);
        Button goHome3 = findViewById(R.id.goHome3);

        cancelOrder2.setOnClickListener(v -> {
            Order selectedOrder = (Order) spinner.getSelectedItem();
            if (selectedOrder != null) {
                orderManager.removeOrder(selectedOrder);
                createSpinner();
            }
        });

        goHome3.setOnClickListener(v -> {
            Intent intent = new Intent(OrdersPlacedActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void updateListView(Order selectedOrder) {
        List<Pizza> pizzas = new ArrayList<>();
        if (selectedOrder != null) {
            pizzas = selectedOrder.getPizzas(); // Assume getPizzas() returns a List<Pizza>
        }

        ArrayAdapter<Pizza> pizzaAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                pizzas
        );
        ordersListView.setAdapter(pizzaAdapter);
    }
}
