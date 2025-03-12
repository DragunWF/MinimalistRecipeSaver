package com.example.minimalistrecipesaver;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
}