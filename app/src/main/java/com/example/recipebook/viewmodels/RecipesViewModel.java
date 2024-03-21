package com.example.recipebook.viewmodels;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.recipebook.database.AppDatabase;
import com.example.recipebook.models.Recipe;

import java.util.ArrayList;

public class RecipesViewModel extends AndroidViewModel {
    private AppDatabase database;
    private MutableLiveData<Recipe> currentRecipe = new MutableLiveData<>();
    private ObservableArrayList<Recipe> recipes = new ObservableArrayList<>();
    private Handler handler;

    public RecipesViewModel(@NonNull Application application) {
        super(application);
        handler = new Handler();
        database = Room.databaseBuilder(application, AppDatabase.class, "recipedb").build();
        new Thread(() -> {
            ArrayList<Recipe> theRecipes = (ArrayList<Recipe>) database.getRecipesDao().getAll();
            handler.post(() -> {
                recipes.addAll(theRecipes);
            });
        }).start();
    }

    public void saveRecipe(String name, ArrayList<String> ingredients, ArrayList<String> steps, String description) {
        new Thread(() -> {
            Recipe newRecipe = new Recipe();

            newRecipe.name = name;
            newRecipe.description = description;
            newRecipe.ingredients = ingredients;
            newRecipe.steps = steps;
            newRecipe.createdAt = System.currentTimeMillis();
            newRecipe.id = database.getRecipesDao().insert(newRecipe);
        }).start();

    }

    public ObservableArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public MutableLiveData<Recipe> getSingleRecipe(long id) {
        new Thread(() -> {
            Recipe recipe = database.getRecipesDao().findById(id);
            handler.post(() -> {
                currentRecipe.postValue(recipe);
            });
        }).start();
        return currentRecipe;
    }

    public MutableLiveData<Recipe> getCurrentRecipe() {
        return currentRecipe;
    }

    public void setCurrentRecipe(Recipe recipe) {
        this.currentRecipe.setValue(recipe);
    }
}
