package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizzeria2.model.*;

/**
 * Cart activity controller
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class CartActivity extends AppCompatActivity {

    /** Current order in cart */
    private final Order currentOrder = OrderManager.getInstance().getCurrOrder();

    private static int orderNumber = 1;

    /**
     * onCreate method for cartActivity
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        TextView orderNumberText = findViewById(R.id.orderNumberText);
        orderNumberText.setText("Order #: " + orderNumber);
        createButtonIntents();
        loadListView();
        setPrices();
    }

    /**
     * Creates the button intents
     */
    private void createButtonIntents(){
        Button goHome = findViewById(R.id.goHome2);
        Button cancelOrder = findViewById(R.id.cancelOrder);
        Button placeOrder = findViewById(R.id.placeOrder);
        Button removePizza = findViewById(R.id.removePizza);

        goHome.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        });

        cancelOrder.setOnClickListener(v -> {
            currentOrder.clear();
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        });

        placeOrder.setOnClickListener(v -> placeOrderFunction());

        removePizza.setOnClickListener(v -> removePizzaFunction());
    }

    private void placeOrderFunction(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);

        if (currentOrder.getPizzas().isEmpty()) {
            builder.setTitle("Error")
                    .setMessage("Your cart is empty. Please add pizzas to place an order.")
                    .setPositiveButton("OK", null)
                    .setCancelable(true)
                    .show();
        } else {
            builder.setTitle("Success")
                    .setMessage("Your order has been placed successfully.")
                    .setPositiveButton("OK", (dialog, which) -> {
                        OrderManager.getInstance().createOrder();
                        orderNumber++;
                        Intent intent = new Intent(CartActivity.this, OrdersPlacedActivity.class);
                        startActivity(intent);
                    })
                    .setCancelable(false)
                    .show();
        }
    }

    private void removePizzaFunction(){
        ListView pizzaListView = findViewById(R.id.pizzaListView);
        int selectedPosition = pizzaListView.getCheckedItemPosition();

        if (currentOrder.getPizzas().isEmpty()) {
            Toast.makeText(this, "The cart is empty. Nothing to remove.", Toast.LENGTH_SHORT).show();
        } else if (selectedPosition < 0) {
            Toast.makeText(this, "Please select a pizza to remove.", Toast.LENGTH_SHORT).show();
        } else {
            currentOrder.getPizzas().remove(selectedPosition);
            ((ArrayAdapter<?>) pizzaListView.getAdapter()).notifyDataSetChanged();
            setPrices();
            Toast.makeText(this, "Pizza removed.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Loads list view.
     */
    private void loadListView(){
        ListView pizzaListView = findViewById(R.id.pizzaListView);
        PizzaAdapter2 pizzaAdapter = new PizzaAdapter2(this, currentOrder.getPizzas());
        pizzaListView.setAdapter(pizzaAdapter);
        pizzaListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    /**
     * Sets prices in text views
     */
    private void setPrices(){
        TextView subtotalText = findViewById(R.id.subtotal);
        TextView salesTaxText = findViewById(R.id.salesTax);
        TextView orderTotalText = findViewById(R.id.orderTotal);

        double subtotal = currentOrder.getSubtotal();

        subtotalText.setText(String.format("Subtotal $%.2f", subtotal));
        salesTaxText.setText(String.format("Sales Tax $%.2f", subtotal * 0.06625));
        orderTotalText.setText(String.format("Order total $%.2f", subtotal * 1.06625));
    }

}