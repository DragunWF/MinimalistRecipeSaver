package com.example.minimalistrecipesaver;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.minimalistrecipesaver.data.Recipe;
import com.example.minimalistrecipesaver.helpers.Utils;

public class ItemsActivity extends AppCompatActivity {
    private ImageView backImageBtn, addImageBtn, editImageBtn;

    private String initialFragment;
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
            initialFragment = getIntent().getStringExtra(MainActivity.INITIAL_ITEM_FRAGMENT);
            if (initialFragment == null) {
                initialFragment = MainActivity.VIEW_FORM;
            }

            bindUIElements();
            setButtons();

            if (savedInstanceState == null) {
                switch (initialFragment) {
                    case MainActivity.ADD_FORM:
                        loadAddItemFragment();
                        break;
                    case MainActivity.EDIT_FORM:
                        loadEditItemFragment();
                        break;
                    default:
                        loadViewItemFragment();
                        break;
                }
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
            loadAddItemFragment();
        });
        editImageBtn.setOnClickListener(v -> {
            loadEditItemFragment();
        });
    }

    private void loadViewItemFragment() {
        ViewItemFragment fragment = ViewItemFragment.newInstance(viewedRecipeId);
        loadFragment(fragment);
    }

    private void loadAddItemFragment() {
        AddEditItemFragment fragment = AddEditItemFragment.newInstance(AddEditItemFragment.ADD_FORM, -1);
        loadFragment(fragment);
    }

    private void loadEditItemFragment() {
        AddEditItemFragment fragment = AddEditItemFragment.newInstance(AddEditItemFragment.EDIT_FORM, viewedRecipeId);
        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.commit();
    }
}