package com.example.rupizzeria2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rupizzeria2.model.Pizza;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private List<Pizza> pizzaList;
    private PizzaSelectionListener listener;

    public PizzaAdapter(List<Pizza> pizzaList, PizzaSelectionListener listener)
    {
        this.pizzaList = pizzaList;
        this.listener = listener;
    }

    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pizza_item, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.pizzaName.setText(pizza.getName());
        holder.pizzaStyle.setText(pizza.getStyle());

        holder.itemView.setOnClickListener(v ->{
            if(listener != null)
            {
                listener.onPizzaClick(pizza);
            }
        });
    }
    public interface PizzaSelectionListener
    {
        void onPizzaClick(Pizza pizza);
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView pizzaName, pizzaStyle;

        public PizzaViewHolder(View itemView) {
            super(itemView);
            pizzaName = itemView.findViewById(R.id.pizzaName);
            pizzaStyle = itemView.findViewById(R.id.pizzaStyle2);
        }
    }
}
