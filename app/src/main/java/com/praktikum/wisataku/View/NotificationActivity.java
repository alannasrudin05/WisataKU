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
import com.praktikum.wisataku.databinding.ActivityNotificationBinding;

public class NotificationActivity extends AppCompatActivity {

    protected ActivityNotificationBinding activityNotificationBinding;
    private ChipNavigationBar bottomMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNotificationBinding = ActivityNotificationBinding.inflate(LayoutInflater.from(this));
        setContentView(activityNotificationBinding.getRoot());
        bottomMenu = findViewById(R.id.bottom_navigation);


        int selectedItem = getIntent().getIntExtra("selected_item", R.id.notification);
        bottomMenu.setItemSelected(selectedItem, true);
        bottomMenu.setOnItemSelectedListener(item -> {
            setMode(item);

        });

    }



    public void setMode(int selectedMode) {
        Intent intent = null;

        if (selectedMode == R.id.explore) {
            startActivity(new Intent(NotificationActivity.this, MainActivity.class));
        } else if (selectedMode == R.id.cart) {
            startActivity(new Intent(NotificationActivity.this, CartActivity.class));
        } else if (selectedMode == R.id.notification) {
            startActivity(new Intent(NotificationActivity.this, NotificationActivity.class));
        } else if (selectedMode == R.id.profile) {
            startActivity(new Intent(NotificationActivity.this, ProfileActivity.class));
        }

        if (intent != null) {
            intent.putExtra("selected_item", selectedMode);
            startActivity(intent);
        }
    }

}