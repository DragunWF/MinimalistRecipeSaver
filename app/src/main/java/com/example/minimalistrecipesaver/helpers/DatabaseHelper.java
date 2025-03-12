package com.example.minimalistrecipesaver.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.minimalistrecipesaver.data.Recipe;

import java.util.ArrayList;
import java.util.Arrays;

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

    public static void addDummyData() {
        if (recipeBank.getAll().isEmpty()) {
            ArrayList<Recipe> dummyRecipes = new ArrayList<>();

            dummyRecipes.add(new Recipe(
                    "Spaghetti Carbonara",
                    "Italian",
                    "1. Cook spaghetti.\n2. Fry bacon until crispy.\n3. Mix eggs, cheese, and pepper.\n4. Combine everything while hot.",
                    20,
                    new ArrayList<>(Arrays.asList("Spaghetti", "Eggs", "Parmesan Cheese", "Bacon", "Black Pepper"))
            ));

            dummyRecipes.add(new Recipe(
                    "Chicken Curry",
                    "Asian",
                    "1. Sauté onions and garlic.\n2. Add chicken and brown it.\n3. Pour in coconut milk and curry powder.\n4. Simmer until cooked.",
                    40,
                    new ArrayList<>(Arrays.asList("Chicken", "Coconut Milk", "Curry Powder", "Onions", "Garlic", "Potatoes"))
            ));

            dummyRecipes.add(new Recipe(
                    "Pancakes",
                    "Breakfast",
                    "1. Mix flour, milk, eggs, and sugar.\n2. Pour batter onto a heated pan.\n3. Flip when bubbles form.\n4. Serve with syrup.",
                    15,
                    new ArrayList<>(Arrays.asList("Flour", "Milk", "Eggs", "Sugar", "Butter", "Baking Powder", "Maple Syrup"))
            ));

            // Add all dummy recipes to the database
            for (Recipe recipe : dummyRecipes) {
                recipeBank.add(recipe);
            }
        }
    }
}
