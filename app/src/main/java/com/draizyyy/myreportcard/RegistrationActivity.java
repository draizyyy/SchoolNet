package com.draizyyy.myreportcard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.draizyyy.myreportcard.databinding.ActivityLoginBinding;
import com.draizyyy.myreportcard.databinding.FragmentRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private FragmentRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.regButton.setOnClickListener(view -> {
            String login = binding.regLogin.getText().toString().trim();
            String password = binding.regPassword.getText().toString().trim();
            Log.i("MY APP", "login: " + login + " password " + password);
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(login, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
//            intent.putExtra("name", getTextValue(binding.email));
                    startActivity(intent);
                    finish();
                }
            });
        });
    }
}