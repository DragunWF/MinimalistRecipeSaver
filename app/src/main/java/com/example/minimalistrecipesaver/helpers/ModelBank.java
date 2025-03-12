package com.example.minimalistrecipesaver.helpers;

import android.content.SharedPreferences;

import com.example.minimalistrecipesaver.data.Model;
import com.google.gson.Gson;

public class ModelBank<T extends Model> {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private String modelKey;
    private Class<T> modelClass;

    private static Gson gson = new Gson();
}
