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

import androidx.appcompat.app.AlertDialog;
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
    /** PizzaAdapter2 for managing pizza list */
    private PizzaAdapter2 pizzaAdapter;

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

        ordersListView.setOnItemClickListener((parent, view, position, id) -> {

            if (pizzaAdapter != null) {
                pizzaAdapter.setSelectedPosition(position);
                pizzaAdapter.notifyDataSetChanged();
            }
        });
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
            price.setText(getString(R.string.total_zero));
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
                double subtotal = selectedOrder.getSubtotal();
                price.setText(String.format("$%.2f", subtotal * 1.06625));
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
            AlertDialog.Builder builder = new AlertDialog.Builder(OrdersPlacedActivity.this);
            builder.setTitle("Confirm Cancellation")
                    .setMessage("Are you sure you want to cancel this order?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Order selectedOrder = (Order) spinner.getSelectedItem();
                        if (selectedOrder != null) {
                            orderManager.removeOrder(selectedOrder);
                            createSpinner();
                        }
                    })
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .setCancelable(false)
                    .show();
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

        pizzaAdapter = new PizzaAdapter2(this, pizzas);
        ordersListView.setAdapter(pizzaAdapter);
    }

}

