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

            // Breakfast Recipes
            dummyRecipes.add(new Recipe(
                    "Pancakes",
                    "Breakfast",
                    "1. Mix flour, milk, eggs, and sugar.\n2. Pour batter onto a heated pan.\n3. Flip when bubbles form.\n4. Serve with syrup.",
                    15,
                    new ArrayList<>(Arrays.asList("Flour", "Milk", "Eggs", "Sugar", "Butter", "Baking Powder", "Maple Syrup"))
            ));

            dummyRecipes.add(new Recipe(
                    "Omelette",
                    "Breakfast",
                    "1. Beat eggs and season with salt & pepper.\n2. Heat butter in a pan.\n3. Pour eggs and cook until firm.\n4. Add cheese, vegetables, or ham before folding.",
                    10,
                    new ArrayList<>(Arrays.asList("Eggs", "Salt", "Pepper", "Butter", "Cheese", "Bell Peppers", "Ham"))
            ));

            dummyRecipes.add(new Recipe(
                    "French Toast",
                    "Breakfast",
                    "1. Whisk eggs, milk, and cinnamon.\n2. Dip bread slices in the mixture.\n3. Cook on a buttered pan until golden brown.\n4. Serve with syrup or fruit.",
                    12,
                    new ArrayList<>(Arrays.asList("Bread", "Eggs", "Milk", "Cinnamon", "Butter", "Maple Syrup"))
            ));

            // Lunch Recipes
            dummyRecipes.add(new Recipe(
                    "Spaghetti Carbonara",
                    "Lunch",
                    "1. Cook spaghetti.\n2. Fry bacon until crispy.\n3. Mix eggs, cheese, and pepper.\n4. Combine everything while hot.",
                    20,
                    new ArrayList<>(Arrays.asList("Spaghetti", "Eggs", "Parmesan Cheese", "Bacon", "Black Pepper"))
            ));

            dummyRecipes.add(new Recipe(
                    "Grilled Chicken Salad",
                    "Lunch",
                    "1. Grill chicken breast until cooked.\n2. Chop lettuce, tomatoes, and cucumbers.\n3. Slice chicken and mix with vegetables.\n4. Drizzle with dressing and serve.",
                    25,
                    new ArrayList<>(Arrays.asList("Chicken Breast", "Lettuce", "Tomatoes", "Cucumber", "Olive Oil", "Lemon Juice", "Salt", "Pepper"))
            ));

            dummyRecipes.add(new Recipe(
                    "Vegetable Stir-Fry",
                    "Lunch",
                    "1. Heat oil in a wok.\n2. Add garlic, carrots, and bell peppers.\n3. Stir-fry with soy sauce and sesame oil.\n4. Serve over rice or noodles.",
                    20,
                    new ArrayList<>(Arrays.asList("Carrots", "Bell Peppers", "Broccoli", "Garlic", "Soy Sauce", "Sesame Oil", "Rice"))
            ));

            // Dinner Recipes
            dummyRecipes.add(new Recipe(
                    "Chicken Curry",
                    "Dinner",
                    "1. Sauté onions and garlic.\n2. Add chicken and brown it.\n3. Pour in coconut milk and curry powder.\n4. Simmer until cooked.",
                    40,
                    new ArrayList<>(Arrays.asList("Chicken", "Coconut Milk", "Curry Powder", "Onions", "Garlic", "Potatoes"))
            ));

            dummyRecipes.add(new Recipe(
                    "Beef Stew",
                    "Dinner",
                    "1. Brown beef in a pot.\n2. Add onions, carrots, and potatoes.\n3. Pour in beef broth and simmer until tender.\n4. Season with salt and pepper.",
                    60,
                    new ArrayList<>(Arrays.asList("Beef", "Carrots", "Potatoes", "Onions", "Beef Broth", "Salt", "Pepper"))
            ));

            dummyRecipes.add(new Recipe(
                    "Baked Salmon",
                    "Dinner",
                    "1. Preheat oven to 375°F (190°C).\n2. Season salmon with salt, pepper, and lemon.\n3. Bake for 20 minutes.\n4. Serve with steamed vegetables.",
                    25,
                    new ArrayList<>(Arrays.asList("Salmon", "Lemon", "Salt", "Pepper", "Olive Oil", "Garlic", "Vegetables"))
            ));

            // Add all dummy recipes to the database
            for (Recipe recipe : dummyRecipes) {
                recipeBank.add(recipe);
            }
        }
    }

    public static void clear() {
        recipeBank.clear();
    }
}
