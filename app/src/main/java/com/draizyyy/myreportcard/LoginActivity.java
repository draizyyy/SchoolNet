package com.draizyyy.myreportcard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.draizyyy.myreportcard.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            intent.putExtra("name", getTextValue(binding.email));
            startActivity(intent);
            finish();
//            binding.registrationButton.setOnClickListener(view -> {
//                Intent intent1 = new Intent();
//            });
        }
        binding.registrationButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
//            intent.putExtra("name", getTextValue(binding.email));
            startActivity(intent);
        });
        binding.loginButton.setOnClickListener(view1 -> {
            String email = binding.loginLogin.getText().toString().trim();
            String password = binding.loginPassword.getText().toString().trim();
            Log.i("MY APP", "email: " + email + ", password " + password);
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
//            intent.putExtra("name", getTextValue(binding.email));
                    startActivity(intent2);
                    finish();
                }
            });
        });
    }
}