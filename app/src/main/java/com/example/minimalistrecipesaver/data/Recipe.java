package com.example.minimalistrecipesaver.data;

import java.util.ArrayList;

public class Recipe extends Model {
    private String title, category, cookingInstructions;
    private int preparationTime; // minutes
    private ArrayList<String> ingredients;

    public Recipe(String title, String category, String cookingInstructions, int preparationTime, ArrayList<String> ingredients) {
        this.title = title;
        this.category = category;
        this.cookingInstructions = cookingInstructions;
        this.preparationTime = preparationTime;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", cookingInstructions='" + cookingInstructions + '\'' +
                ", preparationTime=" + preparationTime +
                ", ingredients=" + ingredients +
                ", id=" + id +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
