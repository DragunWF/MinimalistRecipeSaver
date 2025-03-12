package com.example.minimalistrecipesaver.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.minimalistrecipesaver.ItemsActivity;
import com.example.minimalistrecipesaver.R;
import com.example.minimalistrecipesaver.data.Recipe;
import com.example.minimalistrecipesaver.helpers.DatabaseHelper;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> localDataSet;
    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleText, categoryText;
        private final ImageView viewImageBtn;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            titleText = view.findViewById(R.id.titleText);
            categoryText = view.findViewById(R.id.categoryText);
            viewImageBtn = view.findViewById(R.id.viewImageBtn);
        }

        public TextView getTitleText() {
            return titleText;
        }

        public TextView getCategoryText() {
            return categoryText;
        }

        public ImageView getViewImageBtn() {
            return viewImageBtn;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public RecipeAdapter(List<Recipe> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_items, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Recipe recipe = localDataSet.get(position);

        viewHolder.getTitleText().setText(recipe.getTitle());
        viewHolder.getCategoryText().setText(recipe.getCategory());

        viewHolder.getViewImageBtn().setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemsActivity.class);
            intent.putExtra(Recipe.RECIPE_ID, recipe.getId());
            context.startActivity(intent);
        });
    }

    public void updateDataSet() {
        List<Recipe> recipes = DatabaseHelper.getRecipeBank().getAll();
        updateDataSet(recipes);
    }

    public void updateDataSet(List<Recipe> updatedRecipes) {
        localDataSet.clear();
        for (Recipe recipe : updatedRecipes) {
            localDataSet.add(recipe);
        }
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
