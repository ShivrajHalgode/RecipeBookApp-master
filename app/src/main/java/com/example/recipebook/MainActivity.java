package com.example.recipebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.recipebook.models.Recipe;
import com.example.recipebook.viewmodels.RecipeAdapter;
import com.example.recipebook.viewmodels.RecipesViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExtendedFloatingActionButton addRecipeButton = findViewById(R.id.add_recipe_button);
        RecyclerView recyclerView = findViewById(R.id.recipe_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecipesViewModel viewModel = new ViewModelProvider(this).get(RecipesViewModel.class);
        ObservableArrayList recipes = viewModel.getRecipes();
        RecipeAdapter adapter = new RecipeAdapter(recipes,
            entry -> {
                viewModel.setCurrentRecipe(entry);
                Intent intent = new Intent(MainActivity.this, ViewRecipeScreen.class);
                intent.putExtra("recipe_id", entry.id);
                startActivity(intent);
            });
        recipes.addOnListChangedCallback(new ObservableList.OnListChangedCallback() {
            @Override
            public void onChanged(ObservableList sender) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
                adapter.notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeRemoved(positionStart, itemCount);
            }
        });

        recyclerView.setAdapter(adapter);

        addRecipeButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddRecipeScreen.class);
            startActivity(intent);
        });
    }
}
