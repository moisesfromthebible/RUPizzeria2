package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rupizzeria2.model.*;

import java.util.ArrayList;
import java.util.Arrays;
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

    private void createRecyclerView() {
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

        pizzaAdapter = new PizzaAdapter(pizzaList, selectedPizza -> {
            selectedPizza.setSize(Size.valueOf(size.toUpperCase()));
            displayPizzaDetails(selectedPizza);
        });
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
                if(pizza != null)
                {
                    pizza.setSize(Size.valueOf(size.toUpperCase()));
                }
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
        if(pizza != null)
        {
            pizza.setSize(Size.valueOf(size.toUpperCase()));
        }
        return true;

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

    private void displayPizzaDetails(Pizza pizza) {
        ListView toppingsListView = findViewById(R.id.toppingsListView);
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);

        if (pizza.getSize() == null && size != null) {
            pizza.setSize(Size.valueOf(size.toUpperCase()));
        }

        if (pizza.getName().equals("Build Your Own")) {
            List<String> availableToppings = Arrays.asList(
                    "Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom", "BBQ Chicken", "Beef",
                    "Ham", "Provolone", "Cheddar", "Spinach", "Black Olives", "Pineapple"
            );
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, availableToppings);
            toppingsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            toppingsListView.setAdapter(adapter);

            toppingsListView.setOnItemClickListener((parent, view, position, id) -> {
                String selectedTopping = availableToppings.get(position);
                Topping topping = Topping.getTopping(selectedTopping);
                if (pizza.getToppings().contains(topping)) {
                    pizza.getToppings().remove(topping);
                } else {
                    if (pizza.getToppings().size() < 7) {
                        pizza.getToppings().add(topping);
                    } else {
                        Toast.makeText(this, "Max 7 toppings!", Toast.LENGTH_SHORT).show();
                    }
                }
                totalPriceTextView.setText(String.format("$%.2f", pizza.price()));
            });
        } else {
            List<String> toppingStrings = new ArrayList<>();
            for (Topping topping : pizza.getToppings()) {
                toppingStrings.add(topping.toString());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, toppingStrings
            );
            toppingsListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
            toppingsListView.setAdapter(adapter);
        }

        if (pizza.getSize() != null) {
            totalPriceTextView.setText(String.format("$%.2f", pizza.price()));
        } else {
            totalPriceTextView.setText("Select Size");
        }
    }



}