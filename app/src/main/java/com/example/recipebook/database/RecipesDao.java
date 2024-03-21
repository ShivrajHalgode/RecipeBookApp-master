package com.example.recipebook.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.recipebook.models.Recipe;

import java.util.List;

@Dao
public interface RecipesDao {

    @Insert
    public long insert(Recipe recipe);

    @Query("SELECT * FROM recipe")
    public List<Recipe> getAll();

    @Query("SELECT * FROM recipe WHERE id = :id LIMIT 1")
    public Recipe findById(long id);

    @Update
    public void update(Recipe recipe);

    @Delete
    public void delete(Recipe recipe);

}
