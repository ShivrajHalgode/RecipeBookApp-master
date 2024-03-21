package com.example.recipebook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipebook.viewmodels.RecipesViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddRecipeScreen extends AppCompatActivity {

    String uriPath = "";
    TextView imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe_screen);

        RecipesViewModel viewModel = new ViewModelProvider(this).get(RecipesViewModel.class);

        ArrayList<String> ingredientsArrayList = new ArrayList<>();
        ArrayList<String> stepsArrayList = new ArrayList<>();

        ArrayAdapter<String> ingredientsAdapter;
        ArrayAdapter<String> stepsAdapter;

        ListView ingredientsList;
        ListView stepsList;

        FloatingActionButton addIngredientButton = findViewById(R.id.add_ingredient);
        FloatingActionButton removeIngredientButton = findViewById(R.id.remove_ingredient);

        FloatingActionButton addStepButton = findViewById(R.id.add_step);
        FloatingActionButton removeStepButton = findViewById(R.id.remove_step);

        ExtendedFloatingActionButton readyToAddButton = findViewById(R.id.ready_to_add_button);

        TextInputEditText nameText = findViewById(R.id.nameEditText);
        TextInputEditText descriptionText = findViewById(R.id.descriptionEditText);
        TextInputEditText ingredientText = findViewById(R.id.ingredientsEditText);
        TextInputEditText stepsText = findViewById(R.id.stepsEditText);

        ingredientsList = findViewById(R.id.ingredients_list);
        stepsList = findViewById(R.id.steps_list);

        ingredientsAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, ingredientsArrayList);
        ingredientsList.setAdapter(ingredientsAdapter);

        stepsAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, stepsArrayList);
        stepsList.setAdapter(stepsAdapter);

        addIngredientButton.setOnClickListener(view -> {
            ingredientsArrayList.add(ingredientText.getText().toString());
            ingredientText.setText("");
            ingredientsAdapter.notifyDataSetChanged();
        });

        removeIngredientButton.setOnClickListener(view -> {
            if (ingredientsArrayList.size() > 0){
                ingredientsArrayList.remove(ingredientsArrayList.size() - 1);
            }
            ingredientsAdapter.notifyDataSetChanged();
        });

        addStepButton.setOnClickListener(view -> {
            stepsArrayList.add(stepsText.getText().toString());
            stepsText.setText("");
            stepsAdapter.notifyDataSetChanged();
        });

        removeStepButton.setOnClickListener(view -> {
            if (stepsArrayList.size() > 0){
                stepsArrayList.remove(stepsArrayList.size() - 1);
            }
            stepsAdapter.notifyDataSetChanged();
        });

        readyToAddButton.setOnClickListener(view -> {
            String name = nameText.getText().toString();
            String description = descriptionText.getText().toString();
            Intent intent = new Intent(AddRecipeScreen.this, MainActivity.class);
            viewModel.saveRecipe(name, ingredientsArrayList, stepsArrayList, description);
            startActivity(intent);
        });

        /*
         * TODO: This works, but hours of experience says that
         * displaying an image from internal storage is more work than its worth
         * at this point.
        Button imageButton = findViewById(R.id.imageButton);
        this.imagePath = findViewById(R.id.imagePath);

        // this code is based on code found on https://www.geeksforgeeks.org/how-to-select-an-image-from-gallery-in-android/
        imageButton.setOnClickListener(view -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(i, "Select Picture"), 200);
        });
        */

    }


/*
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == 200) { // 200 is a good status code
                Uri selectedImageUri = data.getData();
                if(null != selectedImageUri) {
                    uriPath = selectedImageUri.getPath();
                    Log.d("URI", uriPath);
                    this.imagePath.setText(uriPath);
                }
            }
        }
    }
    */
}
