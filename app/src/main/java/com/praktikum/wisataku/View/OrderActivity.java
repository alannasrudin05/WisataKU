package com.praktikum.wisataku.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.praktikum.wisataku.R;
import com.praktikum.wisataku.databinding.ActivityOrderBinding;
import com.praktikum.wisataku.databinding.ActivityStatusBinding;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding activityOrderBinding;
    private ChipNavigationBar bottomMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityOrderBinding = ActivityOrderBinding.inflate(LayoutInflater.from(this));
        setContentView(activityOrderBinding.getRoot());

        bottomMenu = findViewById(R.id.bottom_navigation);

        int selectedItem = getIntent().getIntExtra("selected_item", R.id.cart);
            bottomMenu.setItemSelected(selectedItem, true);
            bottomMenu.setOnItemSelectedListener(item -> {
            setMode(item);

        });

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }





    public void setMode(int selectedMode) {
        Intent intent = null;

        if (selectedMode == R.id.explore) {
            startActivity(new Intent(OrderActivity.this, MainActivity.class));
        } else if (selectedMode == R.id.cart) {
            startActivity(new Intent(OrderActivity.this, CartActivity.class));
        } else if (selectedMode == R.id.notification) {
            startActivity(new Intent(OrderActivity.this, CartActivity.class));
        } else if (selectedMode == R.id.profile) {
            startActivity(new Intent(OrderActivity.this, ProfileActivity.class));
        }

        if (intent != null) {
            intent.putExtra("selected_item", selectedMode);
            startActivity(intent);
        }
    }
}