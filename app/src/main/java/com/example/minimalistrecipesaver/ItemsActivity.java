package com.example.minimalistrecipesaver;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.minimalistrecipesaver.data.Recipe;
import com.example.minimalistrecipesaver.helpers.Utils;

public class ItemsActivity extends AppCompatActivity {
    private ImageView backImageBtn, addImageBtn, editImageBtn;

    private int viewedRecipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_items);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            viewedRecipeId = getIntent().getIntExtra(Recipe.RECIPE_ID, -1);

            bindUIElements();
            setButtons();

            if (savedInstanceState == null) {
                loadViewItemFragment();
            }
        } catch (Exception err) {
            err.printStackTrace();
            Utils.longToast(err.getMessage(), this);
        }
    }

    private void bindUIElements() {
        backImageBtn = findViewById(R.id.backImageBtn);
        addImageBtn = findViewById(R.id.addImageBtn);
        editImageBtn = findViewById(R.id.editImageBtn);
    }

    private void setButtons() {
        backImageBtn.setOnClickListener(v -> {
            finish();
        });
        addImageBtn.setOnClickListener(v -> {

        });
        editImageBtn.setOnClickListener(v -> {

        });
    }

    private void loadViewItemFragment() {
        // Create a new instance of the fragment with the recipe ID
        ViewItemFragment fragment = ViewItemFragment.newInstance(viewedRecipeId);

        // Begin a transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace the container with the new fragment
        transaction.replace(R.id.fragmentContainerView, fragment);

        // Commit the transaction
        transaction.commit();
    }
}