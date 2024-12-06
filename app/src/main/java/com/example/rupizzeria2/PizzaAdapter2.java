package com.example.rupizzeria2;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rupizzeria2.model.Pizza;

import java.util.List;

public class PizzaAdapter2 extends ArrayAdapter<Pizza> {
    private final Context context;
    private final List<Pizza> pizzaList;
    private int selectedPosition = -1;

    public PizzaAdapter2(Context context, List<Pizza> pizzaList) {
        super(context, R.layout.pizza_item_layout, pizzaList);
        this.context = context;
        this.pizzaList = pizzaList;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }


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
        pizzaName.setText(pizza.getName());

        TextView pizzaStyle = convertView.findViewById(R.id.pizzaStyle2);
        pizzaStyle.setText(pizza.getStyle());

        TextView pizzaCrust = convertView.findViewById(R.id.pizzaCrust);
        pizzaCrust.setText(pizza.getCrust().toString());

        TextView pizzaPrice = convertView.findViewById(R.id.pizzaPrice);
        pizzaPrice.setText(String.format("$%.2f", pizza.price()));

        TextView pizzaToppings = convertView.findViewById(R.id.pizzaToppings);
        pizzaToppings.setText(TextUtils.join(", ", pizza.getToppings()));

        return convertView;
    }

    private int getImageResId(String pizzaName, String pizzaStyle){
        if(pizzaName.contains("deluxe") && pizzaStyle.contains("chicago style")){
            return (R.drawable.deluxe_chicago_style);
        }else if(pizzaName.contains("deluxe") && pizzaStyle.contains("ny style")){
            return (R.drawable.deluxe_ny_style);
        }else if(pizzaName.contains("bbq") && pizzaStyle.contains("chicago style ")){
            return (R.drawable.bbq_chicken_chicago_style);
        }else if(pizzaName.contains("bbq") && pizzaStyle.contains("ny style ")){
            return (R.drawable.bbq_chicken_ny_style);
        }else if(pizzaName.contains("meatzza") && pizzaStyle.contains("chicago style ")){
            return (R.drawable.meatzza_chicago_style);
        }else if(pizzaName.contains("meatzza") && pizzaStyle.contains("ny style ")){
            return (R.drawable.meatzza_ny_style);
        }else if(pizzaName.contains("build your own") && pizzaStyle.contains("chicago style ")){
            return (R.drawable.build_your_own_chicago_style);
        }else if(pizzaName.contains("build your own") && pizzaStyle.contains("ny style ")){
            return (R.drawable.build_your_own_ny_style);
        } else {
            return (R.drawable.ic_launcher_background);
        }
    }
}
