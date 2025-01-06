package com.praktikum.wisataku.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.praktikum.wisataku.R;
import com.praktikum.wisataku.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ChipNavigationBar bottomMenu;
    protected ActivityProfileBinding activityProfileBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = ActivityProfileBinding.inflate(LayoutInflater.from(this));
        setContentView(activityProfileBinding.getRoot());
        bottomMenu = findViewById(R.id.bottom_navigation);

        int selectedItem = getIntent().getIntExtra("selected_item", R.id.profile);
        bottomMenu.setItemSelected(selectedItem, true);
        bottomMenu.setOnItemSelectedListener(item -> {
            setMode(item);

        });

    }

    public void setMode(int selectedMode) {
        Intent intent = null;

        if (selectedMode == R.id.explore) {
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        } else if (selectedMode == R.id.cart) {
            startActivity(new Intent(ProfileActivity.this, CartActivity.class));
        } else if (selectedMode == R.id.notification) {
            startActivity(new Intent(ProfileActivity.this, CartActivity.class));
        } else if (selectedMode == R.id.profile) {
            startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
        }

        if (intent != null) {
            intent.putExtra("selected_item", selectedMode);
            startActivity(intent);
        }
    }

}