package com.example.minimalistrecipesaver.services;

import com.example.minimalistrecipesaver.data.Recipe;
import com.example.minimalistrecipesaver.helpers.DatabaseHelper;
import com.example.minimalistrecipesaver.helpers.ModelBank;

public class RecipeService {
    public static void add(Recipe recipe) {
        ModelBank<Recipe> bank = DatabaseHelper.getRecipeBank();
        bank.add(recipe);
    }

    public static void edit(Recipe recipe) {
        ModelBank<Recipe> bank = DatabaseHelper.getRecipeBank();
        bank.update(recipe);
    }

    public static void delete(int id) {
        ModelBank<Recipe> bank = DatabaseHelper.getRecipeBank();
        bank.delete(id);
    }
}
