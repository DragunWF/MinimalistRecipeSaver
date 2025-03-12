package com.example.minimalistrecipesaver.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.minimalistrecipesaver.data.Recipe;

public class DatabaseHelper {
    private static final String FILE_KEY = "db";

    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;

    private static ModelBank<Recipe> recipeBank;

    public static void initialize(Context context) {
        sharedPref = context.getSharedPreferences(FILE_KEY, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        recipeBank = new ModelBank<>(sharedPref, editor, "recipes", Recipe.class);
    }

    public static ModelBank<Recipe> getRecipeBank() {
        return recipeBank;
    }
}
