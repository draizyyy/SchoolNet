package com.draizyyy.myreportcard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.draizyyy.myreportcard.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.loginButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            intent.putExtra("name", getTextValue(binding.email));
            startActivity(intent);
            finish();
        });
    }
}