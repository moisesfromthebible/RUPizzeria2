package com.example.rupizzeria2;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.rupizzeria2.model.Pizza;

import java.util.List;

/**
 * Pizza Adapter for Pizzas
 *
 * @author Moises Cespedes Manuel, Binoy Patel
 */
public class PizzaAdapter2 extends ArrayAdapter<Pizza> {
    /** Context for accessing resources and views */
    private final Context context;
    /** List of pizzas to display*/
    private final List<Pizza> pizzaList;
    /** Position of selected pizza */
    private int selectedPosition = -1;

    /**
     * Constructor
     * @param context
     * @param pizzaList
     */
    public PizzaAdapter2(Context context, List<Pizza> pizzaList) {
        super(context, R.layout.pizza_item_layout, pizzaList);
        this.context = context;
        this.pizzaList = pizzaList;
    }

    /**
     * Sets position of selected pizza and refreshes ListView
     * @param position
     */
    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    /**
     * Generate the view for each pizza item in ListView
     * @param position Position of item
     * @param convertView Old view to reuse
     * @param parent Parent view
     * @return View corresponding to item at position
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.pizza_item_layout, parent, false);
        }

        Pizza pizza = pizzaList.get(position);

        ImageView pizzaImage = convertView.findViewById(R.id.pizzaImage);
        pizzaImage.setImageResource(getImageResId(pizza.getName().toLowerCase(), pizza.getStyle().toLowerCase()));

        TextView pizzaName = convertView.findViewById(R.id.pizzaName);
        pizzaName.setText(pizza.getSize().toString() + " " + pizza.getName());

        TextView pizzaStyle = convertView.findViewById(R.id.pizzaStyle2);
        pizzaStyle.setText(pizza.getStyle());

        TextView pizzaCrust = convertView.findViewById(R.id.pizzaCrust);
        pizzaCrust.setText(pizza.getCrust().toString());

        TextView pizzaPrice = convertView.findViewById(R.id.pizzaPrice);
        pizzaPrice.setText(String.format("$%.2f", pizza.price()));

        TextView pizzaToppings = convertView.findViewById(R.id.pizzaToppings);
        pizzaToppings.setText(TextUtils.join(", ", pizza.getToppings()));

        if (position == selectedPosition) {
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.selected_item_color));
        } else {
            convertView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        }

        return convertView;
    }

    /**
     * Retrieve image ID based on pizza name and style
     * @param pizzaName Name of Pizza
     * @param pizzaStyle Style of Pizza
     * @return Resource ID of image
     */
    private int getImageResId(String pizzaName, String pizzaStyle){
        if(pizzaName.contains("deluxe") && pizzaStyle.contains("chicago style")){
            return (R.drawable.deluxe_chicago_style);
        } else if(pizzaName.contains("deluxe") && pizzaStyle.contains("ny style")){
            return (R.drawable.deluxe_ny_style);
        } else if(pizzaName.contains("bbq") && pizzaStyle.contains("chicago style ")){
            return (R.drawable.bbq_chicken_chicago_style);
        } else if(pizzaName.contains("bbq") && pizzaStyle.contains("ny style ")){
            return (R.drawable.bbq_chicken_ny_style);
        } else if(pizzaName.contains("meatzza") && pizzaStyle.contains("chicago style ")){
            return (R.drawable.meatzza_chicago_style);
        } else if(pizzaName.contains("meatzza") && pizzaStyle.contains("ny style")){
            return (R.drawable.meatzza_ny_style);
        } else if(pizzaName.contains("build your own") && pizzaStyle.contains("chicago style")){
            return (R.drawable.build_your_own_chicago_style);
        } else if(pizzaName.contains("build your own") && pizzaStyle.contains("ny style")){
            return (R.drawable.build_your_own_ny_style);
        } else {
            return (R.drawable.ic_launcher_background);
        }
    }

    /**
     * Holds references to views
     */
    static class ViewHolder {
        /** TextView for pizza name*/
        TextView pizzaName;
        /** TextView for pizza style*/
        TextView pizzaStyle;
        /** TextView for pizza crust*/
        TextView pizzaCrust;
        /** ImageView for pizza image*/
        ImageView pizzaImage;
    }
}
