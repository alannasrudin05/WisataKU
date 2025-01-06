package com.praktikum.wisataku.View;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.praktikum.wisataku.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    protected ActivityIntroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.signInBtn.setOnClickListener(v -> {
            Intent intent = new Intent(IntroActivity.this, SignInActivity.class);
            startActivity(intent);
        });

        binding.signUpTxt.setOnClickListener(v -> {
            Intent intent = new Intent(IntroActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }
}