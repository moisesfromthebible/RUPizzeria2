package com.example.rupizzeria2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
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

    /** NY Pizza Factory */
    private final static PizzaFactory NYpizzaFactory = new NYPizza();

    /** Chicago Pizza Factory */
    private final static PizzaFactory chicagoPizzaFactory = new ChicagoPizza();

    /** Pizza Object */
    private Pizza pizza;

    /** Size radio group */
    private RadioGroup sizeGroup;

    /** Size of pizza */
    private Size size;

    /** Pizza adapter class
     */
    private PizzaAdapter pizzaAdapter;

    /** List view for toppings */
    private ListView toppingsListView;

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

        toppingsListView = findViewById(R.id.toppingsListView);
        createButtonIntents();
        createRadioGroup();
        createRecyclerView();
    }

    /**
     * Creates the pizza recycler view
     */
    private void createRecyclerView() {
        RecyclerView pizzaRecyclerView = findViewById(R.id.pizzaRecyclerView);
        pizzaRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(NYpizzaFactory.createDeluxe());
        pizzaList.add(NYpizzaFactory.createBBQChicken());
        pizzaList.add(NYpizzaFactory.createMeatzza());
        pizzaList.add(NYpizzaFactory.createBuildYourOwn());
        pizzaList.add(chicagoPizzaFactory.createDeluxe());
        pizzaList.add(chicagoPizzaFactory.createBBQChicken());
        pizzaList.add(chicagoPizzaFactory.createMeatzza());
        pizzaList.add(chicagoPizzaFactory.createBuildYourOwn());

        pizzaAdapter = new PizzaAdapter(pizzaList);
        pizzaRecyclerView.setAdapter(pizzaAdapter);

        pizzaAdapter.setOnPizzaSelectedListener(selectedPizza -> {
            pizza = selectedPizza;
            if (size != null) {
                pizza.setSize(size);
            }
            updatePrice();
            displayToppings();
        });
    }

    /**
     * Method to display toppings
     */
    private void displayToppings() {
        if (pizza instanceof BuildYourOwn) {
            updatePrice();
            List<Topping> allToppings = Topping.getAllToppings();
            ArrayAdapter<Topping> toppingsAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_multiple_choice,
                    allToppings
            );

            toppingsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            toppingsListView.setAdapter(toppingsAdapter);

            for (int i = 0; i < allToppings.size(); i++) {
                if (pizza.getToppings().contains(allToppings.get(i))) {
                    toppingsListView.setItemChecked(i, true);
                }
            }

            toppingsListView.setOnItemClickListener((parent, view, position, id) -> {
                Topping selectedTopping = allToppings.get(position);
                if (toppingsListView.isItemChecked(position)) {
                    pizza.addTopping(selectedTopping);
                } else {
                    pizza.removeTopping(selectedTopping);
                }
                updatePrice();
            });
        } else {
            List<Topping> toppings = pizza.getToppings();
            ArrayAdapter<Topping> toppingsAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    toppings
            );

            toppingsListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
            toppingsListView.setAdapter(toppingsAdapter);
        }
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
                pizza.setSize(size);
                Order newOrder = OrderManager.getInstance().getCurrOrder();
                newOrder.addPizza(pizza);
                Intent intent = new Intent(PizzaOrderActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        clear.setOnClickListener(v -> {
            sizeGroup.clearCheck();
            size = null;

            pizzaAdapter.clearSelection();

            toppingsListView.setAdapter(null);

            TextView priceTextView = findViewById(R.id.priceTextView);
            priceTextView.setText("$0.00");
            Toast.makeText(PizzaOrderActivity.this, "Selection cleared!", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Validates whether the pizza
     *
     * @return True if all needed items are selected
     */
    private boolean validateOptions(){
        if(!pizzaAdapter.isAnyItemSelected()) {
            Toast.makeText(PizzaOrderActivity.this, "Select a valid pizza style!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(sizeGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(PizzaOrderActivity.this, "Select a pizza size!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * Creates the RadioGroup for the android studio
     */
    private void createRadioGroup() {
        sizeGroup = findViewById(R.id.sizeRadioGroup);

        sizeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.smallButton) {
                size = Size.SMALL;
            } else if (checkedId == R.id.mediumButton) {
                size = Size.MEDIUM;
            } else if (checkedId == R.id.largeButton) {
                size = Size.LARGE;
            } else {
                size = null;
            }

            if (pizza != null) {
                pizza.setSize(size);
                updatePrice();
            }
        });
    }

    /**
     * Updates price
     */
    private void updatePrice() {
        TextView priceTextView = findViewById(R.id.priceTextView);

        if (pizza == null || size == null) { return; }

        double price = pizza.price();
        priceTextView.setText(String.format("$%.2f", price));
    }
}