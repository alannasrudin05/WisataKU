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

import com.praktikum.wisataku.R;
import com.praktikum.wisataku.databinding.ActivityPaymentBinding;
import com.praktikum.wisataku.databinding.ActivityProsesBinding;

public class ProsesActivity extends AppCompatActivity {

    ActivityProsesBinding activityProsesBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProsesBinding = ActivityProsesBinding.inflate(LayoutInflater.from(this));
        setContentView(activityProsesBinding.getRoot());

        activityProsesBinding.sudahTransferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProsesActivity.this, StatusActivity.class);
                startActivity(intent);
            }
        });

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


    }
}