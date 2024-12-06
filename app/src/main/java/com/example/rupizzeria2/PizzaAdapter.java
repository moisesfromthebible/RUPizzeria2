package com.example.rupizzeria2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rupizzeria2.model.Pizza;

import java.util.List;

/**
 * Adapter class for the pizza recycler view
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    /** List of pizzas */
    private List<Pizza> pizzaList;
    /** Position selected */
    private int selectedPosition = RecyclerView.NO_POSITION;

    /**
     * Constructor for adapter
     *
     * @param pizzaList List of pizzas
     */
    public PizzaAdapter(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    /**
     * Creates a new {@link PizzaViewHolder} by inflating the layout for a pizza item.
     * This method is called when a new ViewHolder needs to be created to represent an item
     * in the {@link RecyclerView}.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new instance of {@link PizzaViewHolder} that holds the inflated view for a pizza item.
     */
    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pizza_item, parent, false);
        return new PizzaViewHolder(view);
    }

    /**
     * Binds the data of the pizza item at the given position to the {@link PizzaViewHolder}.
     * This method is called when the RecyclerView needs to display the item at the specified position.
     * It updates the views within the ViewHolder with the data from the corresponding pizza item,
     * and handles the item selection logic.
     *
     * @param holder The ViewHolder that holds the views for the pizza item.
     * @param position The position of the pizza item in the data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.pizzaName.setText(pizza.getName());
        holder.pizzaStyle.setText(pizza.getStyle());
        holder.pizzaCrust.setText(pizza.getCrust().toString());
        String pizzaName = pizza.getName().toLowerCase();
        String pizzaStyle = pizza.getStyle().toLowerCase();

        holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(),
                position==selectedPosition ? R.color.selected_item_color : android.R.color.transparent));

        if(pizzaName.contains("deluxe") && pizzaStyle.contains("chicago style")){
            holder.pizzaImage.setImageResource(R.drawable.deluxe_chicago_style);
        }else if(pizzaName.contains("deluxe") && pizzaStyle.contains("ny style")){
            holder.pizzaImage.setImageResource(R.drawable.deluxe_ny_style);
        }else if(pizzaName.contains("bbq") && pizzaStyle.contains("chicago style")){
            holder.pizzaImage.setImageResource(R.drawable.bbq_chicken_chicago_style);
        }else if(pizzaName.contains("bbq") && pizzaStyle.contains("ny style")){
            holder.pizzaImage.setImageResource(R.drawable.bbq_chicken_ny_style);
        }else if(pizzaName.contains("meatzza") && pizzaStyle.contains("chicago style")){
            holder.pizzaImage.setImageResource(R.drawable.meatzza_chicago_style);
        }else if(pizzaName.contains("meatzza") && pizzaStyle.contains("ny style")){
            holder.pizzaImage.setImageResource(R.drawable.meatzza_ny_style);
        }else if(pizzaName.contains("build your own") && pizzaStyle.contains("chicago style")){
            holder.pizzaImage.setImageResource(R.drawable.build_your_own_chicago_style);
        }else if(pizzaName.contains("build your own") && pizzaStyle.contains("ny style")){
            holder.pizzaImage.setImageResource(R.drawable.build_your_own_ny_style);
        } else {
            holder.pizzaImage.setImageResource(R.drawable.ic_launcher_background);
        }
        holder.itemView.setSelected(position == selectedPosition);
        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
            if (listener != null) {
                listener.onPizzaSelected(pizzaList.get(position));
            }
        });
    }

    /**
     * Returns the total number of items in the pizza list.
     * This method is called by the RecyclerView to determine the size of the data set.
     *
     * @return The number of items in the pizza list.
     */
    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    /**
     * ViewHolder for a pizza item. This class holds the views that display the pizza's name and style
     * and handles the item selection UI changes.
     */
    static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView pizzaName, pizzaStyle, pizzaCrust;
        ImageView pizzaImage;

        public PizzaViewHolder(View itemView) {
            super(itemView);
            pizzaName = itemView.findViewById(R.id.pizzaName);
            pizzaStyle = itemView.findViewById(R.id.pizzaStyle2);
            pizzaImage = itemView.findViewById(R.id.pizzaImage);
            pizzaCrust = itemView.findViewById(R.id.pizzaCrust);
        }
    }

    /**
     * Method to check if any item is selected.
     *
     * @return true if an item is selected, false otherwise.
     */
    public boolean isAnyItemSelected() {
        return selectedPosition != RecyclerView.NO_POSITION;
    }

    public interface OnPizzaSelectedListener {
        void onPizzaSelected(Pizza selectedPizza);
    }
    private OnPizzaSelectedListener listener;

    public void setOnPizzaSelectedListener(OnPizzaSelectedListener listener) {
        this.listener = listener;
    }

    public void clearSelection() {
        selectedPosition = -1;
        notifyDataSetChanged();
    }
}
