package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizzeria2.model.Order;
import com.example.rupizzeria2.model.OrderManager;
import com.example.rupizzeria2.model.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * OrdersPlacedActivity controller
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class OrdersPlacedActivity extends AppCompatActivity {

    /** Spinner for orders */
    private Spinner spinner;
    /** List of pizzas */
    private ListView ordersListView;
    /** OrderManager */
    private final OrderManager orderManager = OrderManager.getInstance();

    /**
     * OnCreate method
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_placed);

        spinner = findViewById(R.id.ordersSpinner);
        ordersListView = findViewById(R.id.ordersListView);

        createSpinner();
        createButtonIntents();
    }

    /**
     * Creates spinner
     */
    private void createSpinner() {
        List<Order> orders = orderManager.getOrders();

        if (orders == null || orders.isEmpty()) {
            spinner.setAdapter(null);
            updateListView(null);
            TextView price = findViewById(R.id.price);
            price.setText("$0.00");
            return;
        }

        ArrayAdapter<Order> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                orders
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Order selectedOrder = (Order) spinner.getSelectedItem();
                updateListView(selectedOrder);
                TextView price = findViewById(R.id.price);
                double subtotal = getSubtotal(selectedOrder);
                price.setText(String.format("$%.2f", subtotal * 1.0625));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateListView(null);
            }
        });
    }

    /**
     * Creates button intents
     */
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

    /**
     * Updates the listview of pizzas
     *
     * @param selectedOrder Order selected in spinner
     */
    private void updateListView(Order selectedOrder) {
        List<Pizza> pizzas = new ArrayList<>();
        if (selectedOrder != null) {
            pizzas = selectedOrder.getPizzas();
        }

        ArrayAdapter<Pizza> pizzaAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                pizzas
        );
        ordersListView.setAdapter(pizzaAdapter);
    }


    /**
     * Calculates the subtotal of the current order by summing the prices of all pizzas.
     *
     * @return The total price of all pizzas in the order before tax.
     */
    private double getSubtotal(Order currentOrder){
        double subtotal = 0;
        for (Pizza pizza : currentOrder.getPizzas()){
            subtotal += pizza.price();
        }
        return subtotal;
    }
}
