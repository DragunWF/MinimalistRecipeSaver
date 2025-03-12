package com.example.minimalistrecipesaver.helpers;

import android.widget.EditText;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static void longToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void toast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getText(EditText text) {
        return String.valueOf(text.getText());
    }

    public static ArrayList<String> convertIngredientsToList(String ingredients) {
        String[] ingredientsArr = ingredients.split(",");
        ArrayList<String> ingredientsList = new ArrayList<>();
        for (String ingredient : ingredientsArr) {
            ingredientsList.add(ingredient.trim());
        }
        return ingredientsList;
    }
}
