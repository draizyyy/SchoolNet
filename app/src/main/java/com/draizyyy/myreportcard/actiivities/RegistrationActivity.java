package com.draizyyy.myreportcard.actiivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.draizyyy.myreportcard.pojos.User;
import com.draizyyy.myreportcard.databinding.FragmentRegistrationBinding;
import com.draizyyy.myreportcard.retrofit.NetworkService;
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
            String name = binding.regName.getText().toString().trim();
            String surname = binding.regSurname.getText().toString().trim();
            Log.i("MY APP", "login: " + login + " password " + password);
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(login, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    new Thread(() -> {
                        NetworkService networkService = new NetworkService();
                        System.out.println("login + " + login);
                        networkService.addUser(new User(login, name, surname));
//                        DayDao dayDao = App.getDatabase().dayDao();
//                        dayDao.insertUser(new User(login, name, surname));
                    }).start();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
//            intent.putExtra("name", getTextValue(binding.email));
                    startActivity(intent);
                    finish();
                }
            });
        });
    }
}