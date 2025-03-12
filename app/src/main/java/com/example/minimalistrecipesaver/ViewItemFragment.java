package com.example.minimalistrecipesaver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minimalistrecipesaver.data.Recipe;
import com.example.minimalistrecipesaver.helpers.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = Recipe.RECIPE_ID;

    // TODO: Rename and change types of parameters
    private int viewedRecipeId;

    public ViewItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewItemFragment newInstance(int param1) {
        ViewItemFragment fragment = new ViewItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            viewedRecipeId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_item, container, false);
        Recipe recipe = DatabaseHelper.getRecipeBank().get(viewedRecipeId);

        // Set text
        TextView titleText = view.findViewById(R.id.titleText);
        TextView categoryText = view.findViewById(R.id.categoryText);
        TextView preparationTimeText = view.findViewById(R.id.preparationTimeText);
        TextView ingredientsListText = view.findViewById(R.id.ingredientsListText);
        TextView cookingInstructionsText = view.findViewById(R.id.cookingInstructionsText);

        // Load data
        titleText.setText(recipe.getTitle());
        categoryText.setText(recipe.getCategory());
        preparationTimeText.setText(String.valueOf(recipe.getPreparationTime()));
        ingredientsListText.setText(recipe.getIngredientsRawString());
        cookingInstructionsText.setText(recipe.getCookingInstructions());

        return view;
    }
}