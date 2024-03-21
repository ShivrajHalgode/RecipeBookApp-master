package com.example.recipebook.models;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String description;

    @ColumnInfo
    public ArrayList<String> ingredients;

    @ColumnInfo
    public ArrayList<String> steps;

    @ColumnInfo(name = "created_at")
    public long createdAt;
}
