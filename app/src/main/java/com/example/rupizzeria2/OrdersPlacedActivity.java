package com.example.rupizzeria2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizzeria2.model.Order;
import com.example.rupizzeria2.model.OrderManager;

public class OrdersPlacedActivity extends AppCompatActivity {

    private Spinner spinner;

    private OrderManager orderManager = OrderManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_placed);

        spinner = findViewById(R.id.ordersSpinner);
        ArrayAdapter<Order> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                orderManager.getOrders()
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
