package com.example.minimalistrecipesaver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minimalistrecipesaver.data.Recipe;
import com.example.minimalistrecipesaver.helpers.DatabaseHelper;
import com.example.minimalistrecipesaver.helpers.Utils;
import com.example.minimalistrecipesaver.services.RecipeService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEditItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEditItemFragment extends Fragment {
    public static final String ADD_FORM = "add";
    public static final String EDIT_FORM = "edit";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String formType;
    private int viewedRecipeId;
    private boolean isEditForm;

    public AddEditItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEditItem.
     */
    // TODO: Rename and change types and number of parameters
    public static AddEditItemFragment newInstance(String param1, int param2) {
        AddEditItemFragment fragment = new AddEditItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            formType = getArguments().getString(ARG_PARAM1);
            viewedRecipeId = getArguments().getInt(ARG_PARAM2);

            isEditForm = viewedRecipeId != -1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_edit_item, container, false);
        TextInputEditText titleText = view.findViewById(R.id.titleText);
        TextInputEditText categoryText = view.findViewById(R.id.categoryText);
        TextInputEditText preparationTimeText = view.findViewById(R.id.preparationTimeText);
        TextInputEditText ingredientsText = view.findViewById(R.id.ingredientsText);
        TextInputEditText instructionsText = view.findViewById(R.id.cookingInstructionsText);
        MaterialButton submitBtn = view.findViewById(R.id.submitBtn);

        if (isEditForm) {
            Recipe recipe = DatabaseHelper.getRecipeBank().get(viewedRecipeId);
            titleText.setText(recipe.getTitle());
            categoryText.setText(recipe.getCategory());
            preparationTimeText.setText(String.valueOf(recipe.getPreparationTime()));
            ingredientsText.setText(recipe.getIngredientsRawString());
            instructionsText.setText(recipe.getCookingInstructions());
        }

        submitBtn.setOnClickListener(v -> {
            String title = Utils.getText(titleText);
            String category = Utils.getText(categoryText);
            String preparationTimeStr = Utils.getText(preparationTimeText);
            String ingredients = Utils.getText(ingredientsText);
            String instructions = Utils.getText(instructionsText);

            if (title.isEmpty() || preparationTimeStr.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
                Utils.longToast("All fields are required!", requireContext());
                return;
            }

            int preparationTime = Integer.parseInt(preparationTimeStr);
            ArrayList<String> ingredientsList = Utils.convertIngredientsToList(ingredients);
            if (isEditForm) {
                Recipe recipe = DatabaseHelper.getRecipeBank().get(viewedRecipeId);
                recipe.setTitle(title);
                recipe.setCategory(category);
                recipe.setPreparationTime(preparationTime);
                recipe.setIngredients(ingredientsList);
                recipe.setCookingInstructions(instructions);
                RecipeService.edit(recipe);
                Utils.longToast(title + " has been edited!", requireContext());
            } else {
                RecipeService.add(new Recipe(title, category, instructions, preparationTime, ingredientsList));
                Utils.longToast(title + " has been added to the recipe list!", requireContext());
            }

            requireActivity().finish();
        });

        return view;
    }
}