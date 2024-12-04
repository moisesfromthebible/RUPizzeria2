package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Pizza Order Activity Class
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class PizzaOrderActivity extends AppCompatActivity {

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
    }

    /**
     * Sets Button Intents
     */
    private void createButtonIntents(){
        Button goHome = findViewById(R.id.goHome);

        goHome.setOnClickListener(v -> {
            Intent intent = new Intent(PizzaOrderActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}