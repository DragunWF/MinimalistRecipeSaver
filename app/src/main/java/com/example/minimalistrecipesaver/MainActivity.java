package com.example.minimalistrecipesaver;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minimalistrecipesaver.helpers.DatabaseHelper;
import com.example.minimalistrecipesaver.helpers.Utils;

public class MainActivity extends AppCompatActivity {
    private Button addBtn;
    private SearchView searchBar;

    private RecyclerView recipeRecycler;
    private RecyclerView.Adapter recipeAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            DatabaseHelper.initialize(this);
            DatabaseHelper.clear(); // remove later
            DatabaseHelper.addDummyData();

            bindUIElements();
            setButtons();
            setRecycler();
        } catch (Exception err) {
            err.printStackTrace();
            Utils.longToast(err.getMessage(), this);
        }
    }

    private void bindUIElements() {
        addBtn = findViewById(R.id.addBtn);
        searchBar = findViewById(R.id.searchBar);
        recipeRecycler = findViewById(R.id.recipeRecycler);
    }

    private void setButtons() {
        addBtn.setOnClickListener(v -> {

        });
    }

    private void setRecycler() {
        recipeRecycler.setHasFixedSize(false);

        // TODO: Add adapter

        layoutManager = new LinearLayoutManager(this);
        recipeRecycler.setLayoutManager(layoutManager);
    }
}