package com.example.recipebook.viewmodels;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipebook.R;
import com.example.recipebook.models.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    public interface onRecipeClicked{
        public void onClick(Recipe recipe);
    }

    onRecipeClicked listener;

    private ArrayList<Recipe> recipes;
    public RecipeAdapter(ArrayList<Recipe> recipes, onRecipeClicked listener){
        this.recipes = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        TextView nameView = holder.itemView.findViewById(R.id.recipe_title);
        TextView descView = holder.itemView.findViewById(R.id.recipe_desc);

        holder.itemView.setOnClickListener(view -> {
            listener.onClick(recipes.get(position));
        });

        nameView.setText(recipe.name);
        descView.setText(recipe.description + "");
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
