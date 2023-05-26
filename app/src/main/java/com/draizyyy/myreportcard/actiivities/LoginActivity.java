package com.draizyyy.myreportcard.actiivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.draizyyy.myreportcard.R;
import com.draizyyy.myreportcard.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseAuth auth = FirebaseAuth.getInstance();
        Animation close = AnimationUtils.loadAnimation(this, R.anim.close_schoolnet_logo);
        Animation open = AnimationUtils.loadAnimation(this, R.anim.open_preloader_anim);

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
        close.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                binding.loadingAnim.setImageResource(R.drawable.load_animation_2_gif);
                binding.loadingAnim.startAnimation(open);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        binding.loginButton.setOnClickListener(view1 -> {
            binding.loadingAnim.startAnimation(close);


//            binding.loadingAnim.startAnimation(open);
//            String email = binding.loginLogin.getText().toString().trim();
//            String password = binding.loginPassword.getText().toString().trim();
//            Log.i("MY APP", "email: " + email + ", password " + password);
//            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
//                if (task.isSuccessful()) {
//                    Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
////            intent.putExtra("name", getTextValue(binding.email));
//                    startActivity(intent2);
//                    finish();
//                }
//            });
        });
    }
}