package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        createButtonIntents();
    }

    private void createButtonIntents(){
        Button goHome = findViewById(R.id.goHome2);

        goHome.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}