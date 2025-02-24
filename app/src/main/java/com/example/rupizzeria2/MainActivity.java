package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Main Menu Activity class
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class MainActivity extends AppCompatActivity {

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        createButtonIntent();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Creates the Intent for the buttons
     */
    private void createButtonIntent(){
        Button goToOrder = findViewById(R.id.goToOrder);
        Button goToCart = findViewById(R.id.goToCart);
        Button goToOrdersPlaced = findViewById(R.id.goToOrdersPlaced);

        goToOrder.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PizzaOrderActivity.class);
            startActivity(intent);
        });

        goToCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });

        goToOrdersPlaced.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrdersPlacedActivity.class);
            startActivity(intent);
        });
    }

}