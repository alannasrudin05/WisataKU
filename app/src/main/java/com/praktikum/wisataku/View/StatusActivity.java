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
import com.praktikum.wisataku.databinding.ActivityProsesBinding;
import com.praktikum.wisataku.databinding.ActivityStatusBinding;

public class StatusActivity extends AppCompatActivity {

    ActivityStatusBinding activityStatusBinding;
    ChipNavigationBar bottomMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStatusBinding = ActivityStatusBinding.inflate(LayoutInflater.from(this));
        setContentView(activityStatusBinding.getRoot());

        bottomMenu = findViewById(R.id.bottom_navigation);

        activityStatusBinding.planeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatusActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });

        bottomMenu.setItemSelected(R.id.cart, true);
//        int selectedItem = getIntent().getIntExtra("selected_item", R.id.cart);
//            bottomMenu.setItemSelected(selectedItem, true);
//            bottomMenu.setOnItemSelectedListener(item -> {
//            setMode(item);
//
//        });
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }





    public void setMode(int selectedMode) {
        Intent intent = null;

        if (selectedMode == R.id.explore) {
            startActivity(new Intent(StatusActivity.this, MainActivity.class));
        } else if (selectedMode == R.id.cart) {
            startActivity(new Intent(StatusActivity.this, CartActivity.class));
        } else if (selectedMode == R.id.notification) {
            startActivity(new Intent(StatusActivity.this, CartActivity.class));
        } else if (selectedMode == R.id.profile) {
            startActivity(new Intent(StatusActivity.this, ProfileActivity.class));
        }

        if (intent != null) {
            intent.putExtra("selected_item", selectedMode);
            startActivity(intent);
        }
    }
}