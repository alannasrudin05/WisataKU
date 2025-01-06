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
import com.praktikum.wisataku.databinding.ActivitySignInBinding;
import com.praktikum.wisataku.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    protected ActivitySignUpBinding activitySignUpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignUpBinding = ActivitySignUpBinding.inflate(LayoutInflater.from(this));
        setContentView(activitySignUpBinding.getRoot());

        activitySignUpBinding.signInTxt.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            intent.putExtra("selected", 0);
            startActivity(intent);
        });

        activitySignUpBinding.signUpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            intent.putExtra("selected", 0);
            startActivity(intent);
        });

    }
}