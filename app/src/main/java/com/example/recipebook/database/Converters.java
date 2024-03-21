package com.example.recipebook.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    /*
      Much of the code below was found at:
        https://stackoverflow.com/questions/44986626/android-room-database-how-to-handle-arraylist-in-an-entity
        However, it was not merely blatantly copied and pasted but researched and understood before putting it into use.
        The use of the type converter was researched using documentation found here:
        https://developer.android.com/training/data-storage/room/referencing-data
        The usage of the gson library was found here:
        https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/Gson.html
    */
    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> arrayList = gson.fromJson(value, listType);
        return arrayList;
    }

    @TypeConverter
    public static String toString(ArrayList<String> values) {
        Gson gson = new Gson();
        String convertedList = gson.toJson(values);
        return convertedList;
    }
}
