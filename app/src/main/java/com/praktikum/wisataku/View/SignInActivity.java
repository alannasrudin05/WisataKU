package com.praktikum.wisataku.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.praktikum.wisataku.R;
import com.praktikum.wisataku.databinding.ActivityIntroBinding;
import com.praktikum.wisataku.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    protected ActivitySignInBinding activitySignInBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignInBinding = ActivitySignInBinding.inflate(LayoutInflater.from(this));
        setContentView(activitySignInBinding.getRoot());

        activitySignInBinding.signUpTxt.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
//            intent.putExtra("selected", 0);
            startActivity(intent);
        });

        activitySignInBinding.signInBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            intent.putExtra("username", String.valueOf(activitySignInBinding.usernameField));
//                intent.putExtra("selected", 0);
            startActivity(intent);
        });
    }
}