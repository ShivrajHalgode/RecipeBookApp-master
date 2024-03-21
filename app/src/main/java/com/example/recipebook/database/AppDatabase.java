package com.example.recipebook.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.recipebook.models.Recipe;

@Database(entities = {Recipe.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipesDao getRecipesDao();

}
