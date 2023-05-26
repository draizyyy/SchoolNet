package com.draizyyy.myreportcard.actiivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.draizyyy.myreportcard.pojos.User;
import com.draizyyy.myreportcard.databinding.FragmentRegistrationBinding;
import com.draizyyy.myreportcard.retrofit.NetworkService;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private FragmentRegistrationBinding binding;
    final Handler h = new Handler();
    Vibrator v;
    private void makeBadToast() {
        Toast.makeText(getApplicationContext(), "Сервер недоступен", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        binding = FragmentRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.regButton.setOnClickListener(view -> {
            v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
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
                        if (networkService.isServerAccessible()) {
                            networkService.addUser(new User(login, name, surname));
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            h.post(() -> makeBadToast());
                        }
//                        DayDao dayDao = App.getDatabase().dayDao();
//                        dayDao.insertUser(new User(login, name, surname));
                    }).start();
                }
            });
        });
    }
}