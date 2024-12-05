package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rupizzeria2.model.*;

import java.util.ArrayList;
import java.util.List;

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

    private Spinner pizzaStyle;

    private RadioGroup sizeGroup;

    private String size;

    private RecyclerView pizzaRecyclerView;
    private PizzaAdapter pizzaAdapter;
    private List<Pizza> pizzaList;

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
        createRadioGroup();
        createRecyclerView();
    }

    private void createRecyclerView(){
        pizzaRecyclerView = findViewById(R.id.pizzaRecyclerView);
        pizzaRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        PizzaFactory NYPizzas = new NYPizza();
        PizzaFactory ChicagoPizzas = new ChicagoPizza();

        pizzaList = new ArrayList<>();
        pizzaList.add(NYPizzas.createDeluxe());
        pizzaList.add(NYPizzas.createBBQChicken());
        pizzaList.add(NYPizzas.createMeatzza());
        pizzaList.add(NYPizzas.createBuildYourOwn());
        pizzaList.add(ChicagoPizzas.createDeluxe());
        pizzaList.add(ChicagoPizzas.createBBQChicken());
        pizzaList.add(ChicagoPizzas.createMeatzza());
        pizzaList.add(ChicagoPizzas.createBuildYourOwn());

        pizzaAdapter = new PizzaAdapter(pizzaList);
        pizzaRecyclerView.setAdapter(pizzaAdapter);
    }

    /**
     * Sets Button intents
     */
    private void createButtonIntents(){
        Button goHome = findViewById(R.id.goHome);
        Button addToCart = findViewById(R.id.addToCart);
        Button clear = findViewById(R.id.clear);

        goHome.setOnClickListener(v -> {
            Intent intent = new Intent(PizzaOrderActivity.this, MainActivity.class);
            startActivity(intent);
        });

        addToCart.setOnClickListener(v -> {
            if (validateOptions()){
                Order newOrder = Order.getInstance();
                newOrder.addPizza(pizza);
                Intent intent = new Intent(PizzaOrderActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        clear.setOnClickListener(v -> {
            sizeGroup.clearCheck();
            pizzaStyle.setSelection(0);
        });
    }

    private boolean validateOptions(){

        if(pizzaStyle.getSelectedItem().toString().equals("Select Pizza Style"))
        {
            Toast.makeText(PizzaOrderActivity.this, "Select a valid pizza style!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(sizeGroup.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(PizzaOrderActivity.this, "Select a pizza size!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
        //return pizzaStyle.isSelected() && sizeGroup.isSelected();
    }

    /**
     * Sets spinner intents
     */
    private void createSpinnerIntents(){
        pizzaStyle = findViewById(R.id.pizzaStyle);

        pizzaStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String state = parent.getItemAtPosition(position).toString();
                createPizzaFactory(state);

                if(state.equals("Select Pizza Style"))
                {
                    pizzaFactory = null;
                    pizza = null;
                    Toast.makeText(PizzaOrderActivity.this, "Select Valid Pizza Style!", Toast.LENGTH_SHORT).show();
                }
                else {
                    createPizzaFactory(state);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pizzaFactory = null;
                pizza = null;
            }
        });
    }

    /**
     * Creates the pizza factory based on user selection
     *
     * @param pizzaStyleString Selected pizza style
     */
    private void createPizzaFactory(String pizzaStyleString){
        pizza = null;

        if(pizzaStyleString.equals("New York Style")){
            pizzaFactory = new NYPizza();
        }else{
            pizzaFactory = new ChicagoPizza();
        }
    }

    /**
     * Creates the RadioGroup for the android studio
     */
    private void createRadioGroup(){
        sizeGroup = findViewById(R.id.sizeRadioGroup);

        sizeGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if(checkedId == R.id.smallButton) {
                size = "Small";
            }

            else if(checkedId == R.id.mediumButton) {
                size = "Medium";
            }

            else if(checkedId == R.id.largeButton) {
                size = "Large";
            }

            else {
                size = null;
            }

        });
    }

}